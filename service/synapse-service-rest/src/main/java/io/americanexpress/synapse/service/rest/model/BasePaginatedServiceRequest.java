package io.americanexpress.synapse.service.rest.model;

/**
 * BasePaginatedServiceRequest should be used when pagination is needed in a Poly Controller.
 */
public abstract class BasePaginatedServiceRequest implements BaseServiceRequest {

    /**
     * Used for services that support pagination.
     */
    private PageInformation pageInformation;

    /**
     * Get the pageInformation.
     *
     * @return the pageInformation
     */
    public PageInformation getPageInformation() {
        return pageInformation;
    }

    /**
     * Set the pageInformation.
     *
     * @param pageInformation the pageInformation to set
     */
    public void setPageInformation(PageInformation pageInformation) {
        this.pageInformation = pageInformation;
    }
}
