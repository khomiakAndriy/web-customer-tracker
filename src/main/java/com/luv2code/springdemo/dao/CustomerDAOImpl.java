package com.luv2code.springdemo.dao;


import com.luv2code.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

    // need inject the session factory

    @Autowired
    private SessionFactory sessionFactory;


    public List<Customer> getCustomers() {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create query
        Query<Customer> theQuery =
                currentSession.createQuery("from Customer order by lastName", Customer.class);

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return results
        return customers;
    }

    public void saveCustomer(Customer customer) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(customer);
    }

    public Customer getCustomer(int id) {
        Session currentSession = sessionFactory.getCurrentSession();


        return currentSession.get(Customer.class, id);
    }

    public void deleteCustomer(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query query =
                currentSession.createQuery("delete from Customer where id=:customerId");

        query.setParameter("customerId", id);

        query.executeUpdate();
    }
}
