package com.alderaeney.farmcrashbackend.friendrequests;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.alderaeney.farmcrashbackend.player.Player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    List<FriendRequest> findAllFriendRequestsByPlayerSendingRequestAndAccepted(Player playerSendingRequest,
            Boolean accepted);

    List<FriendRequest> findAllFriendRequestsByPlayerGettingTheRequestAndAccepted(Player playerGettingTheRequest,
            Boolean accepted);

    Optional<FriendRequest> findByPlayerGettingTheRequestAndPlayerSendingRequest(
            Player playerGettingTheRequest, Player playerSendingRequest);

}
