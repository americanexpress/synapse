package ${package}.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ${package}.entity.${tableName}Entity;

/**
 * @author ${author}
 */
@Repository
public interface ${tableName}Repository extends JpaRepository<${tableName}Entity, Long> {
}
