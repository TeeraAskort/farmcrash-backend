package com.alderaeney.farmcrashbackend.friendrequests;

import java.util.List;
import java.util.Optional;

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
        return repository.findAllFriendRequestsByPlayerSendingRequestAndAccepted(player, false);
    }

    public List<FriendRequest> getAllRequestsToPlayer(Player player) {
        return repository.findAllFriendRequestsByPlayerGettingTheRequestAndAccepted(player, false);
    }

    public void saveRequest(FriendRequest request) {
        repository.save(request);
    }

    public void removeRequest(FriendRequest request) {
        repository.delete(request);
    }

    public Optional<FriendRequest> getFriendRequestByPlayerSendingAndPlayerGettingTheRequest(Player sender,
            Player getter) {
        return repository.findByPlayerGettingTheRequestAndPlayerSendingRequest(getter, sender);
    }
}
