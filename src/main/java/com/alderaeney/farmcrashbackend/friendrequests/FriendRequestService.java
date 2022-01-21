package com.alderaeney.farmcrashbackend.friendrequests;

import java.util.List;

import com.alderaeney.farmcrashbackend.player.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendRequestService {

    private final FriendRequestRepository repository;

    @Autowired
    public FriendRequestService(FriendRequestRepository repository) {
        this.repository = repository;
    }

    public List<FriendRequest> getAllRequestsFromPlayer(Player player) {
        return repository.findAllFriendRequestsByPlayerSendingRequest(player);
    }

    public List<FriendRequest> getAllRequestsToPlayer(Player player) {
        return repository.findAllFriendRequestsByPlayerGettingTheRequest(player);
    }

    public void saveRequest(FriendRequest request) {
        repository.save(request);
    }

    public void removeRequest(FriendRequest request) {
        repository.delete(request);
    }
}
