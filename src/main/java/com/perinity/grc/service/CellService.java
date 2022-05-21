package com.perinity.grc.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.perinity.grc.repository.CellRepository;

@ApplicationScoped
public class CellService {

	@Inject
	CellRepository repository;

}
