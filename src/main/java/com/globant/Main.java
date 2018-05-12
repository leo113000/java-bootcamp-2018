package com.globant;

public class Main {

	public static void main(String[] args) {
		HighSchoolMySQL HSM = HighSchoolMySQL.getInstance();
		HSM.timelineByTeacher(1);
		HSM.timelineByTeacher(2);
		HSM.timelineByTeacher(3);
	}

}
