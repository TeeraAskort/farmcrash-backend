package com.alderaeney.farmcrashbackend.worker;

import java.util.Set;

import javax.persistence.*;

import com.alderaeney.farmcrashbackend.player.Player;
import com.alderaeney.farmcrashbackend.task.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Worker {
    @Id
    @SequenceGenerator(name = "worker_sequence", sequenceName = "worker_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "worker_sequence")
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private Integer age;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Task taskAssignedTo;
    @ManyToMany(mappedBy = "workers", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Player> players;
}
