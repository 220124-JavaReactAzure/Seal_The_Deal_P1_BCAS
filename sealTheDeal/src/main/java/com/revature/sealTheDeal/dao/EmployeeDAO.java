package com.revature.sealTheDeal.dao;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.mapping.List;
import org.hibernate.query.Query;

import com.revature.sealTheDeal.models.Employee;
import com.revature.sealTheDeal.util.HibernateUtil;

public class EmployeeDAO {

	public boolean addEmployee(Employee employee) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(employee);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public List<Employee> getAllEmployees() {
		try {
			Session session = HibernateUtil.getSession();
			List<Employee> employee = session.createQuery("FROM employeeinfo").list();
			return employee;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public Employee getEmployeeByUsername(String username) {
		try {
			Session session = HibernateUtil.getSession();
			Employee employee = session.get(Employee.class, username);
			return employee;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public void updateEmployeeWithSessionMethod(Employee employee) {
		try {
			Session session = HibernateUtil.getSession();
			// Updates and Deletes always start with a transaction and end with a commit
			Transaction transaction = session.beginTransaction();
			session.merge(employee);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

	}

	// Not truly implemented
	public void updateEmployeeWithHQL(Employee employee) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();

			Query query = session.createQuery("update userinfo set username= :username," + " pass= pass,"
					+ " email= :email," + " first_name= :firstName," + " last_name= :lastName,"
					+ " accounttype=:accountype where accounttypes = ???");
			query.setParameter("email", employee.getEmail());
			query.executeUpdate();
			transaction.commit();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

	}

	public void deleteDirector(int id) {
	}
}
