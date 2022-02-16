package com.revature.sealTheDeal.dao;


import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.mapping.List;
import org.hibernate.query.Query;

import com.revature.sealTheDeal.models.Employee;
import com.revature.sealTheDeal.models.Guest;
import com.revature.sealTheDeal.util.HibernateUtil;

public class GuestDAO {

	public boolean addGuest(Guest guest) {
		try {
			Session session = HibernateUtil.getSession();
			session.save(guest);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public List<Guest> getAllGuests() {
		try {
			Session session = HibernateUtil.getSession();
			List<Guest> guest = session.createQuery("FROM userinfo").list();
			return guest;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public Guest getGuestByUsername(String username) {
		try {
			Session session = HibernateUtil.getSession();
			Guest guest = session.get(Guest.class, username);
			return guest;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public void updateGuestWithSessionMethod(Guest guest) {
		try {
			Session session = HibernateUtil.getSession();
			// Updates and Deletes always start with a transaction and end with a commit
			Transaction transaction = session.beginTransaction();
			session.merge(guest);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

	}

	// Not truly implemented
	public void updateGuestWithHQL(Guest guest) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();

			Query query = session.createQuery("update userinfo set username= :username," + " pass= pass,"
					+ " email= :email," + " first_name= :firstName," + " last_name= :lastName,"
					+ " accounttype=:accountype where accounttypes = ???");
			query.setParameter("username", guest.getUsername());
			query.executeUpdate();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

	}

	public void deleteGuest(int id) {
	}
}
