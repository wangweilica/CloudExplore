package com.sunsoft.study.pattern.template.callback;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementCallback {  
	
    Object doInStatement(PreparedStatement stmt, String sql) throws SQLException;  
}  