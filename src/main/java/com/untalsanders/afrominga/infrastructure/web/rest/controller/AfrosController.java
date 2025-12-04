package com.untalsanders.afrominga.infrastructure.web.rest.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import com.untalsanders.afrominga.application.service.AfrosService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AfrosController extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(AfrosController.class);

    private final AfrosService afrosService;

    public AfrosController() {
        this.afrosService = new AfrosService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LOGGER.info("PATH: {}", req.getServletPath());
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().write(new Gson().toJson(afrosService.getAllAfros()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        super.doPost(req, res);
    }
}
