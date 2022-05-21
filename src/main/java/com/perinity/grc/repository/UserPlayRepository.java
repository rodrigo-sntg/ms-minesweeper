package com.perinity.grc.repository;

import com.perinity.grc.repository.entity.Cell;
import com.perinity.grc.repository.entity.UserPlay;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserPlayRepository implements PanacheRepository<UserPlay> {

}