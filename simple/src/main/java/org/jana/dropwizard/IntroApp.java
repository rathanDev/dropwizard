package org.jana.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class IntroApp extends Application<BasicConfig> {

    public static void main(String[] args) throws Exception {
        new IntroApp().run("server", "config.yml");
    }

    @Override
    public void run(BasicConfig config, Environment env) {
        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                env.servlets().addFilter("CORS", CrossOriginFilter.class);
        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        int defaultSize = config.getDefaultSize();
        BrandRepo brandRepository = new BrandRepo(initBrands());
        BrandResource brandResource = new BrandResource(defaultSize, brandRepository);
        env.jersey().register(brandResource);

        TaskRepo taskRepo = new TaskRepo();
        TaskResource taskResource = new TaskResource(taskRepo);
        env.jersey().register(taskResource);

        env.healthChecks().register("application", new AppHealthCheck());
    }

    private List<Brand> initBrands() {
        return Arrays.asList(
                new Brand(1L, "abc"),
                new Brand(2L, "def")
        );
    }

    @Override
    public void initialize(Bootstrap<BasicConfig> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }


}
