package org.jana.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.jana.dropwizard.other.cli.RenderCommand;
import org.jana.dropwizard.core.Task;
import org.jana.dropwizard.dao.TaskDao;
import org.jana.dropwizard.other.Brand;
import org.jana.dropwizard.other.BrandRepo;
import org.jana.dropwizard.other.BrandResource;
import org.jana.dropwizard.resource.TaskResource;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class IntroApp extends Application<SimpleConfig> {

    public static void main(String[] args) throws Exception {
        new IntroApp().run("server", "config.yml");
    }

    private final HibernateBundle<SimpleConfig> hibernateBundle =
            new HibernateBundle<SimpleConfig>(Task.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(SimpleConfig configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public String getName() {
        return "simple";
    }

    @Override
    public void initialize(Bootstrap<SimpleConfig> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
        bootstrap.addCommand(new RenderCommand());
        bootstrap.addBundle(hibernateBundle);
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

        BrandRepo brandRepository = new BrandRepo(initBrands());
        BrandResource brandResource = new BrandResource(brandRepository);
        env.jersey().register(brandResource);

        TaskDao taskDao = new TaskDao(hibernateBundle.getSessionFactory());
        TaskResource taskResource = new TaskResource(taskDao);
        env.jersey().register(taskResource);

        env.healthChecks().register("application", new AppHealthCheck());
    }

    private List<Brand> initBrands() {
        return Arrays.asList(
                new Brand(1L, "abc"),
                new Brand(2L, "def")
        );
    }


}
