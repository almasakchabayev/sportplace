package com.epam.aa.sportplace.dao;

public interface DaoCommand<T> {
    T execute(DaoFactory daoFactory);
}
