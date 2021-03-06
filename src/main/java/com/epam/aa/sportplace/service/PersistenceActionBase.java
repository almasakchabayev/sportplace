package com.epam.aa.sportplace.service;

import com.epam.aa.sportplace.dao.DaoFactory;

public abstract class PersistenceActionBase<E> {
    protected E e;

    public PersistenceActionBase(E e) {
        this.e = e;
    }

    protected <T> T doAction() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        return daoFactory.executeTx(factory -> doPersistenceAction(factory));
    }

    protected abstract <T> T doPersistenceAction(DaoFactory daoFactory);

}
