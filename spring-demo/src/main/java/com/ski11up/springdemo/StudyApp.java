package com.ski11up.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * Study App.
 * </p>
 *
 * <p>
 * Get in sync with teachers.
 * </>
 *
 * @author Jagdeep Jain
 */
public class StudyApp {
  public static void main(String[] args) {

    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(StudyConfig.class);
    Teacher theTeacher = context.getBean("mathsTeacher", Teacher.class);

    System.out.println(theTeacher.getHomeWork());
    System.out.println(theTeacher.getStudyMaterial());

    context.close();
  }
}
