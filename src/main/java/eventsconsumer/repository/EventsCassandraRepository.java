package eventsconsumer.repository;
import org.springframework.data.cassandra.repository.MapIdCassandraRepository;

public interface EventsCassandraRepository extends MapIdCassandraRepository<SensorEvents> {
}
