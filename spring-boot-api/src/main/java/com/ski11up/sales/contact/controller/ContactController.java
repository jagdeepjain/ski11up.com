package com.ski11up.sales.contact.controller;

import com.ski11up.sales.contact.entity.Contact;
import com.ski11up.sales.contact.service.ContactService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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

@RestController
@RequestMapping(path = "/contact", produces = { "application/json"})
public class ContactController {

  private ContactService contactService;

  public ContactController(ContactService contactService) {
    this.contactService = contactService;
  }

  @PostMapping("/add")
  public ResponseEntity<?> addContact(@RequestBody Contact contact,
                                      UriComponentsBuilder ucBuilder) {
    contactService.save(contact);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(
        ucBuilder.path("/app/contact/add/{id}").buildAndExpand(
            contact.getId()).toUri());
    return new ResponseEntity<String>(headers, HttpStatus.CREATED);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") int id) {
    Contact user = contactService.find(id);
    if (user == null) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    contactService.delete(id);
    return new ResponseEntity<Contact>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<?> search(@PathVariable("id") int id) {
    Contact contact = contactService.find(id);
    if (contact == null) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    final HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);

    return new ResponseEntity<Contact>(contact, httpHeaders, HttpStatus.OK);
  }

  @GetMapping("/list")
  public ResponseEntity<List<Contact>> getContacts() {
    List<Contact> contacts = contactService.findAll();
    if (contacts.isEmpty()) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    final HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);

    return new ResponseEntity<List<Contact>>(contacts,
        httpHeaders, HttpStatus.OK);
  }

}
