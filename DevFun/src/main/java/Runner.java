import config.DevFunConfig;
import dao.BookReviewEntityDAO;
import entity.BookReviewEntity;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resource.BookReviewResource;

public class Runner extends Application<DevFunConfig> {

    public static void main(String[] args) throws Exception {
        new Runner().run(args);
    }

    @Override
    public void run(DevFunConfig devFunConfig, Environment environment) throws Exception {
        System.out.println("Running");
        BookReviewEntityDAO bookReviewEntityDAO = new BookReviewEntityDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new BookReviewResource(bookReviewEntityDAO));
    }

    HibernateBundle<DevFunConfig> hibernateBundle = new HibernateBundle<DevFunConfig>(BookReviewEntity.class) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(DevFunConfig config) {
            return config.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<DevFunConfig> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

}
