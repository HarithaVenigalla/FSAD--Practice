package com.klu;

import org.hibernate.*;

import org.hibernate.cfg.Configuration;
public class MainApp {
	public static void main(String[] args) {

	Configuration cfg = new Configuration();
    cfg.configure("hibernate.cfg.xml");
    cfg.addAnnotatedClass(Student.class);

    SessionFactory factory = cfg.buildSessionFactory();
    Session session = factory.openSession();

    Transaction tx = session.beginTransaction();

    Student s = new Student("Hari");
    session.save(s);

    tx.commit();

    session.close();
    factory.close();

    System.out.println("Student data saved successfully");

	}

}