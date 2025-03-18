package libraryBook.libraryBook;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class App {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            
            // Usa createNativeQuery() para SELECT
            session.createNativeQuery("SELECT 1", Integer.class).getResultList(); 
            
            session.getTransaction().commit();
            System.out.println("¡Operación exitosa!");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}