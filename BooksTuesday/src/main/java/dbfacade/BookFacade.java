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
 * @author Sebastian
 */
public class BookFacade {

    private static EntityManagerFactory emf;
    private static BookFacade instance;

    private BookFacade() {
    }

    //get the active bookfacade, takes the active EntityManagerFactory so we can create entity managers using the same EntityManagerFactory, returns the instance of bookfacade
    public static BookFacade getBookFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BookFacade();
        }
        return instance;
    }

    //adds a book to the DB, takes the author as a string, returns the book object that is created in the DB.
    public Book addBook(String author) {
        Book book = new Book(author);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
            return book;
        } finally {
            em.close();
        }
    }

    //finds a specific book, takes the id of the book searched for, returns the book found from the id.
    public Book findBook(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Book book = em.find(Book.class, id);
            return book;
        } finally {
            em.close();
        }
    }

    //collects all books in the DB, takes nothing, returns a list containing all books available in db
    public List<Book> getAllBooks() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Book> query
                    = em.createQuery("Select book from Book book", Book.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        BookFacade facade = BookFacade.getBookFacade(emf);
        Book b1 = facade.addBook("Author 1");
        Book b2 = facade.addBook("Author 2");
        //Find book by ID
        System.out.println("Book1: " + facade.findBook(b1.getId()).getAuthor());
        System.out.println("Book2: " + facade.findBook(b2.getId()).getAuthor());
        //Find all books
        System.out.println("Number of books: " + facade.getAllBooks().size());
        List<Book> list = facade.getAllBooks();
        for (Book book: list) {
            System.out.println(book.toString());
        }

    }

}
