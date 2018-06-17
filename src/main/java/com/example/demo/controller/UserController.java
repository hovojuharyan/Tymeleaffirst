package com.example.demo.controller;

import com.example.demo.model.Project;
import com.example.demo.model.ProjectStatus;
import com.example.demo.model.User;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/myPage")
    public String myPage(ModelMap map, @AuthenticationPrincipal UserDetails userDetails){
        map.addAttribute("user0",new User());
        map.addAttribute("myProjects",
                projectRepository.findAllByUsers(userRepository.findOneByEmail(userDetails.getUsername())));
        map.addAttribute("myself",userRepository.findOneByEmail(userDetails.getUsername()));
        map.addAttribute("allStatus", Arrays.asList(ProjectStatus.values()));
        return "myPage";
    }

    @PostMapping("/changeStatus")
    public String changeStatus(@RequestParam("postid") int id,@RequestParam("status") ProjectStatus status){
        Project project=projectRepository.findOne(id);
        project.setProjectStatus(status);
        projectRepository.save(project);
        return "redirect:/user/myPage";
    }

    @GetMapping("/allUserPage")
    public String allUserPage(ModelMap map){
        map.addAttribute("allUsers",userRepository.findAll());
        return "allUsers";
    }
}
