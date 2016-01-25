package com.sunsoft.study.pattern.template.callback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTemplate {

	private String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://192.168.11.165/cloudhealth?rewriteBatchedStatements=true";
	private String username = "root";
	private String password = "123456";

	// 模板方法
	private final Object execute(StatementCallback action, String sql, boolean openTransation) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		Object result = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			if(openTransation) con.setAutoCommit(false);
			stmt = con.prepareStatement(sql);
			result = action.doInStatement(stmt, sql);// 抽象方法
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (!con.isClosed()) {
					try {
						if(openTransation) {
							con.commit();
							con.setAutoCommit(true);
						}
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return result;
	}
	
	// 查询
	public Object query(StatementCallback stmt, String sql, boolean openTransation) throws SQLException{  
	    return execute(stmt, sql, openTransation);  
	}  
	
	// 更新
	public boolean update(StatementCallback stmt, String sql, boolean openTransation) throws SQLException{  
	    return (boolean)execute(stmt, sql, openTransation);  
	} 
		
	// 批量更新
	public int[] updateBatch(StatementCallback stmt, String sql, boolean openTransation) throws SQLException{  
	    return (int[])execute(stmt, sql, openTransation);  
	} 
}