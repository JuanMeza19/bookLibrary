package libraryBook.libraryBook;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static StandardServiceRegistry serviceRegistry;

    static {
        try {
            // Configura el registro de servicios usando el archivo hibernate.cfg.xml
            serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

            // Construye los metadatos a partir de las fuentes (entidades)
            Metadata metadata = new MetadataSources(serviceRegistry)
                .getMetadataBuilder()
                .build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();

        } catch (Throwable ex) {
        	StandardServiceRegistryBuilder.destroy(serviceRegistry); // Liberar recursos
            System.err.println("Error al inicializar SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
        if (serviceRegistry != null) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry); // Libera recursos
        }
    }
}