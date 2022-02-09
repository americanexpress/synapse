package ${package}.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ${package}.entity.SampleEntity;

/**
 * @author ${author}
 */
@Repository
public interface SampleRepository extends JpaRepository<SampleEntity, Long> {
}
