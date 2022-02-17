package com.revature.sealTheDeal.dao;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.sealTheDeal.models.User;
import com.revature.sealTheDeal.models.WeddingUser;
import com.revature.sealTheDeal.util.HibernateUtil;

public class UserDAO {

	public boolean getByUsername(String username) {
		try {
			Session session = HibernateUtil.getSession();
			User user = session.get(User.class, username);
			return true;
		} catch (HibernateException | IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	
}
