package com.ski11up.springbootdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Hello World Controller.
 *
 * <p>Hello World service controller. </>
 *
 * @author Jagdeep Jain
 */
@Controller
public class HelloWorld {

  @RequestMapping("/hello-world")
  @ResponseBody
  public String helloWorld() {
    return "Hello World!";
  }
}
