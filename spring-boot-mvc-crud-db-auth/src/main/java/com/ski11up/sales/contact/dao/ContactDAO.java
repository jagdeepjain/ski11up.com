package com.ski11up.sales.contact.dao;

import com.ski11up.sales.contact.entity.Contact;

import java.util.List;

/**
 * <p>
 * Contact DAO Interface.
 * </p>
 *
 * <p>
 * Contact DAO Interface, containing calls for CRUD operations.
 * </>
 *
 * @author Jagdeep Jain
 */
public interface ContactDAO {

  public List<Contact> findAll();

  public void save(Contact contact);

  public Contact find(int id);

  public void delete(int id);

  public List<Contact> search(String firstName);
}
