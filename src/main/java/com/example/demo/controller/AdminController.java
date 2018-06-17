package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/admin")
    public String adminPage(ModelMap map){
//        map.addAttribute("admin",userRepository.findOneByEmail(userDetails.getUsername()));
        map.addAttribute("allProjects",projectRepository.findAll());
        map.addAttribute("project",new Project());
        map.addAttribute("allUsers",userRepository.findAll());
        return "adminPage";
    }

    @PostMapping("/addProject")
    public String addProject(@ModelAttribute("project") Project project){
        project.setProjectStatus(ProjectStatus.ONLYBEGIN);
        for (User user : project.getUsers()) {
            user.getProjects().add(project);
        }
        projectRepository.save(project);
        return "redirect:/admin/admin";
    }

    @GetMapping("/addUserPage")
    public String addUserPage(ModelMap map){
        map.addAttribute("user",new User());
        map.addAttribute("profTypes", Arrays.asList(UserProfType.values()));
        return "addUser";
    }

    @GetMapping("/singlUser/{id}")
    public String singlUserPage(ModelMap map,@PathVariable("id") int id){
        map.addAttribute("thisUser",userRepository.findOne(id));
        return "singlUser";
    }

//    @PostMapping("/addUser")
//    public String addUser(@ModelAttribute("user") User user, @RequestParam("picture")MultipartFile multipartFile) throws IOException {
//        String picName=multipartFile.getOriginalFilename()+"_"+System.currentTimeMillis();
//        File file=new File("/home/hovo/Desktop/pics/"+picName);
//        multipartFile.transferTo(file);
//        user.setPicUrl(picName);
//        user.setUserType(UserType.USER);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return "redirect:/admin/admin";
//    }

}
