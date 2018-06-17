package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserType;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.CurrentUser;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/image")
    public void getImageAsByteArray(HttpServletResponse response, @RequestParam("fileName") String fileName) throws IOException {
        InputStream in=new FileInputStream("/home/hovo/Desktop/pics/"+fileName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in,response.getOutputStream());
    }

    @GetMapping("/loginPage")
    public String loginPage(ModelMap map){
        map.addAttribute("user",new User());
        return "login";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(){
        CurrentUser currentUser= (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser.getUser().getUserType()== UserType.ADMIN){
            return "redirect:/admin/admin";
        }
        return "redirect:/user/myPage";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user, @RequestParam("picture")MultipartFile multipartFile) throws IOException {
        String picName=multipartFile.getOriginalFilename()+"_"+System.currentTimeMillis();
        File file=new File("/home/hovo/Desktop/pics/"+picName);
        multipartFile.transferTo(file);
        user.setPicUrl(picName);
        user.setUserType(UserType.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/admin/admin";
    }
}
