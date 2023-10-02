package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.dao.StudentDAOImpl;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
//			createStudent(studentDAO);
//			readStudent(studentDAO);
			getStudents(studentDAO);
//			findStudentByLastname(studentDAO);
//			updateStudent(studentDAO);
//			deleteStudent(studentDAO);
//			deleteAllStudents(studentDAO);
		};
	}

	// CREATE
	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating the student...");
		Student student = new Student("jordan", "Sinatra", "jordanSin@gmail.com");

		// save the student
		System.out.println("Saving the student...");
		studentDAO.save(student);

		// display id of the saved student
		System.out.println("Student id: " + student.getId());
	}
	// READ
	private void readStudent(StudentDAO studentDAO){
		int id = 12;
		Student student = studentDAO.findById(id);

		if(student != null){
			System.out.println(student);
		}
		else {
			System.out.println("Unable to find student with id: " +id);
		}
	}
	private void getStudents(StudentDAO studentDAO){
		List<Student> students = studentDAO.findAll();
		for(var student : students){
			System.out.println(student);
		}
	}
	private void findStudentByLastname(StudentDAO studentDAO){
		String lastName = "Sinatra";
		List<Student> students = studentDAO.findByLastName(lastName);
		for(var student : students){
			System.out.println(student);
		}
	}
	// UPDATE
	private void updateStudent(StudentDAO studentDAO){
		Student student = studentDAO.findById(1);
		student.setFirstname("Tristan");
		studentDAO.update(student);

		System.out.println("updated student: ");
		System.out.println(student);
	}
	// DELETE
	private void deleteStudent(StudentDAO studentDAO){
		int id = 5;
		Student student = studentDAO.delete(id);
		if(student == null){
			System.out.println("no student was found with id: " + id);
		}
		else{
			System.out.println("removed student: ");
			System.out.println(student);
		}
	}
	private void deleteAllStudents(StudentDAO studentDAO){
		System.out.println("Deleting " + studentDAO.deleteAll() + " records...");;
	}
}
