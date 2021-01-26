package com.ski11up.sales.contact.dao;

import com.ski11up.sales.contact.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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
public interface UserRepository extends CrudRepository<User, Long> {
  public User getUserByUsername(@Param("username") String username);
}
