package org.jana.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.jana.dropwizard.core.Task;
import org.jana.dropwizard.dao.TaskDao;
import org.jana.dropwizard.resource.TaskResource;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class IntroApp extends Application<SimpleConfig> {

    public static void main(String[] args) throws Exception {
        new IntroApp().run("server", "config.yml");
    }

    @Override
    public void run(SimpleConfig config, Environment env) {

        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                env.servlets().addFilter("CORS", CrossOriginFilter.class);
        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        TaskDao taskDao = new TaskDao(hibernateBundle.getSessionFactory());
        TaskResource taskResource = new TaskResource(taskDao);
        env.jersey().register(taskResource);

        // env.healthChecks().register("application", new AppHealthCheck());
    }

    HibernateBundle<SimpleConfig> hibernateBundle = new HibernateBundle<SimpleConfig>(Task.class) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(SimpleConfig config) {
            return config.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<SimpleConfig> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

}
