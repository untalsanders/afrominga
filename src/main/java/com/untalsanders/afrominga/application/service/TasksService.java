package com.untalsanders.afrominga.application.service;

import com.untalsanders.afrominga.domain.model.Task;
import com.untalsanders.afrominga.domain.usecase.RetrieveTaskUseCase;

import java.util.ArrayList;
import java.util.List;

public class TasksService implements RetrieveTaskUseCase {

    @Override
    public List<Task> getAll() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "Primera tarea"));
        taskList.add(new Task(2L, "Segunda tarea"));
        return taskList;
    }
}
