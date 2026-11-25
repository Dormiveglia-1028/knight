package me.scpark.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ExampleController {
    @GetMapping("/thymeleaf/example")
    public String thymeleaf(Model model) {
        Person person = new Person();
        person.setName("PH");
        person.setAge(30000);
        person.setHobbies(List.of("play","sleep","eat"));
        model.addAttribute("student", person);
        return "example";
    }

    @GetMapping("/thymeleaf/test")
    public String test(Model model) {
        model.addAttribute("name","PH");
        model.addAttribute("age",30000);
        List<String> hobbies = new ArrayList<>();
        hobbies.add("play");
        hobbies.add("sleep");
        hobbies.add("eat");
        model.addAttribute("hobbies",hobbies);
        return "test";
    }
    @GetMapping("/thymeleaf/test2")
    public String test2(Model model) {
        Person person = new Person();
        person.setName("PH");
        person.setAge(30000);
        List<String> hobbies = new ArrayList<>();
        hobbies.add("play");
        hobbies.add("sleep");
        hobbies.add("eat");
        person.setHobbies(hobbies);
        model.addAttribute("student",person);
        return "test2";
    }
}
@Getter
@Setter
class Person {
    private String name;
    private int age;
    private List<String> hobbies;
}
