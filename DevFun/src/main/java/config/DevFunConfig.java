package config;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import java.util.Objects;

public class DevFunConfig extends Configuration {

    private DataSourceFactory dataSourceFactory;

    public DataSourceFactory getDataSourceFactory() {
        if (Objects.isNull(dataSourceFactory))
            dataSourceFactory = new DataSourceFactory();
        return dataSourceFactory;
    }

    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

}
