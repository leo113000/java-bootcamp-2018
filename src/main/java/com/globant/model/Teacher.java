package com.globant.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Getter
@Setter
@Entity("teachers")
public class Teacher{
	@Id
	@Property("_id")
	protected ObjectId id;
	@Property("first_name")
	private String firstName;
	@Property("last_name")
	private String lastName;
	@Property("birth_date")
	private String birthDate;
}
