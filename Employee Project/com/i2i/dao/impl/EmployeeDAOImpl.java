package com.i2i.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.i2i.dao.EmployeeDAO;
import com.i2i.database.DatabaseConnection;
import com.i2i.exception.EmployeeException;
import com.i2i.model.Employee;
import com.i2i.model.EmployeeId;
import com.i2i.model.Role;
import com.i2i.util.ConstantUtil;
import com.i2i.util.HibernateUtil;

/**
 * This class is used to perform the database operations
 * for Employee details. 
 * 
 * @author   Vishwaeaswaran M
 * @version  1.0 10 Aug 2022
 */ 
public class EmployeeDAOImpl implements EmployeeDAO {
    static Logger log = LogManager.getLogger(EmployeeDAOImpl.class.getName());

    /**
     * <p>
     * This mehtod is used to get the id from the employee_id table
     * </p>
     * 
     * @return Integer id
     *         for which the id needs to be access.
     *
     * @throws EmployeeException
     */
    public Integer getEmployeeId() throws EmployeeException {
        Session session = null;
        Transaction transaction = null;
        int id = 1;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hqlQuery = "FROM EmployeeId";
            Query query = session.createQuery(hqlQuery);
            List<EmployeeId> employeeIds = query.list();
            EmployeeId employeeId = new EmployeeId();
            if (!employeeIds.isEmpty()) {
                id = employeeIds.get(0).getId();
                id++;
            }
            transaction = session.beginTransaction();
            hqlQuery = "UPDATE EmployeeId SET id = :updatableId";
            query = session.createQuery(hqlQuery);
            query.setParameter("updatableId", id);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(hibernateException);
            throw new EmployeeException(hibernateException.getMessage());
        } finally {
            session.close();
        }
        return id;
    }

    /**
     * <p>
     * Get the employee and store it into the employee table
     * </p>
     * 
     * @param Employee employee
     *         for which the employee details to be inserted in database.
     * @return boolean
     *         returns boolean true if the employee inserted in table othrwise false.
     *
     * @throws EmployeeException
     */
    public boolean insertEmployee(Employee employee) throws EmployeeException {
        Session session = null;
        Transaction transaction = null;
        boolean status = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
            status = true;
        } catch (HibernateException hibernateException) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(hibernateException);
            throw new EmployeeException(hibernateException.getMessage());
        } finally {
            session.close();
        }
        return status;
    }

    /**
     * <p>
     * This method is used to update a employee for the given id in employee
     * table.
     * </p>
     * 
     * @param Employee employee
     *         for which the employee needs to be updated in table.
     * @return boolean value true if updated, otherwise false.
     *
     * @throws EmployeeException
     */
    public Employee updateEmployee(Employee employee) throws EmployeeException {
        Employee updatedEmployee = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
            updatedEmployee = employee;
        } catch (HibernateException hibernateException) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.error(hibernateException);
            throw new EmployeeException(hibernateException.getMessage());
        } finally {
            session.close();
        }
        return updatedEmployee;
    }

    /**
     * <p>
     * This method is used to send the entire employees as a list for 
     * called method
     * </p>
     * 
     * @param String roleName
     *         for which roleName is used to retrieve the employee base on role
     * @param int startValue
     *         for which startValue is used to set the start value of the employee
     * @return employees list if the employees are available in table
     *        otherwise return empty list.
     *
     * @throws EmployeeException
     */
    public List<Employee> retrieveEmployeesByRoleName(String roleName, int startValue) throws EmployeeException {
        Session session = null;
        List<Employee> employees;
        int pageSize = 5;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hqlQuery = "SELECT role.employees FROM Role role WHERE role.role = :roleName";
            Query query = session.createQuery(hqlQuery);
            query.setParameter("roleName", roleName);
            query.setFirstResult(startValue);
            query.setMaxResults(pageSize);
            employees = query.list();
        } catch (HibernateException hibernateException) {
            log.error(hibernateException);
            throw new EmployeeException(hibernateException.getMessage());
        } finally {
            session.close();
        }
        return employees;
    }

    /**
     * <p>
     * This method is used to delete a trainer for the given id from 
     * the table.
     * </p>
     * 
     * @param String id 
     *         for which id is needs to delete the particular trainer.
     * @return boolean value true if deleted, otherwise false.
     *
     * @throws EmployeeException
     */
    public boolean deleteTrainerById(String id) throws EmployeeException {
        boolean status = false;
        Employee employee = retrieveEmployeeByIdAndRole(id, ConstantUtil.TRAINER);
        if (employee != null) {
            Session session = null;
            Transaction transaction = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                transaction = session.beginTransaction();
                session.delete(employee);
                transaction.commit();
                status = true;
            } catch (HibernateException hibernateException) {
                if (transaction != null) {
                    transaction.rollback();
                }
                log.error(hibernateException);
                throw new EmployeeException(hibernateException.getMessage());
            } finally {
                session.close();
            }
        }
        return status;
    }

    /**
     * <p>
     * This method is used to delete a trainee for the given id from 
     * the table.
     * </p>
     * 
     * @param String id 
     *         for which id is needs to delete the particular trainee.
     * @return boolean value true if deleted, otherwise false.
     *
     * @throws EmployeeException
     */
    public boolean deleteTraineeById(String id) throws EmployeeException {
        boolean status = false;
        Employee employee = retrieveEmployeeByIdAndRole(id, ConstantUtil.TRAINEE);
        if (employee != null) {
            Session session = null;
            Transaction transaction = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                transaction = session.beginTransaction();
                session.delete(employee);
                transaction.commit();
                status = true;
            } catch (HibernateException hibernateException) {
                if (transaction != null) {
                    transaction.rollback();
                }
                log.error(hibernateException);
                throw new EmployeeException(hibernateException.getMessage());
            } finally {
                session.close();
            }
        }
        return status;
    }

    /**
     * <p>
     * This method is used to send a employee for the given id from 
     * the employee table to called method
     * </p>
     * 
     * @return Employee object if the employee is available in table
     *        otherwise return null.
     *
     * @throws EmployeeException
     */
    public Employee retrieveEmployeeByIdAndRole(String id, String roleName) throws EmployeeException {
        Session session = null;
        Employee employee = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hqlQuery = "SELECT Emp FROM Employee Emp JOIN Emp.roles roles WHERE roles.role = :roleName AND Emp.employeeId = :id";
            Query query = session.createQuery(hqlQuery);
            query.setParameter("roleName", roleName);
            query.setParameter("id", id);
            employee = (Employee) query.uniqueResult();
        } catch (HibernateException hibernateException) {
            log.error(hibernateException);
            throw new EmployeeException(hibernateException.getMessage());
        } finally {
            session.close();
        }
        return employee;
    }

    /**
     * <p>
     * This method is used to send the count of the 
     *  employees for called method
     * </p>
     * 
     * @return noOfRecords as long
     *
     * @throws EmployeeException
     */
    public long getNoOfEmployeeByRoleName(String roleName) throws EmployeeException {
        Session session = null;
        long noOfRecords = 0;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hqlQuery = "SELECT count (Emp.employeeId) FROM Employee Emp JOIN Emp.roles roles WHERE roles.role = :roleName";
            Query query = session.createQuery(hqlQuery);
            query.setParameter("roleName", roleName);
            noOfRecords = (long) query.uniqueResult();
        } catch (HibernateException hibernateException) {
            log.error(hibernateException);
            throw new EmployeeException(hibernateException.getMessage());
        } finally {
            session.close();
        }
        return noOfRecords;
    }

}