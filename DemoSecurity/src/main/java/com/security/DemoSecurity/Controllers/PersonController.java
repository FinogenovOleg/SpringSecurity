package com.security.DemoSecurity.Controllers;

import com.security.DemoSecurity.Entity.Person;
import com.security.DemoSecurity.Services.PersonService;
import com.security.DemoSecurity.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class PersonController {

    private final PersonValidator validator;
    private final PersonService personService;

    @Autowired
    public PersonController(PersonValidator validator, PersonService personService) {
        this.validator = validator;
        this.personService = personService;
    }

    @GetMapping("/hello")
    public String greetingPage(@RequestParam(name = "name", defaultValue = "World") String name,
                               Model model) {
        model.addAttribute("name", name);
        return "hello.html";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login.html";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("person") Person person) {
        return "registration.html";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) {
        validator.validate(person,bindingResult);

        if (bindingResult.hasErrors()) return "redirect:/auth/registration";

        personService.register(person);

        return "redirect:/auth/login";
    }
}
