package com.untalsanders.afrominga.domain.usecase;

import com.untalsanders.afrominga.domain.model.Task;

import java.util.List;

public interface RetrieveTaskUseCase {
    List<Task> getAll();
}
