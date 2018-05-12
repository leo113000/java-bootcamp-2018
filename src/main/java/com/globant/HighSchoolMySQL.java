package com.globant;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HighSchoolMySQL {
	/**
	 * Static attribute of the same type as the class that will contain the instance of himself
	 */
	private static HighSchoolMySQL instance;
	private Connection connection;

	/**
	 * return the existence instance or a new one if not exists
	 *
	 * @return an instance
	 */
	public static HighSchoolMySQL getInstance() {
		return HighSchoolMySQL.instance == null ? new HighSchoolMySQL() : HighSchoolMySQL.instance;
	}

	/**
	 * The constructor is private to forbid the normal instantiation of the class
	 */
	private HighSchoolMySQL() {
		try {
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/high_school", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void timelineByTeacher(int teacherId) {

		try {
			String query = "call timeline_by_teacher(?)";
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, teacherId);
			statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
