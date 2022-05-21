package com.perinity.grc.service;

import com.perinity.grc.repository.CellRepository;
import com.perinity.grc.repository.UserPlayRepository;
import com.perinity.grc.repository.entity.UserPlay;

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
