package com.alderaeney.farmcrashbackend.player;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.alderaeney.farmcrashbackend.crop.Crop;
import com.alderaeney.farmcrashbackend.crop.CropService;
import com.alderaeney.farmcrashbackend.crop.CropStage;
import com.alderaeney.farmcrashbackend.crop.exceptions.CropNotFarmeableException;
import com.alderaeney.farmcrashbackend.crop.exceptions.CropNotFoundException;
import com.alderaeney.farmcrashbackend.item.Item;
import com.alderaeney.farmcrashbackend.item.ItemService;
import com.alderaeney.farmcrashbackend.player.exceptions.CannotStoreImageException;
import com.alderaeney.farmcrashbackend.player.exceptions.CropNotFoundInPlayerException;
import com.alderaeney.farmcrashbackend.player.exceptions.ImageTooBigException;
import com.alderaeney.farmcrashbackend.player.exceptions.ImageTypeNotSupported;
import com.alderaeney.farmcrashbackend.player.exceptions.IndexOutOfBoundsException;
import com.alderaeney.farmcrashbackend.player.exceptions.NotEnoughMoneyException;
import com.alderaeney.farmcrashbackend.player.exceptions.NotEnoughMoneyToHireException;
import com.alderaeney.farmcrashbackend.player.exceptions.NotEnoughMoneyToPerformTaskException;
import com.alderaeney.farmcrashbackend.player.exceptions.PasswordsDoNotMatchException;
import com.alderaeney.farmcrashbackend.player.exceptions.PlayerByUSernameNotFoundException;
import com.alderaeney.farmcrashbackend.player.exceptions.UsernameTakenException;
import com.alderaeney.farmcrashbackend.player.exceptions.WorkerAlreadyHiredException;
import com.alderaeney.farmcrashbackend.player.exceptions.WorkerNotFoundInPlayerException;
import com.alderaeney.farmcrashbackend.stats.DataSet;
import com.alderaeney.farmcrashbackend.stats.Stats;
import com.alderaeney.farmcrashbackend.stats.StatsService;
import com.alderaeney.farmcrashbackend.task.Task;
import com.alderaeney.farmcrashbackend.task.TaskService;
import com.alderaeney.farmcrashbackend.task.exceptions.TaskNotFoundException;
import com.alderaeney.farmcrashbackend.worker.Hired;
import com.alderaeney.farmcrashbackend.worker.Worker;
import com.alderaeney.farmcrashbackend.worker.WorkerService;
import com.alderaeney.farmcrashbackend.worker.exceptions.WorkerNotFoundException;

import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "api/v1/player")
@Slf4j
public class PlayerController {
    private final PlayerService playerService;
    private final TaskService taskService;
    private final CropService cropService;
    private final WorkerService workerService;
    private final ItemService itemService;
    private final StatsService statsService;
    private final PasswordEncoder passwordEncoder;

    private final String IMAGESPATH = "userImages/";

    private static final List<String> ALLOWEDIMAGETYPES = Arrays.asList("image/png", "image/jpg", "image/jpeg",
            "image/gif");

    private final Integer MAXIMAGESIZE = 1048576;

