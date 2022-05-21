package com.perinity.grc.repository;

import javax.enterprise.context.ApplicationScoped;

import com.perinity.grc.repository.entity.Board;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class BoardRepository implements PanacheRepository<Board> {

}