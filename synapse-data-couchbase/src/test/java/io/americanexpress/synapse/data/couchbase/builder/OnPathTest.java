package io.americanexpress.synapse.data.couchbase.builder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.americanexpress.synapse.data.couchbase.parameter.Parameters;

/**
 * @author Hursh Tiwari
 *
 */
class OnPathTest {

	private static final String DUMMY_KEY = "dummyKey";

	/**
	 * Test method for {@link OnPath#isNotMissing()}.
	 */
	@Test
	void isNotMissing_givenDummyKey_expectedClauseCorrectlyGenerated() {
		Parameters parameters=new Parameters();
		OnPath path = new OnPath(DUMMY_KEY,parameters);

		path.isNotMissing();

		assertNotNull(parameters.getParameters());
		assertEquals(1, parameters.getParameters().size());
		assertEquals(DUMMY_KEY,parameters.getParameters().get(0).getKey());
		assertEquals("dummyKey IS NOT MISSING",parameters.getParameters().get(0).toExpression().toString());
	}

	/**
	 * Test method for {@link OnPath#from()}.
	 */
	@Test
	void from_givenDummyKeyAndStringValue_expectedClauseCorrectlyGenerated() {
		Parameters parameters=new Parameters();
		OnPath path = new OnPath(DUMMY_KEY,parameters);

		path.from("2020-01-01 00:00:00.000000").add();

		assertNotNull(parameters.getParameters());
		assertEquals(1, parameters.getParameters().size());
		assertEquals(DUMMY_KEY,parameters.getParameters().get(0).getKey());
		assertEquals("dummyKey BETWEEN $dummyKey_BETWEEN_MIN",parameters.getParameters().get(0).toExpression().toString());
		assertEquals("dummyKey_BETWEEN_MIN",parameters.getParameters().get(0).getQueryKey());
		assertEquals("2020-01-01 00:00:00.000000",parameters.getParameters().get(0).getValue());
	}

	/**
	 * Test method for {@link OnPath#from()}.
	 */
	@Test
	void to_givenDummyKeyAndStringValue_expectedClauseCorrectlyGenerated() {
		Parameters parameters=new Parameters();
		OnPath path = new OnPath(DUMMY_KEY,parameters);

		path.to("2020-01-01 00:00:00.000000").add();

		assertNotNull(parameters.getParameters());
		assertEquals(1, parameters.getParameters().size());
		assertEquals(DUMMY_KEY,parameters.getParameters().get(0).getKey());
		assertEquals("$dummyKey_BETWEEN_MAX",parameters.getParameters().get(0).toExpression().toString());
		assertEquals("dummyKey_BETWEEN_MAX",parameters.getParameters().get(0).getQueryKey());
		assertEquals("2020-01-01 00:00:00.000000",parameters.getParameters().get(0).getValue());
	}
}