    @Autowired
    public PlayerController(PlayerService playerService, TaskService taskService, CropService cropService,
            WorkerService workerService, ItemService itemService, StatsService statsService,
            PasswordEncoder passwordEncoder) {
        this.playerService = playerService;
        this.taskService = taskService;
        this.cropService = cropService;
        this.workerService = workerService;
        this.itemService = itemService;
        this.statsService = statsService;
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
        if (userData.getName().equals("prova")) {
            Optional<Player> playerByName = playerService.findPlayerByName(userData.getName());
            if (playerByName.isPresent()) {
                throw new UsernameTakenException(userData.getName());
            } else {
                if (userData.getPassword().equals(userData.getPasswordRepeat())) {
                    Optional<Crop> crop = cropService.getCropById(1L);
                    if (crop.isPresent()) {
                        ArrayList<Crop> crops = new ArrayList<>();
                        Crop aux = new Crop(CropStage.DAY0, crop.get().getName(), crop.get().getSellPrice(),
                                crop.get().getBuyPrice(), crop.get().getType(), 20, crop.get().getFileName());

                        cropService.addCrop(aux);
                        crops.add(aux);
                        Stats stats = new Stats(
                                new ArrayList<>(
                                        List.of("09/12/2021", "10/12/2021", "11/12/2021", "12/12/2021", "13/12/2021")),
                                List.of(new DataSet("Money over time",
                                        new ArrayList<>(List.of(1000, 3000, 1250, 2200, 1500)),
                                        false, "rgb(221, 16, 16)", 0.1F)));
                        statsService.saveStats(stats);
                        Player player = new Player(userData.getName(), crops, new ArrayList<>(), new ArrayList<>(),
                                BigInteger.valueOf(1000L), LocalDate.now(), "playerImage/avatar.png",
                                passwordEncoder.encode(userData.getPassword()),
                                stats);
                        stats.setPlayer(player);
                        player.setAuthorities(List.of(new SimpleGrantedAuthority("PLAYER")));
                        return playerService.addPlayer(player);
                    } else
                        return null;
                } else
                    throw new PasswordsDoNotMatchException();
            }
        } else {
            Optional<Player> playerByName = playerService.findPlayerByName(userData.getName());
            if (playerByName.isPresent()) {
                throw new UsernameTakenException(userData.getName());
            } else {
                if (userData.getPassword().equals(userData.getPasswordRepeat())) {
                    Optional<Crop> crop = cropService.getCropById(1L);
                    if (crop.isPresent()) {
                        ArrayList<Crop> crops = new ArrayList<>();
                        Crop aux = new Crop(CropStage.DAY0, crop.get().getName(), crop.get().getSellPrice(),
                                crop.get().getBuyPrice(), crop.get().getType(), 20, crop.get().getFileName());

                        cropService.addCrop(aux);
                        crops.add(aux);
                        Stats stats = new Stats(new ArrayList<>(List.of(formatDate(LocalDate.now()))),
                                List.of(new DataSet("Money over time", new ArrayList<>(List.of(1000)),
                                        false, "rgb(221, 16, 16)", 0.1F)));
                        statsService.saveStats(stats);
                        Player player = new Player(userData.getName(), crops, new ArrayList<>(), new ArrayList<>(),
                                BigInteger.valueOf(1000L), LocalDate.now(), "playerImage/avatar.png",
                                passwordEncoder.encode(userData.getPassword()),
                                stats);
                        stats.setPlayer(player);
                        player.setAuthorities(List.of(new SimpleGrantedAuthority("PLAYER")));
                        return playerService.addPlayer(player);
                    } else
                        return null;
                } else
                    throw new PasswordsDoNotMatchException();
            }
        }
    }

