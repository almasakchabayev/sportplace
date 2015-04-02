package com.epam.aa.sportplace.dao;

import com.epam.aa.sportplace.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public abstract class DaoFactory {
    private static final Logger logger = LoggerFactory.getLogger(DaoFactory.class);

    private static Impl defaultImpl;

    public static void init(String impl) {
        // null and empty string are checked in ConfigurationProperties class
        if (!Impl.isImpl(impl)) {
            DaoException daoException = new DaoException("Such implementation '" + impl + "' does not exist");
            logger.error(daoException.getMessage(), daoException);
            throw daoException;
        }
        defaultImpl = Impl.getImplFromString(impl);
    }

    public static DaoFactory getInstance() {
        return getInstance(null);
    }

    public static DaoFactory getInstance(Impl impl) {
        if (impl == null) {
            if (defaultImpl == null) {
                DaoException daoException = new DaoException("DaoFactory was not initilized");
                logger.error(daoException.getMessage(), daoException);
                throw daoException;
            }
            impl = defaultImpl;
        }
        if (impl.equals(Impl.JDBC)) {
            if (JdbcDaoFactory.getDataSource() != null) {
                return new JdbcDaoFactory();
            }
            JdbcConfig.initJdbcDaoFactory();
            return new JdbcDaoFactory();
        }
        //cannot happen
        return null;
    }

    public abstract <T> T execute(DaoCommand<T> daoCommand);
    public abstract <T> T transaction(DaoCommand<T> daoCommand);
    public <T> T executeTx(final DaoCommand<T> daoCommand) {
        return execute(new DaoCommand<T>() {
            public T execute(DaoFactory daofactory) {
                return daofactory.transaction(daoCommand);
            }
        });
    }

    //TODO: add methods returning DAO here
    public abstract GenericDao<Customer> getCustomerDao();

    public enum Impl {
        JDBC;
        private static final Impl[] values = values();

        private String impl;

        static {
            JDBC.impl = "jdbc";
        }

        public String getImpl() {
            return impl;
        }

        public static boolean isImpl(String string) {
            if (getImplFromString(string) == null)
                return false;
            return true;
        }

        public static Impl getImplFromString(String string) {
            for (Impl impl : values) {
                if (impl.getImpl().equals(string))
                    return impl;
            }
            return null;
        }
    }
}