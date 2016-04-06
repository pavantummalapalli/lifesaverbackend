package com.team.lifesaver.backend;


import com.team.lifesaver.backend.configuration.ServicesConfiguration;
import com.team.lifesaver.backend.dao.LifeSaverDAO;
import com.team.lifesaver.backend.resources.LifesaverResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;


/**
 * Created by pavan.t on 12/01/16.
 */
public class ServicesApplication extends Application<ServicesConfiguration> {

    public static void main(String[] args) throws Exception {

        new ServicesApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ServicesConfiguration> bootstrap) {

    }

    @Override
    public void run(ServicesConfiguration configuration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");

        final LifeSaverDAO lifeSaverDAO = jdbi.onDemand(LifeSaverDAO.class);

        LifesaverResource lifesaverResource = new LifesaverResource(lifeSaverDAO);

        environment.jersey().register(lifesaverResource);

        final ServicesHealthCheck servicesHealthCheck = new ServicesHealthCheck(configuration.getHealthCheckProperty());
        environment.healthChecks().register("configurationCheck", servicesHealthCheck);

    }
}
