package com.ski11up.sales.contact.service;

import com.ski11up.sales.contact.dao.UserRepository;
import com.ski11up.sales.contact.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.getUserByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User not found.");
    }

    return new SalesAppUserDetails(user);
  }
}
