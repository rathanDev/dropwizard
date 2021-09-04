package org.jana.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.Arrays;
import java.util.List;

public class IntroApp extends Application<BasicConfig> {

    public static void main(String[] args) throws Exception {
        new IntroApp().run("server", "intro-config.yml");
    }

    @Override
    public void run(BasicConfig config, Environment env) {
        int defaultSize = config.getDefaultSize();
        BrandRepo brandRepository = new BrandRepo(initBrands());
        BrandResource brandResource = new BrandResource(defaultSize, brandRepository);
        env.healthChecks().register("application", new AppHealthCheck());
        env.jersey().register(brandResource);
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
