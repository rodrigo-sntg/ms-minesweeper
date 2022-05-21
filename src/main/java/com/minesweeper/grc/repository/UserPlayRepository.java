package com.minesweeper.grc.repository;

import com.minesweeper.grc.repository.entity.UserPlay;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserPlayRepository implements PanacheRepository<UserPlay> {

}