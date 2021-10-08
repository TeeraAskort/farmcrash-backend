package com.alderaeney.farmcrashbackend.player;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import com.alderaeney.farmcrashbackend.crop.Crop;
import com.alderaeney.farmcrashbackend.item.Item;
import com.alderaeney.farmcrashbackend.worker.Worker;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Player {
    @Id
    @SequenceGenerator(name = "player_sequence", sequenceName = "player_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_sequence")
    private Long id;
    @NonNull
    private String name;
    @NonNull
    @ManyToMany
    @JoinTable(name = "player_crop", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "crop_id"))
    private List<Crop> crops;
    @NonNull
    @ManyToMany
    @JoinTable(name = "player_worker", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "worker_id"))
    private List<Worker> workers;
    @NonNull
    @ManyToMany
    @JoinTable(name = "player_item", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;
    @NonNull
    private BigInteger money;
    @NonNull
    private LocalDate lastTimePlayed;

}
