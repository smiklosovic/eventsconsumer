package eventsconsumer;

import com.datastax.driver.core.PlainTextAuthProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value(value = "${cassandra.contactPoints}")
    private String contactPoints;

    @Value(value = "${cassandra.port}")
    private int port;

    @Value(value = "${cassandra.keyspace}")
    private String keyspace;

    @Value(value = "${cassandra.username}")
    private String username;

    @Value(value = "${cassandra.password}")
    private String password;

    @Override
    protected String getKeyspaceName() {
        return keyspace;
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster =
                new CassandraClusterFactoryBean();
        cluster.setContactPoints(contactPoints);
        cluster.setPort(port);
        cluster.setAuthProvider(new PlainTextAuthProvider(username,password));
        cluster.setJmxReportingEnabled(false);
        return cluster;
    }
}
