package com.tpool.aopdemo;

import com.tpool.aopdemo.dao.AccountDAO;
import com.tpool.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AppdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		return runner -> {
//			demoTheBeforeAdvice(accountDAO, membershipDAO);
//			demoAfterThrowing(membershipDAO);
//			demoAfter(membershipDAO);
			aroundDemo(membershipDAO);
		};
	}

	private void aroundDemo(MembershipDAO membershipDAO) {
		System.out.println("main: before execution");
		membershipDAO.getAccount();
		System.out.println("main: after execution");
	}

	private void demoAfter(MembershipDAO membershipDAO) {
		membershipDAO.getAccount();
	}

	private void demoAfterThrowing(MembershipDAO membershipDAO) {
		try {
			membershipDAO.simulateException();
		}
		catch (Throwable throwable) {
			System.out.println("heres the message ...");
			System.out.println(throwable.getMessage());
		}
	}

	private void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
//		accountDAO.addAccount();
//
//		System.out.println();
//
//		membershipDAO.addAccount("Hi");
//
//		System.out.println();
//
//		membershipDAO.getAccount();
//
//		System.out.println();
//
//		membershipDAO.setAccount();

//		System.out.println();

//		List<String> list = membershipDAO.RetrieveMemberships();

//		System.out.println(list);
	}

}
