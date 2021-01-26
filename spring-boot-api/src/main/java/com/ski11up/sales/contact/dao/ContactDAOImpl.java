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
    Query<Contact> query = currentSession.createQuery("SELECT c FROM Contact " +
            "c " +
            "order by " +
            "c.firstName",
        Contact.class);
    return query.getResultList();
  }

  @Override
  public void save(Contact contact) {
    System.out.println(contact.toString());
    Session currentSession = entityManager.unwrap(Session.class);
    currentSession.saveOrUpdate(contact);
  }

  @Override
  public Contact find(int id) {
    Session currentSession = entityManager.unwrap(Session.class);
    return currentSession.get(Contact.class, id);
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
    Query<Contact> query = currentSession.createQuery("SELECT c FROM Contact " +
        "c " +
        "where " +
        "c.firstName=:firstName", Contact.class);
    query.setParameter("firstName", firstName);
    return query.getResultList();
  }
}