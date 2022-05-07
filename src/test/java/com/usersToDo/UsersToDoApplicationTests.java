package com.usersToDo;

import com.usersToDo.model.ToDoItm;
import com.usersToDo.repo.ToDoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UsersToDoApplicationTests {

	@Autowired
	ToDoRepo toDoRepo;
	@Test
	void addTask() {
		savingItem("1","1","task1");
		assertNotNull(toDoRepo.findById("1").get());
	}



	@Test
	void fetchAll(){
		savingItem("1","1","task1");
		savingItem("2","1","task2");
		List<ToDoItm> todoList = toDoRepo.findAll();
		assertThat(todoList).size().isEqualTo(2);
	}

	@Test
	void deleteTask(){
		savingItem("1","1","task1");
		toDoRepo.deleteById("1");
		assertThat(toDoRepo.existsById("1")).isFalse();
	}

	@Test
	void markComplete(){
		savingItem("1","1","task1");
		ToDoItm toDoItm = toDoRepo.findById("1").get();
		toDoItm.setChecked(true);
		toDoRepo.save(toDoItm);
		ToDoItm toDoItm2 = toDoRepo.findById("1").get();
		assertThat(toDoItm2.isChecked());
	}

	@Test
	void updateTask(){
		savingItem("1","1","task1");
		ToDoItm toDoItm = toDoRepo.findById("1").get();
		String desc = "new description";
		toDoItm.setDescription(desc);
		toDoRepo.save(toDoItm);
		ToDoItm toDoItm2 = toDoRepo.findById("1").get();
		assertThat(toDoItm2.getDescription()).isEqualTo(desc);
	}

	@Test
	void fetchAllTask() {
		savingItem("1","1","task1");
		savingItem("2","2","task2");
		savingItem("3","1","task3");
		savingItem("4","3","task4");
		savingItem("5","1","task5");

		List<ToDoItm> todoList1 = toDoRepo.findAllTasksByUser("1");
		assertThat(todoList1).size().isEqualTo(3);
		List<ToDoItm> todoList2 = toDoRepo.findAllTasksByUser("2");
		assertThat(todoList2).size().isEqualTo(1);
		List<ToDoItm> todoList3 = toDoRepo.findAllTasksByUser("4");
		assertThat(todoList3).size().isEqualTo(0);
	}
	private void savingItem(String id, String userId, String desc) {
		ToDoItm toDoItm = new ToDoItm();
		toDoItm.setId(id);
		toDoItm.setUid(userId);
		toDoItm.setDescription(desc);
		toDoItm.setLastUpdtd();
		toDoRepo.save(toDoItm);
	}
}
