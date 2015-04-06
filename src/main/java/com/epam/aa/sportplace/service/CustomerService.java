package com.epam.aa.sportplace.service;

import com.epam.aa.sportplace.dao.CustomerDao;
import com.epam.aa.sportplace.dao.DaoCommand;
import com.epam.aa.sportplace.dao.DaoFactory;
import com.epam.aa.sportplace.model.Customer;

public class CustomerService {
    private CustomerDao customerDao;

    public Integer create(Customer customer) {
        return new CreateCustomerPersistenceAction(customer).doAction();
    }

//    public Customer find(final Integer integer) {
//        DaoFactory daoFactory = DaoFactory.getInstance();
//        return daoFactory.executeTx(factory -> {
//            CustomerDao customerDAO = factory.getCustomerDao();
//            return customerDAO.read(integer);
//        });
//    }
//
//    public boolean update(final Customer customer) {
//        DaoFactory daoFactory = DaoFactory.getInstance();
//        return daoFactory.executeTx(new DaoCommand<Boolean>() {
//            public Boolean execute(DaoFactory daoFactory) {
//                CustomerDao customerDAO = daoFactory.getCustomerDao();
//                return customerDAO.update(customer);
//            }
//        });
//    }
//
//    public boolean delete(final Customer customer) {
//        DaoFactory daoFactory = DaoFactory.getInstance();
//        return daoFactory.executeTx(new DaoCommand<Boolean>() {
//            public Boolean execute(DaoFactory daoFactory) {
//                CustomerDao customerDAO = daoFactory.getCustomerDao();
//                return customerDAO.delete(customer);
//            }
//        });
//    }

    private class CreateCustomerPersistenceAction extends PersistenceActionBase<Customer> {

        public CreateCustomerPersistenceAction(Customer customer) {
            super(customer);
        }

        protected Integer doPersistenceAction(DaoFactory daoFactory) {
            CustomerDao customerDAO = daoFactory.getCustomerDao();
            return customerDAO.create(super.e);
        }
    }
}
