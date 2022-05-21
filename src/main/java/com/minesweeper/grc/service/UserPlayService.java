package com.minesweeper.grc.service;

import com.minesweeper.grc.repository.UserPlayRepository;
import com.minesweeper.grc.repository.entity.UserPlay;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserPlayService {

	@Inject
    UserPlayRepository repository;

    public UserPlay inserir(UserPlay userPlay){
        this.repository.persist(userPlay);
        return userPlay;
    }

}
