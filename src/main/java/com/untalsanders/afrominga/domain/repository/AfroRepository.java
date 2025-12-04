package com.untalsanders.afrominga.domain.repository;

import com.untalsanders.afrominga.domain.model.Afro;

import java.util.List;

public interface AfroRepository {
    List<Afro> findAll();
}
