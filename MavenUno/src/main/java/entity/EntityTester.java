/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author SJUBE
 */
public class EntityTester {
    
    public static void main(String[] args) {
EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
EntityManager em = emf.createEntityManager();
Book book1 = new Book("J.K. Rowling");
Book book2 = new Book("Georg R. R. Martin");
Book book3 = new Book("Georg Brandes");
try {
      em.getTransaction().begin();
      em.persist(book1);
      em.persist(book2);
      em.persist(book3);
      em.getTransaction().commit();
      //Verify that books are managed and has been given a database id
      System.out.println(book1.getId());
      System.out.println(book2.getId());
      System.out.println(book3.getId());
       	 
    	}finally{
        	em.close();
    	}
}

    }
    

