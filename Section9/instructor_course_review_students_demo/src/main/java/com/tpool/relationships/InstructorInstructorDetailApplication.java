package com.tpool.relationships;

import com.tpool.relationships.entity.*;
import com.tpool.relationships.service.InstructorServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class InstructorInstructorDetailApplication {
	Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(InstructorInstructorDetailApplication.class, args);
	}

	// CommandLineRunner is from the Springboot framework
	// this snippet of code is executed after the spring beans have been loaded
	@Bean
	public CommandLineRunner commandLineRunner(InstructorServiceImpl instructorService){
		return runner -> {
			System.out.println("==============================");
			/** old methods **/
//			addInstructor(instructorService);
//			removeInstructor(instructorService);
//			addCourse(instructorService);
//			updateCourse(instructorService);
//			updateInstructorDetail(instructorService);
//			getCourses(instructorService);
//			removeCourse(instructorService);
//			updateInstructor(instructorService);
//			getInstructor(instructorService);

			/** One to many **/
//			addReview(instructorService);
//			getReviews(instructorService);
//			removeReview(instructorService);

			/** many to many **/
			// creates a new student and adds it to an existing course
			addNewStudent(instructorService);

			// adds an existing student to an existing course
//			addStudentToCourse(instructorService);

			System.out.println("==============================");
		};
	}

	private void addStudentToCourse(InstructorServiceImpl instructorService) {
		System.out.print("student id: ");
		int studentId = scanner.nextInt(); scanner.nextLine();

		Optional<Student> student = instructorService.getStudentRepository().findById(studentId);
		if(student.isEmpty()) {
			System.out.println("unable to find student with id: " +studentId);
			return;
		}

		System.out.print("course id: ");
		int courseId = scanner.nextInt(); scanner.nextLine();

		Course course = instructorService.getCourseRepository().findCoursesLoadStudents(courseId);

		if(course == null) {
			System.out.println("unable to find course with id: " +courseId);
			return;
		}

		// add the student to the course
		course.addStudent(student.get());

		// persist
		instructorService.getCourseRepository().save(course);
	}

	private void addNewStudent(InstructorServiceImpl instructorService) {
		// get the course id to add the student to
		System.out.print("course id: ");
		int courseId = scanner.nextInt();scanner.nextLine();

		// get the course from the db
		Course course = instructorService.getCourseRepository().findCoursesLoadStudents(courseId);

		if(course == null) {
			System.out.println("unable to find course with id: " + courseId);
		}
		else {
			System.out.print("first name: ");
			String firstName = scanner.nextLine();
			System.out.print("last name: ");
			String lastName = scanner.nextLine();
			String email = "%s.%s@gmail.com".formatted(firstName, lastName);
			Student student = new Student(firstName, lastName, email);

			// add student
			course.addStudent(student);

			instructorService.getCourseRepository().save(course);
		}

	}


	private void removeReview(InstructorServiceImpl instructorService) {
		// get the review id from the user
		System.out.print("review id: ");
		int reviewId = scanner.nextInt();scanner.nextLine();

		// get the review from the db
		Optional<Review> review = instructorService.getReviewRepository().findById(reviewId);

		// unable to find review
		if(review.isEmpty()) {
			System.out.println("unable to find review with id: " +reviewId);
		}
		else {
			System.out.println("deleting review with comment: " + review.get().getComment());
			instructorService.getReviewRepository().delete(review.get());
		}

	}

	private void getReviews(InstructorServiceImpl instructorService) {
		// gets the input from the user
		System.out.print("course id: ");
		int courseId = scanner.nextInt();scanner.nextLine();

		// gets the course and load the reviews the course
		Course course = instructorService.getCourseRepository().findCourseLoadReviews(courseId);

		if(course == null) {
			System.out.println("unable to find course with id " +courseId);
		}
		else {
			for(var c : course.getReviews()) {
				System.out.println(c);
			}
		}
	}

	private void addReview(InstructorServiceImpl instructorService) {
		// get the course id from the user
		System.out.print("course id: ");
		int courseId = scanner.nextInt();scanner.nextLine();

		// get the course and load the reviews
		Course course = instructorService.getCourseRepository().findCourseLoadReviews(courseId);

		// unable to find course
		if(course == null) {
			System.out.println("unable to find course with id: " +courseId);
		}
		else {
			// get the comment from the user
			System.out.print("comment: ");
			String comment = scanner.nextLine();

			// create the review
			Review review = new Review(comment);

			// add the review to the course
			course.addReview(review);

			// save the changes
			instructorService.getCourseRepository().save(course);
		}
	}

	private void getInstructor(InstructorServiceImpl instructorService) {
		System.out.print("instructor id: ");
		int instructorId = scanner.nextInt();scanner.nextLine();

		Instructor instructor = instructorService.getInstructorRepository().findInstructorByIdJoinFetch(instructorId);

		if(instructor == null) {
			System.out.printf("Unable to find instructor with id %d%n", instructorId);
		}
		else {
			System.out.println("==================");
			System.out.println(instructor);
			for(var course : instructor.getCourses()) {
				System.out.println(course);
			}
			System.out.println("==================");
		}
	}

	private void updateInstructorDetail(InstructorServiceImpl instructorService) {
		System.out.print("instructor id: ");
		int instructorId = scanner.nextInt(); scanner.nextLine();

		Instructor instructor = instructorService.getInstructorRepository().findInstructorByIdJoinFetch(instructorId);

		if(instructor == null) {
			System.out.println("Instructor with id: %d not found...".formatted(instructorId));
		}
		else{
			// changing the hobby on the instructor object
			System.out.print("hobby: ");
			String hobby = scanner.nextLine();

			if(instructor.getInstructorDetail() == null) {
				instructor.setInstructorDetail(new InstructorDetail("https://youtube.com/%sjkjkj%s".formatted(instructor.getFirstName(), instructor.getLastName()), hobby));
			}
			else {
				instructor.getInstructorDetail().setHobby(hobby);
			}

			// saving the hobby
			instructorService.getInstructorRepository().save(instructor);
		}
	}

	private void removeInstructor(InstructorServiceImpl instructorService) {
		System.out.print("instructor id: ");
		int instructorId = scanner.nextInt();

		// get the instructor and all of the courses
		Instructor instructor = instructorService.getInstructorRepository().findInstructorByIdJoinFetch(instructorId);

		if(instructor == null) {
			System.out.println("instructor with id: " +instructorId +" not found...");
		}
		else {
			instructorService.getInstructorRepository().deleteById(instructorId);
			System.out.println("done");
		}
	}

	private void updateCourse(InstructorServiceImpl instructorService) {
		System.out.print("course id: ");
		int courseId = scanner.nextInt();

		Optional<Course> course = instructorService.getCourseRepository().findById(courseId);
		if(course.isEmpty()) {
			System.out.println("unable to find course with id: " +courseId);
		}
		else{
			System.out.println("old course title: " +course.get().getTitle());
			System.out.print("new course title: ");
			String newCourseTitle = scanner.next();

			course.get().setTitle(newCourseTitle);
			instructorService.getCourseRepository().save(course.get());

			System.out.println("=== updated course ===");
			System.out.println(course.get());
			System.out.println("====================");
		}

	}

	private void updateInstructor(InstructorServiceImpl instructorService) {
		System.out.print("instructor id: ");
		int instructorId = scanner.nextInt();

		Instructor instructor = instructorService.getInstructorRepository().findInstructorByIdJoinFetch(instructorId);
		if(instructor == null){
			System.out.println("unable to find instructor with id: " +instructorId);
		}
		else{
			System.out.print("change instructor first name: ");
			String firstName = scanner.next();
			instructor.setFirstName(firstName);

			instructorService.getInstructorRepository().save(instructor);
			System.out.println("==== instructor after update ======");
			System.out.println(instructor);
			System.out.println("==============================");
		}
	}

	private void removeCourse(InstructorServiceImpl instructorService) {
		// get the input from the user
		System.out.print("course id: ");
		int course_id = scanner.nextInt();

		// get the course from the db
		Optional<Course> course = instructorService.getCourseRepository().findById(course_id);

		// course not found
		if(course.isEmpty()) {
			System.out.println("course with id: " +course_id +" was not found...");
		}
		else{
			int instructor_id = course.get().getInstructor().getId();

			// remove the course from the course table
			instructorService.getCourseRepository().delete(course.get());

			// print out the courses after the delete
			Instructor instructor = instructorService.getInstructorRepository().findInstructorByIdJoinFetch(instructor_id);
			System.out.println(instructor.getCourses());
		}
	}

	public void addInstructor(InstructorServiceImpl instructorService) {
		// first name
		System.out.print("first name: ");
		String firstName = scanner.nextLine();

		// last name
		System.out.print("last name: ");
		String lastName = scanner.nextLine();

		// email
		String email = String.format("%s.%s@gmail.com", firstName, lastName);

		// youtube channel
		String youtubeChannel = String.format("https://youtube.com/%sakjflkajf%s", firstName, lastName);

		// hobby
		System.out.print("hobby: ");
		String hobby = scanner.nextLine();

		// create the instructor detail
		InstructorDetail instructorDetail = new InstructorDetail(youtubeChannel, hobby);

		// create the instructor
		Instructor instructor = new Instructor(firstName, lastName, email);
		instructor.setInstructorDetailId(instructorDetail);

		// save the instructor
		instructorService.getInstructorRepository().save(instructor);
	}

	public void addCourse(InstructorServiceImpl instructorService) {
		// get instructor from the user
		System.out.print("Instructor id: ");
		int instructor_id = scanner.nextInt();

		// find the instructor
		Instructor instructor = instructorService.getInstructorRepository().findInstructorByIdJoinFetch(instructor_id);

		// no instructor found
		if(instructor == null) {
			System.out.println("no instructor found with id: " + instructor_id);
		}
		else {
			// course title
			System.out.print("Course title: ");
			String title = scanner.next();

			// create the course
			Course course = new Course(title, instructor);

			// add the course to the instructor and save
			instructor.addCourse(course);

			// save the instructor
			instructorService.getInstructorRepository().save(instructor);
		}
	}

	public void getCourses(InstructorServiceImpl instructorService) {
		// get the instructor id from the user
		System.out.print("instructor id: ");
		int instructor_id = scanner.nextInt();


		/** Method 1 of loading lazy data (NOT RECOMMENDED) **/
//		// get the instructor from the db
//		Optional<Instructor> instructor = instructorService.getInstructorRepository().findById(instructor_id);
//
//		// instructor not found
//		if(instructor.isEmpty()) {
//			System.out.println("no instructor with id: " + instructor_id + " found...");
//		}
//		// instructor found
//		else{
//			System.out.println("Instructor: " + instructor.get());
//			System.out.println("=========== COURSES ===========");
//			for(var course : instructor.get().getCourses()) {
//				System.out.println(course);
//			}
//			System.out.println("=======================");
//		}

		/** Method 2 Join fetch loading lazy data (RECOMMENDED)**/
		// get the instructor
		Instructor instructor = instructorService.getInstructorRepository().findInstructorByIdJoinFetch(instructor_id);

		if(instructor == null){
			System.out.println("no instructor with id: " + instructor_id + " was found....");
		}
		else{
			System.out.println(instructor.getInstructorDetailId());
			for(var course : instructor.getCourses()) {
				System.out.println(course);
			}
		}

	}
}
