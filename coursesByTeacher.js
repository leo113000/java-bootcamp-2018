use high_school;

function coursesByTeacher(teacher_name){
	return db.courses.find({
		id_teacher : db.teachers.findOne({first_name:teacher_name})._id
		})
		.sort({name:1})
		.pretty();
}

coursesByTeacher("German");