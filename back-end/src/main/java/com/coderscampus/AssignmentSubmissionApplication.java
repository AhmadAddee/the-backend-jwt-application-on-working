package com.coderscampus;

/**
 * Labb 3
 * */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ComponentScan({"com.beta.replyservice", "com.beta.ruleService"})
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
public class AssignmentSubmissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentSubmissionApplication.class, args);
	}
}
