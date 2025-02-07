package com.example.gameLibrary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "Library")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Library {

    @Id
    @GeneratedValue
    @Column(name = "id_library")
    private Long id;


    @Column(name = "game_count")
    private Integer gameCount;

    @ManyToMany(targetEntity = Game.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.REMOVE})
    @JoinTable(
            name = "Library_Games",
            joinColumns = @JoinColumn(name = "id_library"),
            inverseJoinColumns = @JoinColumn(name = "id_game")
    )
    private Collection<Game> games;

    @OneToOne(targetEntity = LibraryUser.class, mappedBy = "userLibrary")
    private LibraryUser libraryOwner;


}
