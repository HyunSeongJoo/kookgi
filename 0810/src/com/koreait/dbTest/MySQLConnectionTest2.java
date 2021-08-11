package com.koreait.dbTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnectionTest2 {

	public static void main(String[] args) {
		
		Connection conn = DBUtil.getMySqlConnection();
		System.out.println("연결 성공: " + conn);
		DBUtil.close(conn);

	}

}

















