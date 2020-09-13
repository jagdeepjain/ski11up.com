package com.ski11up.springdemo;

import org.springframework.stereotype.Component;

/**
 * <p>
 * Implements Tutor Service.
 * </p>
 *
 * <p>
 * Happy Tutor Service.
 * </>
 *
 * @author Jagdeep Jain
 */

@Component
public class HappyTutorService implements TutorService {

  public String provideStudyMaterial() {
    return "Providing study material for needy students.";
  }

}
