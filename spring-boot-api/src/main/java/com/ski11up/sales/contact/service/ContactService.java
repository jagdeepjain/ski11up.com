package com.ski11up.sales.contact.service;

import com.ski11up.sales.contact.entity.Contact;

import java.util.List;

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
public interface ContactService {

  public List<Contact> findAll();

  public Contact save(Contact contact);

  public Contact find(int id);

  public void delete(int id);

  public List<Contact> search(String firstName);

}
