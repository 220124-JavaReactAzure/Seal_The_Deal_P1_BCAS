package com.revature.sealTheDeal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

	@Entity
	@Table(name = "userinfo")
	@Inheritance(strategy = InheritanceType.JOINED)
	public class User {
		
		@Id
		private String username;
		
		@Column(name = "first_name") 
		private String firstName;
		
		@Column(name = "last_name")
		private String lastName;
		
		private String pass;
		
		@Column(unique = true)
		private String user_email;
		
		@Column(name = "account_type")
		private int accountType;
		
		
		public User() {
			super();
		}

		public User(String username, String firstName, String lastName, String pass, String user_email,
				int accountType) {
			super();
			this.username = username;
			this.firstName = firstName;
			this.lastName = lastName;
			this.pass = pass;
			this.user_email = user_email;
			this.accountType = accountType;
		}
		
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public int getAccountType() {
			return accountType;
		}
		public void setAccountType(int accountType) {
			this.accountType = accountType;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPass() {
			return pass;
		}
		public void setPass(String pass) {
			this.pass = pass;
		}
		public String getUser_email() {
			return user_email;
		}
		public void setUser_email(String user_email) {
			this.user_email = user_email;
		}
	
}