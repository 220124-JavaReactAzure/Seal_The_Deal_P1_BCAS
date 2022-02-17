package com.revature.sealTheDeal.dao;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;

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

	public boolean getByEmail(String user_email) {
		try {
			Session session = HibernateUtil.getSession();
			User user = session.get(User.class, user_email);
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

	
}
