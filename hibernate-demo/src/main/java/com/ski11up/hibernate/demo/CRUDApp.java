package com.ski11up.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * <p>
 * Main App Class.
 * </p>
 *
 * <p>
 * Perform CRUD on DB using Hibernate.
 * </>
 *
 * @author Jagdeep Jain
 */
public class CRUDApp {

  public static void main(String[] args) {

    Contact contact = new Contact(
        "Jagdeep",
        "Jain",
        "jagdeep@ski11up.com");

    Contact contactUpdate = new Contact(
        "Sandeep",
        "Jain",
        "jagdeep@ski11up.com");

    Contact contactDelete = new Contact(
        "Pradeep",
        "Jain",
        "pradeep@ski11up.com");

    Configuration cfg = new Configuration().configure();

    StandardServiceRegistry sr =
        new StandardServiceRegistryBuilder()
            .applySettings(cfg.getProperties())
            .build();

    Metadata m =
        new MetadataSources(sr)
            .addAnnotatedClass(Contact.class)
            .getMetadataBuilder().build();

    SessionFactory sf = m.getSessionFactoryBuilder().build();

    Session session = sf.openSession();
    try {
      // Create
      session.getTransaction().begin();
      session.save(contact);
      session.getTransaction().commit();

      // Read - Query is on the Contact Object but not on the DB Table
      List<Contact> contactList = session.createQuery("FROM Contact").list();
      contactList.forEach(
          contactDetails -> {
            System.out.println( "Contact # " + contactDetails);
          }
      );

      // Update based on HQL
      session.getTransaction().begin();
      session.save(contactUpdate);
      session.getTransaction().commit();

      session.getTransaction().begin();
      session.createQuery(
          "UPDATE Contact " +
              "set email='sandeep@ski11up.com' " +
              "WHERE firstName='Sandeep'")
          .executeUpdate();
      session.getTransaction().commit();

      // Delete based on HQL
      session.getTransaction().begin();
      session.save(contactDelete);
      session.getTransaction().commit();

      session.getTransaction().begin();
      Contact contactToDelete = session.get(Contact.class,
          contactDelete.getId());
      session.delete(contactToDelete);
      session.getTransaction().commit();

    } catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      session.close();
      sf.close();
    }
  }
}
