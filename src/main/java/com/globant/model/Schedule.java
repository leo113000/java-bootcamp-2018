package com.globant.model;

import lombok.Getter;
import lombok.Setter;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;

@Getter
@Setter
@Embedded
public class Schedule {
	@Property
	private String day;
	@Property
	private String from;
	@Property
	private String to;
}
