package com.usersToDo.controller;

import com.usersToDo.model.ToDoItm;
import com.usersToDo.repo.ToDoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ToDoController {
    @Autowired
    private ToDoRepo toDoRepo;

    @GetMapping("/userHome/{userId}")
    public String getStrtd(@PathVariable String userId, Model model){
        ToDoItm toItm = new ToDoItm();
        toItm.setUid(userId);
        model.addAttribute("tsks",toItm);
        return "userhome";
    }

    @GetMapping("/viewTask/{userId}")
    public String viewTask(@PathVariable String userId, Model model){
        List<ToDoItm> todoList = new ArrayList<>();
        todoList = toDoRepo.findAllTasksByUser(userId);
        model.addAttribute("userTasks",todoList);
        ToDoItm toItm = new ToDoItm();
        toItm.setUid(userId);
        model.addAttribute("tsks",toItm);
        model.addAttribute("forChecked",new ToDoItm());
        return "viewtasks";
    }

    @PostMapping(value="/addToDo/{userId}")
    public String createToDo(@PathVariable String userId,  Model model){
        ToDoItm toDoItm = new ToDoItm();
        toDoItm.setUid(userId);
        model.addAttribute("addTask",toDoItm);
        return "addTasks";
    }
    @PostMapping(value="/save")
    public String save(@ModelAttribute ToDoItm todoItm){
        todoItm.setChecked(false);
        todoItm.setLastUpdtd();
        toDoRepo.save(todoItm);
        return "redirect:/viewTask/"+todoItm.getUid();
    }

    @PostMapping(value="/updateCompleted/{id}")
    public String update(@PathVariable String id){
        ToDoItm toDoItm= toDoRepo.findById(id).get();
        toDoItm.setLastUpdtd();
        toDoItm.setChecked(!toDoItm.isChecked());
        toDoRepo.save(toDoItm);
        return "redirect:/viewTask/"+toDoItm.getUid();
    }

    @RequestMapping("/delete/{userId}/{taskId}")
    public String delete(@PathVariable String userId, @PathVariable String taskId) {
        toDoRepo.deleteById(taskId);
        return "redirect:/viewTask/"+userId;
    }

    @RequestMapping("/edit/{userId}/{taskId}")
    public String edit(@PathVariable String userId, @PathVariable String taskId, Model model) {
        ToDoItm toDoItm = toDoRepo.getById(taskId);
        model.addAttribute("tasktbedited",toDoItm);
        return "editTask";
    }

}
