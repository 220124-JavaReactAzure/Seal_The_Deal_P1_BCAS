package com.revature.sealTheDeal.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.sealTheDeal.models.User;
import com.revature.sealTheDeal.util.HibernateUtil;

public class UserDAO {

	public boolean getByUsername(String username) {
		try {
			Session session = HibernateUtil.getSession();
			User user = session.get(User.class, username);
			if(user == null) {
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

	@SuppressWarnings("unchecked")
	public boolean getByEmail(String user_email) {
		try {
			Session session = HibernateUtil.getSession();
			String sql = "from User where user_email = '" + user_email + "'";
			Query<User> query = session.createQuery(sql);
			int results = query.getFirstResult();
			System.out.println(results);
			if(results == 0) {
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
