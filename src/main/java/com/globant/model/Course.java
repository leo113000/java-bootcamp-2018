package com.globant.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.List;
@Getter
@Setter
@Entity ("courses")
public class Course {
	@Id
	@Property("_id")
	protected ObjectId id;
	@Property
	private String name;
	@Reference
	private Teacher teacher;
	@Embedded("schedules")
	private List<Schedule> schedules;
}
