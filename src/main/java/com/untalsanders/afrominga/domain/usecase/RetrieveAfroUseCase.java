package com.untalsanders.afrominga.domain.usecase;

import com.untalsanders.afrominga.domain.model.Afro;

import java.util.List;

public interface RetrieveAfroUseCase {
    /**
     * Retrieve all <code>Afro</code>s.
     *
     * @return <code>Collection</code> of <code>Afro</code>s
     */
    List<Afro> getAllAfros();
}
