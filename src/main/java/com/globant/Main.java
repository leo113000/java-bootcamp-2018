package com.globant;

import com.globant.model.Student;
import com.globant.persistence.StudentDAO;
import com.globant.persistence.MorphiaConnection;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;

public class Main {

	public static void main(String[] args) {
		MorphiaConnection mc = MorphiaConnection.getInstance();
		Datastore d = mc.getDatastore();

		StudentDAO studentDAO = new StudentDAO(d);

		Query<Student> query = d.createQuery(Student.class);
		query.and(
				query.criteria("qualifications.notes").not().lessThan(4)
		);

		QueryResults<Student> retrievedStudents =  studentDAO.find(query);

		System.out.println("Estudiantes aprobados");

		for (Student s : retrievedStudents.asList()){
			System.out.println(s.getFirstName());
		}



	}

}
