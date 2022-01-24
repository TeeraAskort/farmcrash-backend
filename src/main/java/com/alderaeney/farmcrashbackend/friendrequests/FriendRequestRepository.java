package com.alderaeney.farmcrashbackend.friendrequests;

import java.util.List;
import java.util.Optional;

import com.alderaeney.farmcrashbackend.player.Player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    List<FriendRequest> findAllFriendRequestsByPlayerSendingRequest(Player playerSendingRequest);

    List<FriendRequest> findAllFriendRequestsByPlayerGettingTheRequest(Player playerGettingTheRequest);

    Optional<FriendRequest> findByPlayerGettingTheRequestAndPlayerSendingRequest(
            Player playerGettingTheRequest, Player playerSendingRequest);

}