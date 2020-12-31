package com.ski11up.sales.contact.service;


import com.ski11up.sales.contact.dao.ContactDAO;
import com.ski11up.sales.contact.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
@Repository
public class ContactServiceImpl implements ContactService {

  private ContactDAO contactDAO;

  @Autowired
  public ContactServiceImpl(ContactDAO contactDAO) {
    this.contactDAO = contactDAO;
  }
  @Override
  @Transactional
  public List<Contact> finalAll() {
    return contactDAO.findAll();
  }

  @Override
  @Transactional
  public void save(Contact contact) {
    contactDAO.save(contact);
  }

  @Override
  @Transactional
  public Contact find(int id) {
    return contactDAO.find(id);
  }

  @Override
  @Transactional
  public void delete(int id) {
    contactDAO.delete(id);
  }

  @Override
  @Transactional
  public List<Contact> search(String firstName) {
    return contactDAO.search(firstName);
  }
}
