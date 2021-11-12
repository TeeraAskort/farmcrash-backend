package com.alderaeney.farmcrashbackend.player;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.alderaeney.farmcrashbackend.crop.Crop;
import com.alderaeney.farmcrashbackend.crop.CropService;
import com.alderaeney.farmcrashbackend.crop.CropStage;
import com.alderaeney.farmcrashbackend.crop.exceptions.CropNotFarmeableException;
import com.alderaeney.farmcrashbackend.crop.exceptions.CropNotFoundException;
import com.alderaeney.farmcrashbackend.player.exceptions.CropNotFoundInPlayerException;
import com.alderaeney.farmcrashbackend.player.exceptions.NotEnoughMoneyException;
import com.alderaeney.farmcrashbackend.player.exceptions.PlayerByUSernameNotFoundException;
import com.alderaeney.farmcrashbackend.player.exceptions.PlayerNotFoundException;
import com.alderaeney.farmcrashbackend.player.exceptions.UsernameTakenException;
import com.alderaeney.farmcrashbackend.player.exceptions.WorkerAlreadyHiredException;
import com.alderaeney.farmcrashbackend.player.exceptions.WorkerNotFoundInPlayerException;
import com.alderaeney.farmcrashbackend.task.Task;
import com.alderaeney.farmcrashbackend.task.TaskService;
import com.alderaeney.farmcrashbackend.task.exceptions.TaskNotFoundException;
import com.alderaeney.farmcrashbackend.worker.Worker;
import com.alderaeney.farmcrashbackend.worker.WorkerService;
import com.alderaeney.farmcrashbackend.worker.exceptions.WorkerNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "api/v1/player")
@Slf4j
public class PlayerController {
    private final PlayerService playerService;
    private final TaskService taskService;
    private final CropService cropService;
    private final WorkerService workerService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PlayerController(PlayerService playerService, TaskService taskService, CropService cropService,
            WorkerService workerService, PasswordEncoder passwordEncoder) {
        this.playerService = playerService;
        this.taskService = taskService;
        this.cropService = cropService;
        this.workerService = workerService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(path = "login")
    public Player getPlayerById() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<Player> player = playerService.findPlayerByName(username);
        if (player.isPresent()) {
            return player.get();
        } else {
            throw new PlayerByUSernameNotFoundException(username);
        }
    }

    @GetMapping(path = "leaderboard")
    public List<Player> getLeaderBoard() {
        return playerService.getLeaderBoard();
    }

    @PostMapping(path = "create")
    public Player createPlayer(@RequestBody PlayerLogin userData) {
        Optional<Player> playerByName = playerService.findPlayerByName(userData.getName());
        if (playerByName.isPresent()) {
            throw new UsernameTakenException(userData.getName());
        } else {
            Optional<Crop> crop = cropService.getCropById(1L);
            if (crop.isPresent()) {
                ArrayList<Crop> crops = new ArrayList<>();
                Crop aux = crop.get();
                aux.setAmount(20);
                aux.setStage(CropStage.DAY0);
                crops.add(aux);
                Player player = new Player(userData.getName(), crops, new ArrayList<>(), new ArrayList<>(),
                        BigInteger.valueOf(1000L), LocalDate.now(), passwordEncoder.encode(userData.getPassword()));
                player.setAuthorities(List.of(new SimpleGrantedAuthority("PLAYER")));
                return playerService.addPlayer(player);
            } else
                return null;
        }
    }

    @GetMapping(path = "worker/{index}/assignTask/{taskId}")
    @Transactional
    public Player assignTaskToWorker(@PathVariable("index") Integer index, @PathVariable("taskId") Long taskId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<Player> player = playerService.findPlayerByName(username);
        if (player.isPresent()) {
            Player play = player.get();
            try {
                Worker worker = play.getWorkers().get(index);
                Optional<Task> task = taskService.getTaskById(taskId);
                if (task.isPresent()) {
                    worker.setTaskAssignedTo(task.get());
                    play.getWorkers().set(index, worker);
                    return play;
                } else
                    throw new TaskNotFoundException(taskId);
            } catch (Exception e) {
                throw new WorkerNotFoundInPlayerException(index);
            }
        } else {
            throw new PlayerByUSernameNotFoundException(username);
        }
    }

    @GetMapping(path = "crop/{index}/farmCrop")
    @Transactional
    public Player farmCrop(@PathVariable("index") Integer index) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        log.info(username);
        Optional<Player> player = playerService.findPlayerByName(username);
        if (player.isPresent()) {
            Player play = player.get();
            try {
                Crop crop = play.getCrops().get(index);
                if (crop.getStage() == CropStage.READYTOFARM) {
                    crop.setStage(CropStage.SELL);
                    play.getCrops().set(index, crop);
                    return play;
                } else
                    throw new CropNotFarmeableException(crop.getId());
            } catch (Exception e) {
                throw new CropNotFoundInPlayerException(index);
            }
        } else {
            throw new PlayerByUSernameNotFoundException(username);
        }
    }

    @GetMapping(path = "crop/{cropId}/buy/{amount}")
    @Transactional
    public Player buyCrop(@PathVariable("cropId") Long cropId, @PathVariable("amount") Integer amount) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<Player> player = playerService.findPlayerByName(username);
        if (player.isPresent()) {
            Player play = player.get();
            Optional<Crop> crop = cropService.getCropById(cropId);
            if (crop.isPresent()) {
                Integer price = crop.get().getBuyPrice() * amount;
                if (play.getMoney().subtract(BigInteger.valueOf(price)).longValue() >= 0) {
                    play.setMoney(play.getMoney().subtract(BigInteger.valueOf(price)));
                    Crop cropToAdd = crop.get();
                    cropToAdd.setAmount(amount);
                    cropToAdd.setStage(CropStage.DAY0);
                    play.getCrops().add(cropToAdd);
                    return play;
                } else {
                    throw new NotEnoughMoneyException(price, player.get().getMoney());
                }
            } else {
                throw new CropNotFoundException(cropId);
            }
        } else {
            throw new PlayerByUSernameNotFoundException(username);
        }
    }

    @GetMapping(path = "worker/{workerId}/hire")
    @Transactional
    public void hireWorker(@PathVariable("workerId") Long workerId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<Player> player = playerService.findPlayerByName(username);
        if (player.isPresent()) {
            Player play = player.get();
            Optional<Worker> worker = workerService.getWorkerById(workerId);
            if (worker.isPresent()) {
                if (!checkIfWorkerIsAlreadyHired(play.getWorkers(), worker.get().getId())) {
                    play.getWorkers().add(worker.get());
                } else {
                    throw new WorkerAlreadyHiredException(worker.get().getId());
                }
            } else {
                throw new WorkerNotFoundException(workerId);
            }
        } else {
            throw new PlayerByUSernameNotFoundException(username);
        }
    }

    private boolean checkIfWorkerIsAlreadyHired(List<Worker> workers, Long workerId) {
        for (Worker worker : workers) {
            if (worker.getId().equals(workerId)) {
                return true;
            }
        }
        return false;
    }
}
