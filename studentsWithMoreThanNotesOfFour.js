use high_school;


function studentsWithMoreThanNotesOfFour(course_name){
	return db.students.find({qualifications: 
		{$elemMatch: 
			{ course_id : db.courses.findOne({name: course_name})._id,
			  notes:{
			  	$not:{		  
			  		$lt: 4
			  	}
			  }
	        }
	    }
	}).pretty();
}

studentsWithMoreThanNotesOfFour("Programacion");



