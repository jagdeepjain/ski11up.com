package com.ski11up.sales.contact.controller;

import com.ski11up.sales.contact.entity.Contact;
import com.ski11up.sales.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * Contact Controller
 * </p>
 *
 * <p>
 * Controller for Contact.
 * </>
 *
 * @author Jagdeep Jain
 */

@Controller
@RequestMapping("/contact")
public class ContactController {

  private ContactService contactService;

  @Autowired
  public ContactController(ContactService contactService) {
    this.contactService = contactService;
  }

  @GetMapping("/list")
  public String getContacts(Model model) {
    model.addAttribute("contact", contactService.finalAll());
    return "contact";
  }

  @GetMapping("/addContactForm")
  public String addContactForm(Model model) {
    Contact contact = new Contact();
    model.addAttribute("contact", contact);
    return "contact-form";
  }

  @PostMapping("/save")
  public String addContact(@ModelAttribute("contact") Contact contact) {
    contactService.save(contact);
    return "redirect:/contact/list";
  }

  @GetMapping("/updateForm")
  public String updateForm(@RequestParam("id") int id, Model model) {
    Contact contact = contactService.find(id);
    model.addAttribute("contact", contact);
    return "contact-form";
  }

  @GetMapping("/delete")
  public String delete(@RequestParam("id") int id) {
    contactService.delete(id);
    return "redirect:/contact/list";
  }

  @PostMapping("/search")
  public String search(@RequestParam("firstName") String firstName, Model model) {
    model.addAttribute("contact", contactService.search(firstName));
    return "search";
  }
}
