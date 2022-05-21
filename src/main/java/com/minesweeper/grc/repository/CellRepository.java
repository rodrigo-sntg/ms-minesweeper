package com.minesweeper.grc.repository;

import javax.enterprise.context.ApplicationScoped;

import com.minesweeper.grc.repository.entity.Cell;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CellRepository implements PanacheRepository<Cell> {

}