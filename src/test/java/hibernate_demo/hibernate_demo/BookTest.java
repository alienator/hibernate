package hibernate_demo.hibernate_demo;

import org.junit.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import junit.framework.TestCase;

public class BookTest extends TestCase {
	private EntityManagerFactory entityManagerFactory;	
	
	@Override
	public void setUp() throws Exception {
		entityManagerFactory = Persistence
							   .createEntityManagerFactory("bookstest");
	}
	
	@Override
	public void tearDown() throws Exception {
		entityManagerFactory.close();
	}
	
	@Test
	public void test() {
		EntityManager entityManager = entityManagerFactory
									  .createEntityManager();
		
		entityManager.getTransaction().begin();
        entityManager.persist(new Book(1L, "Tit1", "Auth1"));        
        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        Book b = entityManager.find(Book.class, 1L);
        
        System.out.println(b.toString());
        
        assertEquals((Long)b.getId(), (Long)1L);
	}

}
