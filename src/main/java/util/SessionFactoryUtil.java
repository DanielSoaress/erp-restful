package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactoryUtil {
	public static SessionFactory sessionFactory;

	private static SessionFactory buildSessionFactory() throws Exception {
		if(sessionFactory == null) {
			// A SessionFactory is set up once for an application!
			final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build(); 
			// configures settings from hibernate.cfg.xml
			try {
				sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
				return sessionFactory;
			}
			catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
				System.err.println("ERRO na Cria��o do SessionFactory: " + e);
				StandardServiceRegistryBuilder.destroy( registry );
				return null;
			}
		} else {
			return sessionFactory;
		} 
}
		
	public static SessionFactory getSessionFactory() throws Exception {
		sessionFactory = buildSessionFactory();
		return sessionFactory;
	}
	
}
