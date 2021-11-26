package com.alderaeney.farmcrashbackend.item;

import java.util.Set;

import javax.persistence.*;

import com.alderaeney.farmcrashbackend.player.Player;
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
public class Item {
    @Id
    @SequenceGenerator(name = "item_sequence", sequenceName = "item_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_sequence")
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private ItemType type;
    @NonNull
    private Integer sellPrice;
    @Transient
    private String imageUrl;
    @NonNull
    private String filename;

    public String getImageUrl() {
        return "/items/" + this.type.toString() + "/" + this.filename + ".png";
    }
}
