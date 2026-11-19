package me.scpark.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ExampleController {
    @GetMapping("/thymeleaf/example")
    public String thymeleaf(Model model) {
        Person person = new Person();
        person.setName("PH");
        person.setAge(30000);
        person.setHobbies(List.of("game","sleep","eat"));
        model.addAttribute("student", person);
        return "example";
    }
}
@Getter
@Setter
class Person {
    private String name;
    private int age;
    private List<String> hobbies;
}
