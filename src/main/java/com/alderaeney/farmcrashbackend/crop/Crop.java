package com.alderaeney.farmcrashbackend.crop;

import javax.persistence.*;

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
    // @SequenceGenerator(name = "crop_sequence", sequenceName = "crop_sequence",
    // allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @NonNull
    private Integer amount;
    @NonNull
    private String fileName;

    public String getImageUrl() {
        return "/crop/" + this.stage.toString() + "/" + this.fileName + ".png";
    }
}
