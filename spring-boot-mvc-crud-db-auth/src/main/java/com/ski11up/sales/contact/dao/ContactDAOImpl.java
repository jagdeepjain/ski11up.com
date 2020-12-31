package com.ski11up.sales.contact.dao;

import com.ski11up.sales.contact.entity.Contact;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * <p>
 * Contact DAO Implementation.
 * </p>
 *
 * <p>
 * Implements Contact DAO Methods.
 * </>
 *
 * @author Jagdeep Jain
 */
@Repository
public class ContactDAOImpl implements ContactDAO {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  public ContactDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<Contact> findAll() {
    Session currentSession = entityManager.unwrap(Session.class);
    Query<Contact> query = currentSession.createQuery("FROM Contact order by " +
            "firstName",
        Contact.class);
    List<Contact> contacts = query.getResultList();
    return contacts;
  }

  @Override
  public void save(Contact contact) {
    Session currentSession = entityManager.unwrap(Session.class);
    currentSession.saveOrUpdate(contact);
  }

  @Override
  public Contact find(int id) {
    Session currentSession = entityManager.unwrap(Session.class);
    Contact contact = currentSession.get(Contact.class, id);
    return contact;
  }

  @Override
  public void delete(int id) {
    Session currentSession = entityManager.unwrap(Session.class);
    Query query = currentSession.createQuery("delete from Contact where " +
        "id=:id");
    query.setParameter("id", id);
    query.executeUpdate();
  }

  @Override
  public List<Contact> search(String firstName) {
    Session currentSession = entityManager.unwrap(Session.class);
    Query<Contact> query = currentSession.createQuery("FROM Contact where " +
        "firstName=:firstName", Contact.class);
    query.setParameter("firstName", firstName);
    List<Contact> contacts = query.getResultList();
    return contacts;
  }
}