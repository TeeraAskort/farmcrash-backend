package com.alderaeney.farmcrashbackend.crop;

import java.util.Set;

import javax.persistence.*;

import com.alderaeney.farmcrashbackend.player.Player;

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
public class Crop {
    @Id
    @SequenceGenerator(name = "crop_sequence", sequenceName = "crop_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crop_sequence")
    private Long id;
    @NonNull
    private CropStage stage;
    @NonNull
    private String name;
    @NonNull
    private Integer sellPrice;
    @NonNull
    private Integer buyPrice;
    @NonNull
    private CropType type;
    @Transient
    private String imageUrl;
    private Integer amount;

    @ManyToMany(mappedBy = "crops")
    private Set<Player> players;

    public String getImageUrl() {
        return "/images/crops/" + this.stage.toString() + "/" + this.name + ".png";
    }
}
