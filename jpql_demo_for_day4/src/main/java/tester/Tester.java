package tester;

import entity.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee("xa12tt", "Kurt", "Wonnegut", new BigDecimal(37)));
            em.persist(new Employee("hyu654", "Hannah", "Olsen", new BigDecimal(43587)));
            em.persist(new Employee("uio876", "Jan", "Olsen", new BigDecimal(4167)));
            em.persist(new Employee("klo999", "Irene", "Petersen", new BigDecimal(367)));
            em.persist(new Employee("jik666", "Tian", "Wonnegut", new BigDecimal(5567)));
            em.persist(new Employee("m3ch45", "Steven", "The Omnisaiah", new BigDecimal(5667)));
            em.getTransaction().commit();

            //Complete all these small tasks. Your will find the solution to all, but the last,
            //In this document: https://en.wikibooks.org/wiki/Java_Persistence/JPQL#JPQL_supported_functions
            //1) Create a query to fetch all employees with a salary > 100000 and print out all the salaries
            TypedQuery<Employee> query1 = em.createQuery("SELECT e FROM Employee e WHERE e.salary > 10000", Employee.class);
            List<Employee> emplist1 = query1.getResultList();
            System.out.println(emplist1.toString());
            //2) Create a query to fetch the employee with the id "klo999" and print out the firstname
            Employee query2 = em.find(Employee.class, "klo999");
            System.out.println(query2.toString());
            //3) Create a query to fetch the highest salary and print the value
            TypedQuery<Employee> query3 = em.createQuery("SELECT MAX(e.salary) FROM Employee e", Employee.class);
            List<Employee> emplist2 = query3.getResultList();
            System.out.println(emplist2);
            //4) Create a query to fetch the firstName of all Employees and print the names
            TypedQuery<Employee> query4 = em.createQuery("SELECT e.firstName FROM Employee e", Employee.class);
            List<Employee> emplist3 = query4.getResultList();
            System.out.println(emplist3);
            //5 Create a query to calculate the number of employees and print the number
            TypedQuery<Employee> query5 = em.createQuery("SELECT COUNT(e) FROM Employee e", Employee.class);
            List<Employee> emplist4 = query5.getResultList();
            System.out.println(emplist4);
            //6 Create a query to fetch the Employee with the higest salary and print all his details
            TypedQuery<Employee> query6 = em.createQuery("SELECT e FROM Employee e WHERE e.salary=(SELECT MAX(e.salary) FROM Employee e)", Employee.class);
            Employee emplist5 = query6.getSingleResult();
            System.out.println(emplist5);

        } finally {
            em.close();
            emf.close();
        }
    }

}
