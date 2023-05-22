import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
public class HibernateUtil {

	
	private static SessionFactory sessionFactory;
	
	static {
		try {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("AirFlys.cfg.xml").build();
		Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
		 sessionFactory = md.getSessionFactoryBuilder().build();
		}
		catch(Throwable th) {
			throw new ExceptionInInitializerError(th);
		}
	}
	
	public static SessionFactory getfactorysession() {
		
		return sessionFactory;
	}
	
}
