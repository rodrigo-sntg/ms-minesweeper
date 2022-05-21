package com.minesweeper.grc.repository;

import javax.enterprise.context.ApplicationScoped;

import com.minesweeper.grc.repository.entity.Board;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class BoardRepository implements PanacheRepository<Board> {

}