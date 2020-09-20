package com.ski11up.hibernate.demo;

import javax.persistence.*;

/**
 * <p>
 * Contact PoJo.
 * </p>
 *
 * <p>
 * Match with equivalent DB Table.
 * </>
 *
 * @author Jagdeep Jain
 */

@Entity
@Table(name = "contacts",
    schema = "sales",
    uniqueConstraints= {@UniqueConstraint(columnNames={"id"})})
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private int id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "email", nullable = false)
  private String email;

  public Contact() {}

  public Contact(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName(String sandeep) {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String toString() {
    return "Contact Id : " + this.id
        + " First Name : " + this.firstName
        + " Last Name : " + this.lastName;
  }
}
