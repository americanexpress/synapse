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
package com.americanexpress.synapse.data.couchbase.config;

import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.auditing.EnableCouchbaseAuditing;
import org.springframework.data.couchbase.repository.support.IndexManager;

import java.util.Collections;
import java.util.List;

/**
 * <code>BaseDataCouchbaseConfig</code> class is used to set the configurations to connect to our couchbase instance.
 *
 * @author Gabriel Jimenez
 */
@Configuration
@EnableCouchbaseAuditing
public class BaseDataCouchbaseConfig extends AbstractCouchbaseConfiguration {

    private final Environment environment;

    @Autowired
    public BaseDataCouchbaseConfig(Environment environment) {
        this.environment = environment;
    }

    //Defaults here.
    private Integer couchbaseRuntimeMetricsCollectorConfig = 30;
    private Integer couchbaseNetworkLatency = 30;
    private Integer couchbaseConnectTimeout = 20000;
    private Integer couchbaseSocketConnectTimeout = 60000;
    private Integer couchbaseKVTimeout = 75000;
    private Integer replicationTimeout = 60000;
    private Integer couchbaseQueryTimeout = 1000;
    private long keepAliveTimeout = 2500L;
    private Boolean couchbaseMutationTokenEnabled = true;
    private Boolean compressionEnabled = false;


    @Override
    public String typeKey() {
        return "_dataType";
    }

    @Override
    protected List<String> getBootstrapHosts() {
        return Collections.singletonList(environment.getRequiredProperty("couchbase.bootstraphosts"));
    }

    @Override
    public String getBucketName() {
        return environment.getRequiredProperty("couchbase.bucketname");
    }

    @Override
    protected String getUsername() {
        return environment.getRequiredProperty("couchbase.username");
    }

    @Override
    protected String getBucketPassword() {
        return environment.getRequiredProperty("couchbase.password");
    }


    @Override
    protected CouchbaseEnvironment getEnvironment() {
        return DefaultCouchbaseEnvironment.builder()
                .connectTimeout(getCouchbaseConnectTimeout())
                .queryTimeout(getCouchbaseQueryTimeout())
                .kvTimeout(getCouchbaseKVTimeout())
                .socketConnectTimeout(getCouchbaseSocketConnectTimeout())
                .keepAliveTimeout(getKeepAliveTimeout())
                .compressionEnabled(getCompressionEnabled())
                .build();
    }


    @Bean
    @Override
    public IndexManager indexManager() {
        return new IndexManager(false, false, false);
    }


    public Integer getCouchbaseRuntimeMetricsCollectorConfig() {
        return couchbaseRuntimeMetricsCollectorConfig;
    }

    public void setCouchbaseRuntimeMetricsCollectorConfig(Integer couchbaseRuntimeMetricsCollectorConfig) {
        this.couchbaseRuntimeMetricsCollectorConfig = couchbaseRuntimeMetricsCollectorConfig;
    }

    public Integer getCouchbaseNetworkLatency() {
        return couchbaseNetworkLatency;
    }

    public void setCouchbaseNetworkLatency(Integer couchbaseNetworkLatency) {
        this.couchbaseNetworkLatency = couchbaseNetworkLatency;
    }

    public Integer getCouchbaseConnectTimeout() {
        return couchbaseConnectTimeout;
    }

    public void setCouchbaseConnectTimeout(Integer couchbaseConnectTimeout) {
        this.couchbaseConnectTimeout = couchbaseConnectTimeout;
    }

    public Integer getCouchbaseSocketConnectTimeout() {
        return couchbaseSocketConnectTimeout;
    }

    public void setCouchbaseSocketConnectTimeout(Integer couchbaseSocketConnectTimeout) {
        this.couchbaseSocketConnectTimeout = couchbaseSocketConnectTimeout;
    }

    public Integer getCouchbaseKVTimeout() {
        return couchbaseKVTimeout;
    }

    public void setCouchbaseKVTimeout(Integer couchbaseKVTimeout) {
        this.couchbaseKVTimeout = couchbaseKVTimeout;
    }

    public Integer getReplicationTimeout() {
        return replicationTimeout;
    }

    public void setReplicationTimeout(Integer replicationTimeout) {
        this.replicationTimeout = replicationTimeout;
    }

    public Boolean getCouchbaseMutationTokenEnabled() {
        return couchbaseMutationTokenEnabled;
    }

    public void setCouchbaseMutationTokenEnabled(Boolean couchbaseMutationTokenEnabled) {
        this.couchbaseMutationTokenEnabled = couchbaseMutationTokenEnabled;
    }

    public Integer getCouchbaseQueryTimeout() {
        return couchbaseQueryTimeout;
    }

    public void setCouchbaseQueryTimeout(Integer couchbaseQueryTimeout) {
        this.couchbaseQueryTimeout = couchbaseQueryTimeout;
    }

    public long getKeepAliveTimeout() {
        return keepAliveTimeout;
    }

    public void setKeepAliveTimeout(long keepAliveTimeout) {
        this.keepAliveTimeout = keepAliveTimeout;
    }

    public Boolean getCompressionEnabled() {
        return compressionEnabled;
    }

    public void setCompressionEnabled(Boolean compressionEnabled) {
        this.compressionEnabled = compressionEnabled;
    }
}

