/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.cloud.dataflow.server.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.dataflow.core.StreamDefinition;
import org.springframework.cloud.dataflow.rest.UpdateStreamRequest;
import org.springframework.cloud.deployer.spi.app.DeploymentState;

/**
 * Specify the supported operations on the stream.
 *
 * @author Mark Pollack
 * @author Ilayaperumal Gopinathan
 */
public interface StreamService {
	/**
	 * Deploys the stream with the user provided deployment properties.
	 * Implementations are responsible for expanding deployment wildcard expressions.
	 * @param name the name of the stream
	 * @param deploymentProperties deployment properties to use as passed in from the client.
	 */
	void deployStream(String name, Map<String, String> deploymentProperties);

	/**
	 * Retrieve the deployment state for the given stream definitions.
	 *
	 * @param streamDefinitions the list of Stream definitions to calculate the deployment states.
	 * @return the map containing the stream definitions and their deployment states.
	 */
	Map<StreamDefinition, DeploymentState> state(List<StreamDefinition> streamDefinitions);

	/**
	 * Update the stream using the UpdateStreamRequest.
	 *
	 * @param streamName the name of the stream to update
	 * @param updateStreamRequest the UpdateStreamRequest to use during the update
	 */
	void updateStream(String streamName, UpdateStreamRequest updateStreamRequest);

	/**
	 * Rollback the stream to the previous or a specific version of the stream.
	 *
	 * @param streamName the name of the stream to rollback
	 * @param releaseVersion the version to rollback to (if not specified, rollback to the previous deleted/deployed
	 * release version of the stream.
	 */
	void rollbackStream(String streamName, int releaseVersion);


	/**
	 * Un-deploys the stream identified by the given stream name.
	 *
	 * @param name the name of the stream to un-deploy
	 */
	void undeployStream(String name);

}