    @GetMapping(path = "worker/{index}/assignTask/{taskId}")
    public Player assignTaskToWorker(@PathVariable("index") Integer index, @PathVariable("taskId") Long taskId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<Player> player = playerService.findPlayerByName(username);
        if (player.isPresent()) {
            Player play = player.get();
            if (index >= 0 || index < play.getWorkers().size()) {
                Worker worker = play.getWorkers().get(index);
                Optional<Task> task = taskService.getTaskById(taskId);
                if (task.isPresent()) {
                    if (play.getMoney().subtract(BigInteger.valueOf(task.get().getCost())).longValue() >= 0) {
                        Task nTask = new Task(task.get().getType(), task.get().getDaysLeft(), task.get().getCost(),
                                true);
                        worker.setTaskAssignedTo(nTask);
                        taskService.insertTask(nTask);
                        play.getWorkers().set(index, worker);
                        play.setMoney(play.getMoney().subtract(BigInteger.valueOf(task.get().getCost())));
                        Stats stats = updateStats(play.getStats(), play.getMoney().intValue());
                        play.setStats(stats);
                        return play;
                    } else {
                        throw new NotEnoughMoneyToPerformTaskException(play.getMoney().intValue(),
                                task.get().getCost());
                    }
                } else
                    throw new TaskNotFoundException(taskId);
            } else
                throw new WorkerNotFoundInPlayerException(index);

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
                    int aux = checkIfCropExistsAndIsReadyToSell(play.getCrops(), crop.getName());
                    if (aux > -1) {
                        Crop cropToAdd = play.getCrops().get(aux);
                        cropToAdd.setAmount(cropToAdd.getAmount() + crop.getAmount());
                        play.getCrops().remove(crop);
                        play.getCrops().set(aux, cropToAdd);
                    } else {
                        crop.setStage(CropStage.SELL);
                        play.getCrops().set(index, crop);
                    }
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
                    Crop cropToAdd = new Crop(CropStage.DAY0, crop.get().getName(), crop.get().getSellPrice(),
                            crop.get().getBuyPrice(), crop.get().getType(), amount, crop.get().getFileName());
                    cropService.addCrop(cropToAdd);
                    play.getCrops().add(cropToAdd);
                    Stats stats = updateStats(play.getStats(), play.getMoney().intValue());
                    play.setStats(stats);
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
    public Player hireWorker(@PathVariable("workerId") Long workerId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<Player> player = playerService.findPlayerByName(username);
        if (player.isPresent()) {
            Player play = player.get();
            Optional<Worker> worker = workerService.getWorkerById(workerId);
            if (worker.isPresent()) {
                if (!checkIfWorkerIsAlreadyHired(play.getWorkers(), worker.get())) {
                    Worker aux = worker.get();
                    if (play.getMoney().subtract(BigInteger.valueOf(aux.getCostOfHiring())).longValue() >= 0) {
                        Worker nWorker = new Worker(aux.getName(), aux.getAge(), aux.getFilename(), Hired.HIRED, 0);
                        workerService.insertWorker(nWorker);
                        play.getWorkers().add(nWorker);
                        play.setMoney(play.getMoney().subtract(BigInteger.valueOf(aux.getCostOfHiring())));
                        Stats stats = updateStats(play.getStats(), play.getMoney().intValue());
                        play.setStats(stats);
                        return play;
                    } else {
                        throw new NotEnoughMoneyToHireException(play.getMoney().intValue(), aux.getCostOfHiring());
                    }
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

    @GetMapping(path = "crop/{index}/sell")
    @Transactional
    public Player sellCrop(@PathVariable("index") Integer index) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<Player> player = playerService.findPlayerByName(username);
        if (player.isPresent()) {
            Player play = player.get();
            if (index >= 0 && index < play.getCrops().size()) {
                Crop crop = play.getCrops().get(index);
                Integer price = crop.getSellPrice() * crop.getAmount();
                play.setMoney(play.getMoney().add(BigInteger.valueOf(price)));
                play.getCrops().remove(crop);
                cropService.removeCrop(crop.getId());
                Stats stats = updateStats(play.getStats(), play.getMoney().intValue());
                play.setStats(stats);
                return play;
            } else {
                throw new IndexOutOfBoundsException(index);
            }
        } else {
            throw new PlayerByUSernameNotFoundException(username);
        }
    }

    @GetMapping(path = "item/{index}/sell")
    @Transactional
    public Player sellItem(@PathVariable("index") Integer index) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<Player> player = playerService.findPlayerByName(username);
        if (player.isPresent()) {
            Player play = player.get();
            if (index >= 0 && index < play.getItems().size()) {
                Item item = play.getItems().get(index);
                Integer price = item.getSellPrice();
                play.setMoney(play.getMoney().add(BigInteger.valueOf(price)));
                play.getItems().remove(item);
                itemService.removeItem(item.getId());
                Stats stats = updateStats(play.getStats(), play.getMoney().intValue());
                play.setStats(stats);
                return play;
            } else {
                throw new IndexOutOfBoundsException(index);
            }
        } else {
            throw new PlayerByUSernameNotFoundException(username);
        }
    }

    @GetMapping(path = "stats")
    public Stats getStats() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<Player> player = playerService.findPlayerByName(username);
        if (player.isPresent()) {
            return player.get().getStats();
        } else {
            throw new PlayerByUSernameNotFoundException(username);
        }
    }

    @PostMapping(path = "uploadImage")
    @Transactional
    public Player uploadUserImage(@RequestParam("image") MultipartFile image) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<Player> player = playerService.findPlayerByName(username);

        Boolean found = false;
        for (String type : ALLOWEDIMAGETYPES) {
            if (type.equals(image.getContentType())) {
                found = true;
            }
        }
        if (found) {
            throw new ImageTypeNotSupported();
        }

        if (image.getSize() > MAXIMAGESIZE) {
            throw new ImageTooBigException();
        }

        if (player.isPresent()) {
            Player play = player.get();
            String extension = FilenameUtils.getExtension(image.getOriginalFilename());
            String fileName = username + "." + extension;
            Path path = Paths.get(IMAGESPATH + fileName);
            try {
                Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                String storedPath = ServletUriComponentsBuilder.fromCurrentContextPath().path(IMAGESPATH).path(fileName)
                        .toUriString();
                play.setImage(storedPath);
                return play;
            } catch (IOException e) {
                throw new CannotStoreImageException();
            }
        } else {
            throw new PlayerByUSernameNotFoundException(username);
        }
    }

    private boolean checkIfWorkerIsAlreadyHired(List<Worker> workers, Worker newWorker) {
        if (newWorker.getHired() == Hired.HIRED) {
            return true;
        }
        for (Worker worker : workers) {
            if (worker.getName().equals(newWorker.getName())) {
                return true;
            }
        }
        return false;
    }

    private int checkIfCropExistsAndIsReadyToSell(List<Crop> crops, String cropName) {
        for (int i = 0; i < crops.size(); i++) {
            Crop crop = crops.get(i);
            if (crop.getName().equals(cropName) && crop.getStage() == CropStage.SELL) {
                return i;
            }
        }
        return -1;
    }

    private String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    private Stats updateStats(Stats stats, Integer money) {
        String date = formatDate(LocalDate.now());
        Integer index = stats.getLabels().indexOf(date);
        if (index == -1) {
            stats.getLabels().add(date);
            stats.getDatasets().get(0).getData().add(money);
        } else {
            stats.getDatasets().get(0).getData().set(index, money);
        }

        return stats;
    }
}
