package ${package}.entity;

import io.americanexpress.synapse.data.postgres.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;

/**
 *
 * @author ${author}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "")
public class SampleEntity extends BaseEntity {

    @Column(name = "sampleColumn")
    private String sampleCoulmn;

}


