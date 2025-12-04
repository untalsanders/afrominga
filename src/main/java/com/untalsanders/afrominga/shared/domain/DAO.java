package com.untalsanders.afrominga.shared.domain;

public interface DAO<T> {
    void create(T entity);
    T read(String id);
    void update(String id);
    void delete(T entity);
}
