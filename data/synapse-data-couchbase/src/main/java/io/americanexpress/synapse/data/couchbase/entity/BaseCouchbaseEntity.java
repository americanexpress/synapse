package io.americanexpress.synapse.data.couchbase.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

/**
 * @author Darien Liburd
 */

@Data
@Document
public abstract class BaseCouchbaseEntity {

	@Id
	@JsonAlias({"id", "identifier"})
	private String id;

}
