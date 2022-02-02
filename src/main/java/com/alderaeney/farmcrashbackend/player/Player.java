package com.alderaeney.farmcrashbackend.player;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import com.alderaeney.farmcrashbackend.crop.Crop;
import com.alderaeney.farmcrashbackend.item.Item;
import com.alderaeney.farmcrashbackend.stats.Stats;
import com.alderaeney.farmcrashbackend.worker.Worker;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Player implements UserDetails {
    @Id
    // @SequenceGenerator(name = "player_sequence", sequenceName =
    // "player_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    @ElementCollection(targetClass = Crop.class, fetch = FetchType.LAZY)
    private List<Crop> crops;
    @NonNull
    @ElementCollection(targetClass = Worker.class, fetch = FetchType.LAZY)
    private List<Worker> workers;
    @NonNull
    @ElementCollection(targetClass = Item.class, fetch = FetchType.LAZY)
    private List<Item> items;
    @NonNull
    private BigInteger money;
    @NonNull
    private LocalDate lastTimePlayed;
    @NonNull
    private String image;
    @NonNull
    @JsonIgnore
    private String password;
    @ElementCollection(targetClass = GrantedAuthority.class, fetch = FetchType.EAGER)
    private List<GrantedAuthority> authorities = new ArrayList<>();

    @JsonIgnore
    @NonNull
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "player")
    private Stats stats;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "friends", joinColumns = @JoinColumn(name = "friender", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "friendOf", referencedColumnName = "id"))
    private List<Player> friends;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "friends", joinColumns = @JoinColumn(name = "friendOf", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "friender", referencedColumnName = "id"))
    private List<Player> friendOf;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "blockedPlayers", joinColumns = @JoinColumn(name = "blockedBy", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "blocked", referencedColumnName = "id"))
    private List<Player> blockedBy;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "blockedPlayers", joinColumns = @JoinColumn(name = "blocked", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "blockedBy", referencedColumnName = "id"))
    private List<Player> blockedPlayers;

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}