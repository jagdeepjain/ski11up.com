package com.ski11up.sales.contact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * File details.
 * </p>
 *
 * <p>
 * Usage of this file.
 * </>
 *
 * @author Jagdeep Jain
 */

@Controller
public class LoginController {

  @GetMapping("/login")
  public String login() {
    return "login";
  }

}
