package com.revature.sealTheDeal.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.sealTheDeal.models.Employee;
import com.revature.sealTheDeal.models.Guest;
import com.revature.sealTheDeal.models.WeddingUser;
import com.revature.sealTheDeal.util.HibernateUtil;

public class WeddingUserDAO {

	public boolean addWeddingUser(WeddingUser weddingUser) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();
			session.save(weddingUser);
			transaction.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			HibernateUtil.closeSession();
			return false;
		}
	}

	public List<WeddingUser> getAllWeddingUser() {
		try {
			Session session = HibernateUtil.getSession();
			List<WeddingUser> weddingUser = session.createQuery("FROM userinfo").list();
			return weddingUser;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public WeddingUser getWeddingUserByUsername(String username) {
		try {
			Session session = HibernateUtil.getSession();
			WeddingUser weddingUser = session.get(WeddingUser.class, username);
			return weddingUser;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public void updateWeddingUserWithSessionMethod(WeddingUser weddingUser) {
		try {
			Session session = HibernateUtil.getSession();
			// Updates and Deletes always start with a transaction and end with a commit
			Transaction transaction = session.beginTransaction();
			session.merge(weddingUser);
			transaction.commit();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

	}

	// Not truly implemented
	public void updateWeddingUserWithHQL(WeddingUser weddingUser) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction transaction = session.beginTransaction();

			Query query = session.createQuery("update userinfo set username= :username," + " pass= pass,"
					+ " email= :email," + " first_name= :firstName," + " last_name= :lastName,"
					+ " accounttype=:accountype where accounttypes = ???");
			query.setParameter("email", weddingUser.getUser_email());
			query.executeUpdate();
			transaction.commit();
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

	}

	public void deleteWeddingUser(int id) {
	}

	@SuppressWarnings("unchecked")
	public WeddingUser getByWeddingName(String weddingName) {
		try {
			Session session = HibernateUtil.getSession();
			String sql = "from WeddingUser where wedding_party_name = '" + weddingName + "'";
			Query<WeddingUser> query = session.createQuery(sql);
			WeddingUser results = query.getSingleResult();
			if (results != null) {
				HibernateUtil.closeSession();
				return results;
			} else {
				HibernateUtil.closeSession();
				return results;
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
	
	@SuppressWarnings("unchecked")
	public boolean verifyByWeddingName(String weddingName) {
		try {
			Session session = HibernateUtil.getSession();
			String sql = "from WeddingUser where wedding_party_name = '" + weddingName + "'";
			Query<WeddingUser> query = session.createQuery(sql);
			WeddingUser results = query.getSingleResult();
			if (results != null) {
				HibernateUtil.closeSession();
				return true;
			} else {
				HibernateUtil.closeSession();
				return false;
			}
		} catch (NoResultException e) {
			HibernateUtil.closeSession();
			return false;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			HibernateUtil.closeSession();
			return false;
		}

	}

}
