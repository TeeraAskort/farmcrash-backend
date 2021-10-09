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
import com.alderaeney.farmcrashbackend.item.Item;
import com.alderaeney.farmcrashbackend.player.exceptions.NotEnoughMoneyException;
import com.alderaeney.farmcrashbackend.player.exceptions.PlayerNotFoundException;
import com.alderaeney.farmcrashbackend.task.Task;
import com.alderaeney.farmcrashbackend.task.TaskService;
import com.alderaeney.farmcrashbackend.task.exceptions.TaskNotFoundException;
import com.alderaeney.farmcrashbackend.worker.Worker;
import com.alderaeney.farmcrashbackend.worker.WorkerService;
import com.alderaeney.farmcrashbackend.worker.exceptions.WorkerNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/player")
public class PlayerController {
    private final PlayerService playerService;
    private final TaskService taskService;
    private final CropService cropService;
    private final WorkerService workerService;

    @Autowired
    public PlayerController(PlayerService playerService, TaskService taskService, CropService cropService,
            WorkerService workerService) {
        this.playerService = playerService;
        this.taskService = taskService;
        this.cropService = cropService;
        this.workerService = workerService;
    }

    @GetMapping(path = "{playerId}")
    public Player getPlayerById(@PathVariable("playerId") Long id) {
        Optional<Player> player = playerService.getPlayerById(id);
        if (player.isPresent()) {
            return player.get();
        } else {
            throw new PlayerNotFoundException(id);
        }
    }

    @GetMapping(path = "leaderboard")
    public List<Player> getLeaderBoard() {
        return playerService.getLeaderBoard();
    }

    @GetMapping(path = "create/{name}")
    public Player createPlayer(@PathVariable("name") String name) {
        Player player = new Player(name, new ArrayList<Crop>(), new ArrayList<Worker>(), new ArrayList<Item>(),
                BigInteger.valueOf(1000L), LocalDate.now());
        return playerService.addPlayer(player);
    }

    @GetMapping(path = "{playerId}/worker/{workerId}/assignTask/{taskId}")
    @Transactional
    public void assignTaskToWorker(@PathVariable("playerId") Long playerId, @PathVariable("workerId") Long workerId,
            @PathVariable("taskId") Long taskId) {
        Optional<Player> player = playerService.getPlayerById(playerId);
        if (player.isPresent()) {
            boolean workerFound = false;
            for (Worker worker : player.get().getWorkers()) {
                if (worker.getId().equals(workerId)) {
                    workerFound = true;
                    Optional<Task> task = taskService.getTaskById(taskId);
                    if (task.isPresent()) {
                        worker.setTaskAssignedTo(task.get());
                    } else {
                        throw new TaskNotFoundException(taskId);
                    }
                }
                if (!workerFound) {
                    throw new WorkerNotFoundException(workerId);
                }
            }
        } else {
            throw new PlayerNotFoundException(playerId);
        }
    }

    @GetMapping(path = "{playerId}/crop/{cropId}/farmCrop")
    @Transactional
    public void farmCrop(@PathVariable("playerId") Long playerId, @PathVariable("cropId") Long cropId) {
        Optional<Player> player = playerService.getPlayerById(playerId);
        if (player.isPresent()) {
            boolean cropFound = false;
            for (Crop crop : player.get().getCrops()) {
                if (crop.getId().equals(cropId)) {
                    cropFound = true;
                    if (crop.getStage() == CropStage.READYTOFARM) {
                        crop.setStage(CropStage.SELL);
                    } else {
                        throw new CropNotFarmeableException(cropId);
                    }
                }
            }
            if (!cropFound) {
                throw new CropNotFoundException(cropId);
            }
        } else {
            throw new PlayerNotFoundException(playerId);
        }
    }

    @GetMapping(path = "{playerId}/crop/{cropId}/buy/{amount}")
    @Transactional
    public void buyCrop(@PathVariable("playerId") Long playerId, @PathVariable("cropId") Long cropId,
            @PathVariable("amount") Integer amount) {
        Optional<Player> player = playerService.getPlayerById(playerId);
        if (player.isPresent()) {
            Optional<Crop> crop = cropService.getCropById(cropId);
            if (crop.isPresent()) {
                Integer price = crop.get().getBuyPrice() * amount;
                if (player.get().getMoney() - price >= 0) {
                    player.get().setMoney(player.get().getMoney() - price);
                    Crop cropToAdd = crop.get();
                    cropToAdd.setAmount(amount);
                    cropToAdd.setStage(CropStage.DAY0);
                    player.get().getCrops().add(cropToAdd);
                } else {
                    throw new NotEnoughMoneyException(price, player.get().getMoney());
                }
            } else {
                throw new CropNotFoundException(cropId);
            }
        } else {
            throw new PlayerNotFoundException(playerId);
        }
    }

    @GetMapping(path = "{playerId}/worker/{workerId}/hire")
    @Transactional
    public void hireWorker(@PathVariable("playerId") Long playerId, @PathVariable("workerId") Long workerId) {
        Optional<Player> player = playerService.getPlayerById(playerId);
        if (player.isPresent()) {
            Optional<Worker> worker = workerService.getWorkerById(workerId);
            if (worker.isPresent()) {
                player.get().getWorkers().add(worker.get());
            } else {
                throw new WorkerNotFoundException(workerId);
            }
        } else {
            throw new PlayerNotFoundException(playerId);
        }
    }
}
