package org.jana.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(HelloWorldConfiguration config, Environment env) {
        final HelloWorldResource resource = new HelloWorldResource(
                config.getTemplate(),
                config.getDefaultName()
        );
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(config.getTemplate());
        env.healthChecks().register("template", healthCheck);
        env.jersey().register(resource);
    }

}
