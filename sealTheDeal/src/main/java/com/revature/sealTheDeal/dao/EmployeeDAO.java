package com.revature.sealTheDeal.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.sealTheDeal.models.Booking;
import com.revature.sealTheDeal.models.Employee;
import com.revature.sealTheDeal.models.User;
import com.revature.sealTheDeal.util.HibernateUtil;

public class EmployeeDAO {

	public boolean addEmployee(Employee employee) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.save(employee);
			transaction.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			HibernateUtil.closeSession();
			return false;
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
		} catch (HibernateException | IOException e) {
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
		} catch (HibernateException | IOException e) {
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

			Query query = session.createQuery(
					"update Employee set" + " username= :username," + " pass= :pass," + " first_name= :firstName,"
							+ " last_name= :lastName," + " user_email= :email," + " account_type=:accountype,"
							+ " is_account_taken=:isAcountTaken" + " where employee_id = :employeeID");
			query.setParameter("username", employee.getUsername());
			query.setParameter("pass", employee.getPass());
			query.setParameter("firstName", employee.getFirstName());
			query.setParameter("lastName", employee.getLastName());
			query.setParameter("email", employee.getUser_email());
			query.setParameter("accountype", employee.getAccountType());
			query.setParameter("isAcountTaken", employee.isAccountTaken());
			query.setParameter("employeeID", employee.getEmployeeId());
			query.executeUpdate();
			transaction.commit();
			HibernateUtil.closeSession();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			HibernateUtil.closeSession();
		}

	}

	@SuppressWarnings("unchecked")
	public void deleteByEmployeeID(String employeeID) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			String sql = "delete Employee where employee_id = '" + employeeID + "'";
			Query<Employee> query = session.createQuery(sql);
			query.executeUpdate();
			transaction.commit();
			HibernateUtil.closeSession();

		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			HibernateUtil.closeSession();
		}
	}

	@SuppressWarnings("unchecked")
	public Employee getEmployeeByEmployeeID(String employeeID) {
		try {
			Session session = HibernateUtil.getSession();
			String sql = "from Employee where employee_id = '" + employeeID + "'";
			Query<Employee> query = session.createQuery(sql);
			Employee results = query.getSingleResult();
			if (results != null) {
				HibernateUtil.closeSession();
				return results;
			} else {
				HibernateUtil.closeSession();
				return null;
			}
		} catch (NoResultException e) {
			HibernateUtil.closeSession();
			return null;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			HibernateUtil.closeSession();
			return null;
		}
	}

	public boolean addBooking(Booking booking) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.save(booking);
			transaction.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			HibernateUtil.closeSession();
			return false;
		}
	}

	public boolean getByServiceName(String serviceName) {
		try {
			Session session = HibernateUtil.getSession();
			Booking booking = session.get(Booking.class, serviceName);
			if(booking == null) {
				HibernateUtil.closeSession();
				return false;
			}else {
				HibernateUtil.closeSession();
				return true;
			}
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			HibernateUtil.closeSession();
			return true;
		}
	}
}