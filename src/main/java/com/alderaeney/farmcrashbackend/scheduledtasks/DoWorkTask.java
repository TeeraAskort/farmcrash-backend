package com.alderaeney.farmcrashbackend.scheduledtasks;

import com.alderaeney.farmcrashbackend.player.PlayerService;
import com.alderaeney.farmcrashbackend.task.TaskType;
import com.alderaeney.farmcrashbackend.worker.Worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import com.alderaeney.farmcrashbackend.item.Item;
import com.alderaeney.farmcrashbackend.item.ItemService;
import com.alderaeney.farmcrashbackend.item.ItemType;
import com.alderaeney.farmcrashbackend.player.Player;
import com.alderaeney.farmcrashbackend.task.Task;
import com.alderaeney.farmcrashbackend.task.TaskRepository;

@Component
public class DoWorkTask {

    private final PlayerService playerService;
    private final ItemService itemService;
    private final TaskRepository taskRepository;

    @Autowired
    public DoWorkTask(PlayerService playerService, ItemService itemService, TaskRepository taskRepository) {
        this.playerService = playerService;
        this.itemService = itemService;
        this.taskRepository = taskRepository;
    }

    @Scheduled(fixedRate = 10000)
    private void performTasks() {
        List<Player> players = playerService.getAllPlayers();

        for (Player player : players) {
            for (Worker worker : player.getWorkers()) {
                Task task = (Task) worker.getTaskAssignedTo();
                if (task != null) {
                    task.setDaysLeft(task.getDaysLeft() - 1);
                    if (task.getDaysLeft() <= 0) {
                        worker.setTaskAssignedTo(null);
                        taskRepository.delete(task);
                        if (task.getType() == TaskType.FISHING) {
                            Item item = itemService.findRandomItemByType(ItemType.FISH);
                            player.getItems().add(item);
                        }
                    }
                }
            }
        }
    }

}
