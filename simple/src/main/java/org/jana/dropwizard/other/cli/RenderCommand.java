package org.jana.dropwizard.other.cli;

import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import org.jana.dropwizard.SimpleConfig;
import org.jana.dropwizard.other.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class RenderCommand extends ConfiguredCommand<SimpleConfig> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RenderCommand.class);

    public RenderCommand() {
        super("render", "Render the template data to console");
    }

    @Override
    public void configure(Subparser subparser) {
        super.configure(subparser);
        subparser.addArgument("-i", "--include-default")
                .action(Arguments.storeTrue())
                .dest("include-default")
                .help("Also render the template with the default name");
        subparser.addArgument("names").nargs("*");
    }

    @Override
    protected void run(Bootstrap<SimpleConfig> bootstrap,
                       Namespace namespace,
                       SimpleConfig configuration) throws Exception {
        final Template template = configuration.buildTemplate();

        if (namespace.getBoolean("include-default")) {
            LOGGER.info("DEFAULT => {}", template.render(Optional.empty()));
        }

        for (String name : namespace.<String>getList("names")) {
            for (int i = 0; i < 1000; i++) {
                LOGGER.info("{} => {}", name, template.render(Optional.of(name)));
                Thread.sleep(1000);
            }
        }
    }
}
