package io.americanexpress.synapse.service.rest.controller.helpers;

import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class PolyResponseEntityCreator<O extends BaseServiceResponse> {

    public ResponseEntity<List<O>> create(Page<O> page, HttpServletResponse httpServletResponse) {
        final ResponseEntity<List<O>> responseEntity;
        List<O> pageContent = null;
        if (page != null) {
            pageContent = page.getContent();
        }
        if (page == null || CollectionUtils.isEmpty(pageContent)) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            setHeadersInResponse(page, httpServletResponse);
            responseEntity = new ResponseEntity<>(pageContent, HttpStatus.OK);
        }
        return responseEntity;
    }

    private void setHeadersInResponse(final Page<O> page, final HttpServletResponse httpServletResponse) {
        if (page != null && !CollectionUtils.isEmpty(page.getContent())) {
            httpServletResponse.setHeader("size", String.valueOf(page.getSize()));
            httpServletResponse.setHeader("page", String.valueOf(page.getNumber()));
            httpServletResponse.setHeader("total_results_count", String.valueOf(page.getNumberOfElements()));
        }
    }
}
