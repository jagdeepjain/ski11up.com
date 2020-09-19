package com.ski11up.spring.mvc.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Greeting Controller.
 *
 * <p>Contain methods to get the name of the person and greet him/her. </>
 *
 * @author Jagdeep Jain
 */
@Controller
@RequestMapping("/person")
public class GreetingController {

  @GetMapping("/details")
  public String getPerson(Model model) {
    model.addAttribute("person", new Person());
    return "details";
  }

  @PostMapping("/greetings")
  public String greetings(@ModelAttribute Person person, Model model) {
    model.addAttribute("greetings", person);
    return "message";
  }
}
