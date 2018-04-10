import Entity.Developers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


/**
 * Id
 * Column
 *
 * GeneratedValue
 * OneToOne
 *
 * JoinColumn - ставится в подчиненной сущности
 * JoinTable
 * PrimaryKeyJoinColumn
 *
 * Transient
 * Lob
 */
import java.util.List;

public class Custom {

    private SessionFactory sessionFactory;

    public static void main(String[] args)
    throws Exception
    {

        Custom app = new Custom();

        // app.bootstrap();

        app.init();

        if ( app.sessionFactory == null ) {
            System.out.println( "No session created" );
        }


        Session session = app.sessionFactory.openSession();

        session.beginTransaction();

        //        String HQL = "from Developers d where d.name like 'Alex%' ";
        //        List<Developers> result = session.createQuery( HQL, Developers.class ).list();

        String SQL = "select * from developers d where name like 'Alex%'";
        List<Developers> result = session.createNativeQuery( SQL, Developers.class ).list();

        session.getTransaction().commit();

        session.close();


        System.out.println( result );

        app.close();
    }

    void bootstrap()
    {
        final BootstrapServiceRegistry bootstrapServiceRegistry =
                new BootstrapServiceRegistryBuilder()
                        .enableAutoClose()
                        .applyIntegrator( MetadataExtractorIntegrator.INSTANCE )
                        .build();

        final StandardServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder( bootstrapServiceRegistry )
                        // .applySettings(properties())
                        .configure()
                        .build();
    }

    /**
     * sessionFactory = new Configuration().configure().buildSessionFactory();
     */
    protected void init()
    throws Exception
    {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        } catch ( Exception e ) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

//    public List<Cat> search(String catName) {
//        List<Cat> result = null;
//
//        Session session = sessionFactory.openSession();
//        String hql = "from Cat c " +
//                "where c.catName like '%" + catName + "%' " +
//                "order by c.weight desc";
//
//        String sql = "select id, cat_name, weight, sex from Cat where cat_name like '%" + catName + "%'";
//
//        System.out.println(hql);
//        System.out.println(sql);
//
//        result = session.createQuery(hql, Cat.class).list();
//        session.close();
//
//        return result;
//    }


    private void close()
    {
        sessionFactory.close();
    }
}
