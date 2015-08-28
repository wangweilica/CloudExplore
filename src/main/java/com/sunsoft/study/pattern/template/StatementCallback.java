package com.sunsoft.study.pattern.template;

import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public interface StatementCallback {  
    Object doInStatement(Statement stmt) throws SQLException;  
}  