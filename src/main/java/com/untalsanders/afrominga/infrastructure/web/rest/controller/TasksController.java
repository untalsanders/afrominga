package com.untalsanders.afrominga.infrastructure.web.rest.controller;

import com.google.gson.Gson;
import com.untalsanders.afrominga.application.service.TasksService;
import com.untalsanders.afrominga.domain.usecase.RetrieveTaskUseCase;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TasksController extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(TasksController.class);
    private final RetrieveTaskUseCase retrieveTaskUseCase;
    private final List<String> tasks = new ArrayList<>();

    public TasksController() {
        this.retrieveTaskUseCase = new TasksService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String pathInfo = req.getServletPath();

        if (pathInfo == null || pathInfo.equals("/tasks")) {
            LOGGER.info("PATH: {}", pathInfo);
            var jsonResponse = new Gson().toJson(retrieveTaskUseCase.getAll());

            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.setStatus(HttpServletResponse.SC_OK);
            res.getWriter().write(jsonResponse);
        } else {
            String taskId = pathInfo.substring(1); // Remove leading slash
            LOGGER.info("PATH: {}", pathInfo);
            int id;
            try {
                id = Integer.parseInt(taskId);
            } catch (NumberFormatException e) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                res.getWriter().write("Invalid task ID");
                return;
            }
            if (id < 0 || id >= tasks.size()) {
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                res.getWriter().write("Task not found");
                return;
            }
            res.getWriter().write(tasks.get(id));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String task = req.getParameter("task");
        if (task != null && !task.isEmpty()) {
            tasks.add(task);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("Task added successfully");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Task cannot be empty");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Task ID is required");
            return;
        }
        String taskId = pathInfo.substring(1); // Remove leading slash
        int id;
        try {
            id = Integer.parseInt(taskId);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid task ID");
            return;
        }
        if (id < 0 || id >= tasks.size()) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("Task not found");
            return;
        }
        String updatedTask = req.getParameter("task");
        if (updatedTask != null && !updatedTask.isEmpty()) {
            tasks.set(id, updatedTask);
            resp.getWriter().write("Task updated successfully");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Task cannot be empty");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Task ID is required");
            return;
        }
        String taskId = pathInfo.substring(1); // Remove leading slash
        int id;
        try {
            id = Integer.parseInt(taskId);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid task ID");
            return;
        }
        if (id < 0 || id >= tasks.size()) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("Task not found");
            return;
        }
        tasks.remove(id);
        resp.getWriter().write("Task deleted successfully");
    }
}
