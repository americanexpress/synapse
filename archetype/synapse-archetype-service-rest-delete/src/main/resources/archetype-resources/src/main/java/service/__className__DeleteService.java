package ${package}.service;

import io.americanexpress.synapse.service.rest.service.BaseDeleteService;
import org.springframework.stereotype.Service;

/**
 * {@code ${className}DeleteService} class deletes an entity
 * based on the request given.
 * @author ${author}
 */
@Service
public class ${className}DeleteService extends BaseDeleteService {

    /**
     * Remove a resource.
     *
     * @param id identifier received from controller
     */
    @Override
    protected void executeDelete(String id) {
    }
}
