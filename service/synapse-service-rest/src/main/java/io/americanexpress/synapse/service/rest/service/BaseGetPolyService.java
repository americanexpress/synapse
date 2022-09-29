package io.americanexpress.synapse.service.rest.service;

import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import org.springframework.data.domain.Page;

public abstract class BaseGetPolyService<O extends BaseServiceResponse> extends BaseService {

    public Page<O> read() {
        logger.entry();

        Page<O> response = executeRead();

        logger.exit(response);

        return response;
    }

    protected abstract Page<O> executeRead();
}
