package com.sunsoft.study.pattern.template;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplateUserImpl extends JdbcTemplate {  
  
    @Override  
    protected Object doInStatement(ResultSet rs) {  
        List<Integer> userList = new ArrayList<Integer>();
        try {  
            User user = null;  
            while (rs.next()) {  
//                user = new User();
//                user.setUserName(rs.getString("user_name"));
//                user.setPassword(rs.getString("password"));

                userList.add(rs.getInt("id"));
            }  
            return userList;  
        } catch (SQLException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
  
}  