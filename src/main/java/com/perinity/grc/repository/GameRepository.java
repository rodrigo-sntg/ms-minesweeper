package com.perinity.grc.repository;

import javax.enterprise.context.ApplicationScoped;

import com.perinity.grc.repository.entity.Game;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.Optional;

@ApplicationScoped
public class GameRepository implements PanacheRepository<Game> {

    public Optional<Game> findByCode(String code) {
        return find("code LIKE ?1", "%" + code + "%").firstResultOptional();
    }
}