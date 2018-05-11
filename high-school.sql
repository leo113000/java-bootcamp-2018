
CREATE DATABASE IF NOT EXISTS high_school;

use high_school;



-- test zone --

--CALL list_by_course("Base de Datos");
--CALL approval_status_percentage;
--CALL timeline_by_teacher(3);
-- test zone --
/*

CREATE TABLE IF NOT EXISTS teachers
	(id INT AUTO_INCREMENT, first_name CHAR(30), last_name CHAR(30), date_of_birth DATE,
		PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS students
	(id INT AUTO_INCREMENT, first_name CHAR(30), last_name CHAR(30), registration_number INT UNIQUE, date_of_birth DATE,
		PRIMARY KEY(id));

CREATE TABLE IF NOT EXISTS class_days
	(id INT AUTO_INCREMENT, day CHAR(30),
		PRIMARY KEY(id));

CREATE TABLE IF NOT EXISTS schedules
	(id INT AUTO_INCREMENT, beginning TIME, finale TIME, fk_class_day_id INT,
		PRIMARY KEY(id),
		FOREIGN KEY(fk_class_day_id) REFERENCES class_days(id));

CREATE TABLE IF NOT EXISTS courses
	(id INT AUTO_INCREMENT,name CHAR(30), weekly_hours INT, fk_teacher_id INT,
		PRIMARY KEY(id),
		FOREIGN KEY(fk_teacher_id) REFERENCES teachers(id));

CREATE TABLE IF NOT EXISTS courses_x_schedule
	(pfk_course_id INT, pfk_schedule_id INT,
		PRIMARY KEY(pfk_course_id,pfk_schedule_id),
		FOREIGN KEY(pfk_course_id) REFERENCES courses(id),
		FOREIGN KEY(pfk_schedule_id) REFERENCES schedules(id));

CREATE TABLE IF NOT EXISTS approval_status
	(id INT AUTO_INCREMENT, label VARCHAR(30),
		PRIMARY KEY(id));

CREATE TABLE IF NOT EXISTS courses_x_students
	(id INT AUTO_INCREMENT, fk_course_id INT, fk_student_id INT, fk_approval_status_id INT,
		PRIMARY KEY(id),
		FOREIGN KEY(fk_course_id) REFERENCES courses(id),
		FOREIGN KEY(fk_student_id) REFERENCES students(id),
		FOREIGN KEY(fk_approval_status_id) REFERENCES approval_status(id));

CREATE TABLE IF NOT EXISTS marks
	(id INT AUTO_INCREMENT, mark INT, school_year INT, fk_courses_x_students_id INT,
		PRIMARY KEY(id),
		FOREIGN KEY(fk_courses_x_students_id) REFERENCES courses_x_students(id));

INSERT INTO class_days (day) VALUES
	("Monday"),
	("Tuesday"),
	("Wednesday"),
	("Thursday"),
	("Friday");

INSERT INTO approval_status (label) VALUES ("In progress"),("Passed"), ("Failed");

INSERT INTO teachers (first_name, last_name, date_of_birth) VALUES
	("Pablo","Fino","12-12-2000"),
	("German","Gianotti","12-12-2000"),
	("Silvina","Calderon","12-12-2000");

INSERT INTO students (first_name, last_name, registration_number, date_of_birth) VALUES
	("Nicolas","Mozo",10101, "03-03-1996"),
	("David","Peluffo",41271, "03-03-1996"),
	("Leo","Vazquez",21333, "03-03-1996"),
	("Sara","Lavanchy",25458, "03-03-1996"),
	("Franco","Lisotti",65474, "03-03-1996"),
	("Marco Julian","Torre",78542, "03-03-1996"),
	("Axel","Fritz",34579, "03-03-1996"),
	("Ignacio","Gioia",66778, "03-03-1996"),
	("Tomas","Mastrolia",88754, "03-03-1996"),
	("Cosme","Fulanito",99914, "03-03-1996");

INSERT INTO schedules (beginning, finale, fk_class_day_id) VALUES
	("8:00","10:00",1),
	("10:00","12:00",1),
	("8:00","10:00",2),
	("10:00","12:00",2),
	("8:00","10:00",3),
	("10:00","12:00",3),
	("8:00","10:00",4),
	("10:00","12:00",4),
	("8:00","10:00",5),
	("10:00","12:00",5);


INSERT INTO courses (name, weekly_hours, fk_teacher_id) VALUES
	("Base de datos",4,1),
	("Laboratorio",4,2),
	("Programacion",4,3);

INSERT INTO courses_x_students (fk_course_id, fk_student_id,fk_approval_status_id) VALUES
	(2,1,2),
	(2,2,2),
	(2,3,2),
	(1,4,2),
	(1,5,2),
	(3,6,3),
	(3,7,3),
	(3,8,3),
	(3,9,3),
	(1,10,3);

INSERT INTO courses_x_schedule (pfk_course_id, pfk_schedule_id) VALUES
	(1,1),
	(1,2),
	(2,3),
	(2,4),
	(3,5),
	(3,6);


DROP PROCEDURE IF EXISTS list_by_course;

DELIMITER //

CREATE PROCEDURE list_by_course(_course_name VARCHAR(30))
BEGIN
	
	DECLARE _teacher_id INT;
	DECLARE _course_id INT;
	SELECT fk_teacher_id, id INTO _teacher_id, _course_id
		FROM courses
		WHERE name = _course_name;
	IF (_course_id) THEN
	SELECT _course_name AS "course";
	
	SELECT CONCAT(last_name," ",first_name) AS teacher
		FROM teachers 
		WHERE id = _teacher_id;

	SELECT CONCAT(s.last_name," ",s.first_name) AS "students"
		FROM students s
		INNER JOIN courses_x_students cs ON cs.fk_student_id = s.id
		WHERE cs.fk_course_id = _course_id
		ORDER BY s.last_name,s.first_name;
	ELSE
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "No existe la carrera";
	END IF;
END
//
DELIMITER ;


DROP PROCEDURE IF EXISTS approval_status_percentage;

DELIMITER //

CREATE PROCEDURE approval_status_percentage()
BEGIN
	
	DECLARE _approved INT DEFAULT 0;
	DECLARE _failed INT DEFAULT 0;
	DECLARE _total INT DEFAULT 0;

	SET _approved = (SELECT COUNT(cs.id)
		FROM courses_x_students cs
		INNER JOIN approval_status aps ON aps.id = cs.fk_approval_status_id
		WHERE aps.label = "Passed");
	SET _failed = (SELECT COUNT(cs.id)
		FROM courses_x_students cs
		INNER JOIN approval_status aps ON aps.id = cs.fk_approval_status_id
		WHERE aps.label = "Failed");
	SET _total = (SELECT COUNT(id)
		FROM courses_x_students);

	SELECT (_approved * 100/_total) as "Passed", 
		(_failed * 100/_total) as "Failed";

END
//
DELIMITER ;


DROP PROCEDURE IF EXISTS timeline_by_teacher;

DELIMITER //

CREATE PROCEDURE timeline_by_teacher(_teacher_id VARCHAR(30))
BEGIN
	
	SELECT CONCAT(last_name," ",first_name) AS teacher
		FROM teachers 
		WHERE id = _teacher_id;

	SELECT cd.day,s.beginning,s.finale,c.name
		FROM courses_x_schedule cs
		INNER JOIN schedules s ON cs.pfk_schedule_id = s.id
		INNER JOIN courses c ON cs.pfk_course_id = c.id
		INNER JOIN class_days cd ON s.fk_class_day_id = cd.id
		WHERE c.fk_teacher_id = _teacher_id
		ORDER BY cd.day,s.beginning;
END
//
DELIMITER ;
*/

CREATE UNIQUE INDEX IF NOT EXISTS IDX_students
	ON students (last_name,first_name) USING BTREE; 

CREATE INDEX IF NOT EXISTS IDX_class_days
	ON class_days (day) USING BTREE; 
