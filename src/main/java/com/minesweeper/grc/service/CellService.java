package com.minesweeper.grc.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.minesweeper.grc.repository.CellRepository;

@ApplicationScoped
public class CellService {

	@Inject
    CellRepository repository;

}
