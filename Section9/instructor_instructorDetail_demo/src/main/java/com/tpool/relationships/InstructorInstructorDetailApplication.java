package com.tpool.relationships;

import com.tpool.relationships.entity.Instructor;
import com.tpool.relationships.entity.InstructorDetail;
import com.tpool.relationships.repository.AppRepo;
import com.tpool.relationships.repository.InstructorDetailRepository;
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
	public CommandLineRunner commandLineRunner(AppRepo repo, InstructorDetailRepository instructorDetailRepo){
		return runner -> {
			System.out.println("==============================");
//			insertInstructor(repo);
			getAllInstructors(repo);
//			findInstructor(repo);
// 			removeInstructor(repo);
//			findInstructorDetail_andInstructor(instructorDetailRepo);
//			deleteInstructorDetail(instructorDetailRepo);
			System.out.println("==============================");
		};
	}



	// methods
	public void insertInstructor(AppRepo repo){
		// get the first name
		System.out.print("First name: ");
		String firstName = scanner.nextLine();

		// get the last name
		System.out.print("Last name: ");
		String lastName = scanner.nextLine();

		// email
		String email = String.format("%s.%s@gmail.com", firstName, lastName);

		// get the youtube channel
		System.out.print("youtube channel: ");
		String yt_channel = scanner.nextLine();

		// get the hobby
		System.out.print("Hobby: ");
		String hobby = scanner.nextLine();

		// create both objects
		Instructor instructor = new Instructor(firstName, lastName, email);
		InstructorDetail detail = new InstructorDetail(yt_channel, hobby);

		// set the instructor detail on the instructor
		instructor.setInstructorDetailId(detail);

		// persist instructor, cascades to instructor detail
		repo.save(instructor);
	}

	public void getAllInstructors(AppRepo repo){
		var list = repo.findAll();
		for(var inst : list){
			System.out.println(inst);
		}
	}

	public void findInstructor(AppRepo repo) {
		// get the instructor id from the user
		System.out.print("Instructor id: ");
		Integer instructor_id = scanner.nextInt();

		// get the instructor
		Optional<Instructor> instructor = repo.findById(instructor_id);

		// not found
		if(instructor.isEmpty()){
			System.out.println("Instructor with id: " + instructor_id + " was not found..");
		}
		// found
		else{
			System.out.println(instructor.get());
		}

	}

	private void removeInstructor(AppRepo repo) {
		// get the instructor id
		System.out.print("Delete instructor id: ");
		Integer instructor_id = scanner.nextInt();

		// attempt to delete the instructor
		repo.deleteById(instructor_id);
	}

	private void findInstructorDetail_andInstructor(InstructorDetailRepository instructorDetailRepo) {
		// get the id from the user
		System.out.print("Instructor detail id: ");
		Integer instructor_detail_id = scanner.nextInt();

		// get the optional from the repo
		Optional<InstructorDetail> instructorDetail = instructorDetailRepo.findById(instructor_detail_id);

		// not found
		if(instructorDetail.isEmpty()) {
			System.out.println("no instructor detail with id " +instructor_detail_id + " was found...");
		}
		// was found
		else{
			// print out the instructor information
			System.out.println("Instructor of instructor detail id: " +instructor_detail_id + " ==> " +instructorDetail.get().getInstructor());
		}

	}

	private void deleteInstructorDetail(InstructorDetailRepository instructorDetailRepo) {
		// get the input from the user
		System.out.print("instructor detail to delete: ");
		Integer instructor_detail_id = scanner.nextInt();

		// remove the instructor detail, keep the instructor
		instructorDetailRepo.RemoveDetailKeepInstructor(instructor_detail_id);
	}
}
