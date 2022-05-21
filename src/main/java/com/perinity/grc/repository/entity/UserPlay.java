package com.perinity.grc.repository.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserPlay {
    @Id
    @SequenceGenerator(name = "userplayseq", sequenceName = "userplay_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "userplayseq")
	private Long id;
    @ManyToOne
    private Cell cell;
    private int playNumber;

    @ManyToOne
    private Game game;


    private State stateBeforePlay;
    private State stateAfterPlay;

    private boolean isFlag;
}