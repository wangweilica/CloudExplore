package com.sunsoft.study.pattern.template;

import java.util.List;

import org.junit.Test;

public class JunitTest {
	
	@SuppressWarnings("unchecked")
	@Test
	public void getUser() {
		try {
			String sql = "select * from t_user where mobile=15869139334";  
			JdbcTemplate jt = new JdbcTemplateUserImpl();  
			List<User> userList = (List<User>) jt.execute(sql);
			for(User user : userList) {
				System.out.println(user.getUserName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
}
