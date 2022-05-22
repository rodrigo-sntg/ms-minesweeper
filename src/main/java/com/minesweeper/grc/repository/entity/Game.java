package com.minesweeper.grc.repository.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Game {

	public static final int DIFFICULTY_EASY = 1;
	public static final int DIFFICULTY_MEDIUM = 2;
	public static final int DIFFICULTY_HARD = 3;
	public static final int DIFFICULTY_CHUCH_NORRIS = 4;

    public static final int DIFFICULTY_CUSTOM = 5;

    @Id
    @SequenceGenerator(name = "gameseq", sequenceName = "game_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "gameseq")
	private Long id;
	@ManyToOne
    @Cascade(CascadeType.ALL)
    private Board board;
    private State state;
    private String player;
    private int difficulty;
    private String code;

    @ElementCollection
    @CollectionTable(name = "starting_times", joinColumns = @JoinColumn(name = "id"))
    private List<Long> startingTimeList = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "ending_times", joinColumns = @JoinColumn(name = "id"))
    private List<Long> endingTimeList = new ArrayList<>();


    @OneToMany(orphanRemoval = true)
    @JoinTable(
        name="rel_game_userplays",
        joinColumns = @JoinColumn( name="game_id"),
        inverseJoinColumns = @JoinColumn( name="userplay_id")
    )
    private List<UserPlay> playsList  = new ArrayList<>();

    public void initialize(){

    }
    public void start(){

    }
    public void playGame(){

    }
}