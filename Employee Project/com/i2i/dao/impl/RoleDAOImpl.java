package com.i2i.dao.impl;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.i2i.dao.RoleDAO;
import com.i2i.exception.EmployeeException;
import com.i2i.model.Role;
import com.i2i.util.HibernateUtil;

/**
 * This class is used to perform the database operations
 * for Role details. 
 * 
 * @author   Vishwaeaswaran M
 * @version  1.0 17 Sep 2022
 */ 
public class RoleDAOImpl implements RoleDAO {
    static Logger log = LogManager.getLogger(EmployeeDAOImpl.class.getName());

    /**
     * <p>
     * This method is used to send a role for the given role name from 
     * the role table to called method
     * </p>
     * 
     * @return role as Role object if the role is available in table
     *        otherwise return null.
     *
     * @throws EmployeeException
     */
    public Role retrieveRoleByRoleName(String roleName) throws EmployeeException {
        Session session = null;
        Role role = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hqlQuery = "FROM Role WHERE role = :roleName";
            Query query = session.createQuery(hqlQuery);
            query.setParameter("roleName", roleName);
            List<Role> roles = query.list();
            if (!roles.isEmpty()) {
                role = roles.get(0);
            }
        } catch (HibernateException hibernateException) {
            log.error(hibernateException);
            throw new EmployeeException(hibernateException.getMessage());
        } finally {
            session.close();
        }
        return role;
    }
}