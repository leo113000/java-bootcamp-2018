
use high_school;
db.dropDatabase();
use high_school;
db.createCollection("teachers", {
   validator: {
      $jsonSchema: {
         bsonType: "object",
         required: [ "first_name", "last_name", "birth_date"],
         properties: {
            first_name: {
               bsonType: "string",
               description: "must be a string and is required"
            },
            last_name: {
               bsonType: "string",
               description: "must be a string and is required"
            },
            birth_date: {
               bsonType: "date"
            }
         }
      }
   }
});

db.createCollection('courses', {
    validator: {
        $jsonSchema : {
            bsonType: 'object',
            required: ['name', 'id_teacher', 'schedules'],
            properties: {
                'name' : {
                    bsonType: 'string',
                    description: 'This must be an String and is Required'
                },
                'id_teacher' : {
                    bsonType: 'objectId',
                    description: 'This must be an ObjectId referencing a Teacher'
                },
                'schedules' : {
                    bsonType: 'array',
                    description: 'This must be an array with every day of the course schedule',
                    items: {
                        bsonType: 'object',
                        required: ['day', 'from',"to"],
                        properties: {

                            'day' : {
                                enum: ['Monday','Tuesday','Wednesday','Thursday','Friday'],
                                description: 'This must be a day of the week'
                            },
                            'from' : {
                                bsonType: 'string',
                                description: 'This must be a day of the week'                                  
                            },
                            'to' : {
                                bsonType: 'string',
                                description: 'This must be a day of the week'                                  
                            }
                        }
                    }
                }
            }
        }
    }
});

db.createCollection("students", {
   validator: {
      $jsonSchema: {
         bsonType: "object",
         required: [ "first_name", "last_name","registration_number", "birth_date"],
         properties: {
            first_name: {
               bsonType: "string",
               description: "must be a string and is required"
            },
            last_name: {
               bsonType: "string",
               description: "must be a string and is required"
            },
            registration_number: {
               bsonType: "double",
               description: "must be a string and is required"
            },
            birth_date: {
               bsonType: "date"
            },
           	'qualifications' : {
                    bsonType: 'array',
                    description: 'This must be an array with every day of the course schedule',
                    items: {
                        bsonType: 'object',
                        required: ['course_id', 'notes',"status"],
                        properties: {
                            'course_id' : {
                                bsonType: 'objectId',
                    			description: 'This must be an ObjectId referencing a course'
                            },
                            'notes' : {
                                bsonType: 'array',
                                description: 'This must be a array of notes',
                                items: {
                                	bsonType: 'double'
                                }                                 
                            },
                            'status' : {
                                enum: ['Passed','Failed'],
                                description: 'This must be a status of the student in the course'                                  
                            }
                        }
                    }
                } 
         }
      }
   }
});

db.students.createIndex( { "registration_number":1 }, { unique: true } );

db.teachers.insertMany( [
      { first_name: "Pablo", last_name: "Fino", birth_date: new Date("2000-12-12") },
      { first_name: "German", last_name: "Gianotti", birth_date: new Date("2000-12-12")  },
      { first_name: "Silvina", last_name: "Calderon", birth_date: new Date("2000-12-12")  }]);

db.courses.insertMany([
	{name: "Base de datos", id_teacher: db.teachers.findOne({first_name: "Pablo"})._id,
		schedules:[{day: "Monday", from: "18:00" , to: "22:00"}]},
	
	{name: "Programacion", id_teacher: db.teachers.findOne({first_name: "Silvina"})._id,
	schedules:[{day: "Tuesday", from: "18:00" , to: "22:00"}]},

	{name: "Laboratorio", id_teacher: db.teachers.findOne({first_name: "German"})._id,
	schedules:[{day: "Wednesday", from: "18:00" , to: "22:00"}]}]);



db.students.insertMany( [
      { first_name: "Leo", last_name: "Vazquez", registration_number:1, birth_date: new Date("<2000-12-12>"),
      qualifications:
      [{course_id: db.courses.findOne({name: "Laboratorio"})._id, notes: [3,8,4], status:"Passed"}] },
      
      { first_name: "Nicolas", last_name: "Mozo", registration_number:2, birth_date: new Date("<2000-12-12>"),
      qualifications:
      [{course_id: db.courses.findOne({name: "Laboratorio"})._id, notes: [4,2,4], status:"Passed"}]  },
      
      { first_name: "David", last_name: "Peluffo", registration_number:3, birth_date: new Date("<2000-12-12>"),
      qualifications:
      [{course_id: db.courses.findOne({name: "Laboratorio"})._id, notes: [10,7,8], status:"Passed"}]  },
      
      { first_name: "Franco", last_name: "Lisotti", registration_number:4, birth_date: new Date("<2000-12-12>"),
      qualifications:
      [{course_id: db.courses.findOne({name: "Base de datos"})._id, notes: [4,4,4], status:"Passed"}]  },
      
      { first_name: "Sara", last_name: "Lavanchy", registration_number:5, birth_date: new Date("<2000-12-12>"),
      qualifications:
      [{course_id: db.courses.findOne({name: "Base de datos"})._id, notes: [9,9,9], status:"Passed"}]  },
      
      { first_name: "Marco Julian", last_name: "Torre", registration_number:6, birth_date: new Date("<2000-12-12>"),
      qualifications:
      [{course_id: db.courses.findOne({name: "Programacion"})._id, notes: [4,4,1], status:"Passed"}]  },
      
      { first_name: "Axel", last_name: "Fritz", registration_number:7, birth_date: new Date("<2000-12-12>"),
      qualifications:
      [{course_id: db.courses.findOne({name: "Programacion"})._id, notes: [7,7,7], status:"Passed"}]  },
      
      { first_name: "Ingacio", last_name: "Gioia", registration_number:8, birth_date: new Date("<2000-12-12>"),
      qualifications:
      [{course_id: db.courses.findOne({name: "Programacion"})._id, notes: [6,5,4], status:"Passed"}]  },
      
      { first_name: "Tomas", last_name: "Mastrolia", registration_number:9, birth_date: new Date("<2000-12-12>"),
      qualifications:
      [{course_id: db.courses.findOne({name: "Programacion"})._id, notes: [2,2,2], status:"Passed"}]  },
      
      { first_name: "Cosme", last_name: "Fulanito", registration_number:10, birth_date: new Date("<2000-12-12>"),
      qualifications:
      [{course_id: db.courses.findOne({name: "Programacion"})._id, notes: [10,10,10], status:"Passed"}]  }]);
