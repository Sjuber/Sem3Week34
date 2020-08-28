/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author SJUBE
 */



public class BookFacade {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");      
    BookFacade facade = BookFacade.getBookFacade(emf);
    Book b1 = facade.addBook("Author 1");
    Book b2 = facade.addBook("Author 2");
    Book b3 = facade.addBook("Author 3");
    //Find book by ID
    System.out.println("Book1: "+facade.findBook(b1.getId()).getAuthor());
    System.out.println("Book2: "+facade.findBook(b2.getId()).getAuthor());
    System.out.println("Book3: "+facade.findBook(b2.getId()).getAuthor());
    //Find all books
    System.out.println("Number of books: "+facade.getAllBooks().size());

    }
    
    private static EntityManagerFactory emf;
    private static BookFacade instance;

    private BookFacade() {}

    public static BookFacade getBookFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BookFacade();
        }
        return instance;
    }
    
    
    //THis one adds a new book
    public Book addBook(String author){
        Book book = new Book(author);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
            return book;
        }finally {
            em.close();
        }
    }
    
    // THis is basicly to find a certain book
    public Book findBook(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Book book = em.find(Book.class,id);
            return book;
        }finally {
            em.close();
        }
    }
    
    // STandard ye olde get all the books really
    public List<Book> getAllBooks(){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Book> query = 
                       em.createQuery("Select book from Book book",Book.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }

}
