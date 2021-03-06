package com.revature.sealTheDeal.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.sealTheDeal.models.Booking;
import com.revature.sealTheDeal.models.Employee;
import com.revature.sealTheDeal.util.HibernateUtil;
import com.revature.sealTheDeal.util.datasource.ConnectionFactory;

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

	public void addWeddingDay(String weddingDay) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("alter table booking add "+ weddingDay + " bit default 0");
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* Ignored */}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) { /* Ignored */}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) { /* Ignored */}
			}
		}
	}
	
	
	public List<Booking> getByService(int serviceType, String weddingDay) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Booking> bookingList = new ArrayList<Booking>();
	
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("select * from booking where service_type = "+ serviceType);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Booking service = new Booking(rs.getString("service_name"), rs.getInt("service_type"), rs.getDouble("price"),  rs.getBoolean(weddingDay));
				if(service.getServiceName() != null) {
					bookingList.add(service);
				}
			}
			return bookingList;
		} catch(SQLException e) {
			e.printStackTrace();
			return bookingList;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* Ignored */}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) { /* Ignored */}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) { /* Ignored */}
			}
		}
	}

	public Booking getBookedService(String bookedServiceName, String weddingDay) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Booking bookedService = new Booking();
	
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("select * from booking where service_name = '"+ bookedServiceName + "'");
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				bookedService = new Booking(rs.getString("service_name"), rs.getInt("service_type"), rs.getDouble("price"),  rs.getBoolean(weddingDay));
			}
			return bookedService;
		} catch(SQLException e) {
			e.printStackTrace();
			return bookedService;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* Ignored */}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) { /* Ignored */}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) { /* Ignored */}
			}
		}
	}

	public void updateBooking(Booking updateService, String weddingDay) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			String sql = ("update booking set "+ weddingDay +" = '" + updateService.isBooked() + "' where service_name = '"+ updateService.getServiceName() + "'");
			ps = conn.prepareStatement(sql);
			ps.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* Ignored */}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) { /* Ignored */}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) { /* Ignored */}
			}
		}
		
	}
}