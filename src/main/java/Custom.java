import Entity.Developer;
import Entity.DevelopersToSkills;
import Entity.Rate;
import Entity.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;


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
import java.util.Collection;
import java.util.List;

/**
 * [автозагрузчик сущностей]
 * https://stackoverflow.com/questions/32824823/hibernate-mapping-classes-from-hibernate-cfg-xml
 */
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

        try {
            session.beginTransaction();

            //        String HQL = "from Developer d where d.name like 'Alex%' ";
            //        List<Developer> result = session.createQuery( HQL, Developer.class ).list();

            String SQL = "select * from developers d where name like 'Alex%'";
            List<Developer> result = session.createNativeQuery( SQL, Developer.class ).list();


            Developer item = result.stream()
                    .filter( developers -> developers.getName().equals( "Alex" ) )
                    .findFirst()
                    .orElse( null );

            if ( item != null )
                System.out.println( item.getName() );
            else {
                System.out.println(result.get( 0 ).getName());
            }

//            SQL = "SELECT " +
//
//                    "  DISTINCT ON (d2.name) d2.name AS name, " +
//                    "  s.title title, " +
//                    "  s.rate, s.id AS id " +
//
//                    "FROM skills S\n" +
//                    "JOIN developers_to_skills dts ON S.id = dts.skill_id\n" +
//                    "JOIN developers d2 ON dts.developer_id = d2.id\n" +
//                    "ORDER BY name, title";

            SQL = "SELECT\n" +
                    "\n" +
                    "  d2.name As name,\n" +
                    "  s.title title,\n" +
                    "  s.rate, s.id AS id \n" +
                    "\n" +
                    "FROM skills S\n" +
                    "JOIN developers_to_skills dts ON S.id = dts.skill_id\n" +
                    "JOIN developers d2 ON dts.developer_id = d2.id\n" +
                    "  where s.rate = 'Middle' and title = 'Java'\n" +
                    "ORDER BY s.rate";

            List<Skill> result2 = session.createNativeQuery( SQL, Skill.class ).list();

            // [!] Почему все три девелопера лежат в каждом из трех айтемов списка
            result2.get( 0 )
                    .getDevelopersToSkillsById().stream()
                    .map( DevelopersToSkills::getDeveloperByDeveloperId )
                    .map( Developer::getName )
                    .forEach( System.out::println );

            System.out.println(result2.get( 0 ));


            session.getTransaction().commit();

        } catch ( Throwable throwable ){
            throwable.printStackTrace();
        } finally {
            session.close();
            app.close();
        }


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
            e.printStackTrace();
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
