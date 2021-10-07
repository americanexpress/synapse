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

import java.io.File;

import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.springframework.stereotype.Component;

import io.americanexpress.synapse.framework.exception.ApplicationServerException;

/**
 * {@code MavenInvoker} class uses the {@code maven-invoker}
 * in order to generate the module from the archetype in a specified output directory.
 * @author Paolo Claudio
 *
 */
@Component
public class MavenInvoker {

	/**
	 * Generate the module in the specified output directory.
	 * @param outputDirectoryName output directory where the generated module will be created
	 * @param invocationRequest containing the Maven goals
	 * @return the {@link InvocationResult}
	 */
	public InvocationResult invoke(String outputDirectoryName, InvocationRequest invocationRequest) {
		Invoker invoker = new DefaultInvoker();
		invoker.setWorkingDirectory(new File(outputDirectoryName));
		try {
			return invoker.execute(invocationRequest);
		} catch (MavenInvocationException mavenInvocationException) {
			throw new ApplicationServerException(mavenInvocationException);
		}
	}
}
