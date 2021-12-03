package com.alderaeney.farmcrashbackend.stats;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Data
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class DataSet implements Serializable {
    @JsonIgnore
    @Id
    @SequenceGenerator(name = "dataset_sequence", sequenceName = "dataset_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dataset_sequence")
    private Long id;
    @NonNull
    private String label;
    @NonNull
    private ArrayList<Integer> data;
    @NonNull
    private Boolean fill;
    @NonNull
    private String borderColor;
    @NonNull
    private Float tension;
}
