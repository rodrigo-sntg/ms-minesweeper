package com.perinity.grc.repository;

import javax.enterprise.context.ApplicationScoped;

import com.perinity.grc.repository.entity.Cell;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CellRepository implements PanacheRepository<Cell> {

}