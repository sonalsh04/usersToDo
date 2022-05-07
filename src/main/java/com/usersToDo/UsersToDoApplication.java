package com.usersToDo;

import com.usersToDo.model.UserItm;
import com.usersToDo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class UsersToDoApplication {

	@Autowired
	private UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(UsersToDoApplication.class, args);
	}

	@PostConstruct
	private void addingUsers(){
		UserItm user1 = new UserItm("test1","pass1");
		userRepo.save(user1);
		UserItm user2 = new UserItm("test2","pass2");
		userRepo.save(user2);
		UserItm user3 = new UserItm("test3","pass3");
		userRepo.save(user3);
		UserItm user4 = new UserItm("test4","pass4");
		userRepo.save(user4);

	}
}
