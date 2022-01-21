package com.alderaeney.farmcrashbackend.friendrequests;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alderaeney.farmcrashbackend.player.Player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class FriendRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne()
    @JoinColumn(name = "player_sending_id", referencedColumnName = "id")
    private Player playerSendingRequest;

    @NonNull
    @ManyToOne()
    @JoinColumn(name = "player_getting_id", referencedColumnName = "id")
    private Player playerGettingTheRequest;
}
