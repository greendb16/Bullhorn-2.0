package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    MessageRepository messageRepository;

    @RequestMapping("/")
    public String listMessages (Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String messageForm(Model model){
        model.addAttribute("message", new Message());
        return "messageform";
    }
    @PostMapping
    public String messageForm(@Valid Message message, BindingResult result) {
        if (result.hasErrors()) {
            return "messageform";
        }
        messageRepository.save(message);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showTodo(@PathVariable("id") long id, Model model){
        model.addAttribute("todo", todoRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateTodo(@PathVariable("id") long id, Model model){
        model.addAttribute("todo", todoRepository.findById(id).get());
        return "todoform";
    }
    @RequestMapping("/delete/{id}")
    public String delTodo(@PathVariable("id") long id, Model model){
        todoRepository.deleteById(id);
        return "redirect:/";
    }
    }
