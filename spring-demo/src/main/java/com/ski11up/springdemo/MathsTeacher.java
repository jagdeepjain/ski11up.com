package com.ski11up.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Maths Teacher.
 * </p>
 *
 * <p>
 * Get Home Work and other stuff.
 * </>
 *
 * @author Jagdeep Jain
 */

@Component
public class MathsTeacher implements Teacher {

  @Autowired
  @Qualifier("happyTutorService")
  private TutorService tutorService;

  public String getHomeWork() {
    return "Maths Home Work.";
  }

   public String getStudyMaterial() {
    return tutorService.provideStudyMaterial();
  }
}
