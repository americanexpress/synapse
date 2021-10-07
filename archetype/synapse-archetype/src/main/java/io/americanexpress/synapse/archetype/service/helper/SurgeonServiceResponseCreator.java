/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.americanexpress.synapse.archetype.service.helper;

import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.utils.cli.CommandLineException;
import org.springframework.stereotype.Component;

import io.americanexpress.synapse.archetype.model.SurgeonServiceResponse;

/**
 * {@code SurgeonServiceResponseCreator} class creates the {@link SurgeonServiceResponse}.
 * @author Paolo Claudio
 *
 */
@Component
public class SurgeonServiceResponseCreator {

	/**
	 * Create the {@link SurgeonServiceResponse}.
	 * @param invocationResult containing the result of the maven-invoker
	 * @return the {@link SurgeonServiceResponse}
	 */
	public SurgeonServiceResponse create(InvocationResult invocationResult) {
		int exitCode = invocationResult.getExitCode();

		String message = "Success";
		if(exitCode != 0) {
			CommandLineException commandLineException = invocationResult.getExecutionException();
			message = commandLineException != null ? commandLineException.getMessage() : "Unknown Failure";
		}
		
		SurgeonServiceResponse surgeonServiceResponse = new SurgeonServiceResponse();
		surgeonServiceResponse.setMessage(message);
		return surgeonServiceResponse;
	}
}
