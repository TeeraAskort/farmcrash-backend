package com.alderaeney.farmcrashbackend.worker;

import javax.persistence.*;

import com.alderaeney.farmcrashbackend.task.Task;

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
    @Transient
    private String imageUrl;
    @NonNull
    private String filename;
    @NonNull
    private Hired hired;
    @NonNull
    private Integer costOfHiring;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Task taskAssignedTo;

    public String getImageUrl() {
        return "/worker/" + this.filename + ".png";
    }
}
