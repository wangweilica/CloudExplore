package com.sunsoft.study.pattern.template.callback;

import java.util.List;

import com.sunsoft.study.pattern.template.User;

public class Test {
	@SuppressWarnings("unchecked")
	private static void getUser(String mobile) {
		try {
			String sql = "select * from t_user where mobile = " + mobile;  
			UserService service = new UserService();  
			List<User> userList = (List<User>) service.queryUser(sql);
			for(User user : userList) {
				System.out.println(user.getUserName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	
	public static void main(String[] args) {
		getUser("15869139334");
	}
}
