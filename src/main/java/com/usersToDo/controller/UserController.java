package com.usersToDo.controller;

import com.usersToDo.model.UserItm;
import com.usersToDo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String getStrtd(Model model){
        Iterable<UserItm> users = userRepo.findAll();
        model.addAttribute("users", users);
        model.addAttribute("usr",new UserItm());
        return "user";
    }

    @GetMapping(value="/validate")
    public String validateUser(@ModelAttribute UserItm user){
        UserItm result = null;
        List<UserItm> uml= userRepo.findByUserName(user.getName());
        if(!CollectionUtils.isEmpty(uml)) {
            if (uml.get(0).getPass().equals(user.getPass())) {
                result = uml.get(0);
            }
        }
        return "redirect:/userHome/"+result.getUid();
    }
}
