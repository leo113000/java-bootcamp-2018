package com.globant.model;

import lombok.Getter;
import lombok.Setter;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

import java.util.List;
@Getter
@Setter
@Embedded
public class Qualification  {
	//@Reference("course_id")
	private Course course;
	@Property
	private List<Integer> notes;
	@Property
	private String status;
}
