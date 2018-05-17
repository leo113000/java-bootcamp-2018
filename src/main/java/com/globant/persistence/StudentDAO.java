package com.globant.persistence;

import com.globant.model.Student;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

public class StudentDAO extends BasicDAO<Student,ObjectId> {
	public StudentDAO(Datastore ds) {
		super(ds);
	}
}
