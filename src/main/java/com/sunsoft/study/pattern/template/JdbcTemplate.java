package com.sunsoft.study.pattern.template;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.Statement;

public abstract class JdbcTemplate {

	private String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://rdsb3urqimf3ijno.mysql.rds.aliyuncs.com:3306/haochedai";
	private String username = "dbu_haochedai";
	private String password = "HqE4cWfrfUrCxNWY";

	// template method( public can be replace with private )
	public final Object execute(String sql) throws Exception {
		Connection con = null;
		Statement stmt = null;
		Object result = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
			stmt.execute(sql);
//			result = doInStatement(rs);// abstract method
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

	// implements in subclass
	protected abstract Object doInStatement(ResultSet rs);

}