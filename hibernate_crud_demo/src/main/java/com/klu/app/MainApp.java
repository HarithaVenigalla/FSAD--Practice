package com.klu.app;

import com.klu.model.Department;
import com.klu.model.Employee;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.klu.util.hibernateUtil;

public class MainApp {

    static SessionFactory factory = hibernateUtil.getSessionFactory();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("---------- Main Menu ------------");
            System.out.println("1. Insert Employee");
            System.out.println("2. Display Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Select your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    insertEmployee(sc);
                    break;
                case 2:
                    displayEmployee(sc);
                    break;
                case 3:
                    updateEmployee(sc);
                    break;
                case 4:
                    deleteEmployee(sc);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 5);

        factory.close();
        sc.close();
    }

    // ---------------- INSERT ----------------
    static void insertEmployee(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Employee Name: ");
        String name = sc.next();

        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        System.out.print("Enter Department Name: ");
        String deptName = sc.next();

        Department dept = new Department();
        dept.setDeptName(deptName);

        Employee emp = new Employee();
        emp.setEmpName(name);
        emp.setEmpSalary(salary);
        emp.setDepartment(dept);

        session.save(dept);
        session.save(emp);

        tx.commit();
        session.close();

        System.out.println("Employee inserted successfully!");
    }

    // ---------------- DISPLAY ---	-------------
    static void displayEmployee(Scanner sc) {

        Session session = factory.openSession();

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        Employee emp = session.get(Employee.class, id);

        if (emp != null) {
            System.out.println("Name   : " + emp.getEmpName());
            System.out.println("Salary : " + emp.getEmpSalary());
            System.out.println("Dept   : " + emp.getDepartment().getDeptName());
        } else {
            System.out.println("Employee Not Found");
        }

        session.close();
    }

    // ---------------- UPDATE ----------------
    static void updateEmployee(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        Employee emp = session.get(Employee.class, id);

        if (emp != null) {
            System.out.print("Enter New Salary: ");
            emp.setEmpSalary(sc.nextDouble());
            tx.commit();
            System.out.println("Salary Updated Successfully!");
        } else {
            System.out.println("Employee Not Found");
            tx.rollback();
        }

        session.close();
    }

    // ---------------- DELETE ----------------
    static void deleteEmployee(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        Employee emp = session.get(Employee.class, id);

        if (emp != null) {
            session.delete(emp);
            tx.commit();
            System.out.println("Employee Deleted Successfully!");
        } else {
            System.out.println("Employee Not Found");
            tx.rollback();
        }

        session.close();
    }
}
