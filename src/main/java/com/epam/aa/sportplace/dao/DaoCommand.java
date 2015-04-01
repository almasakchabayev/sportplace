package com.epam.aa.sportplace.dao;

public interface DaoCommand {
    Object execute(DaoFactory daoFactory);
}
