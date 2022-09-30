package io.americanexpress.service.book.rest.model;

import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;

import java.util.List;
import java.util.Objects;

public class ReadBookResponses extends BaseServiceResponse {

    List<ReadBookResponse> readBookResponseList;

    public ReadBookResponses(List<ReadBookResponse> readBookResponseList) {
        this.readBookResponseList = readBookResponseList;
    }

    public List<ReadBookResponse> getReadBookResponseList() {
        return readBookResponseList;
    }

    public void setReadBookResponseList(List<ReadBookResponse> readBookResponseList) {
        this.readBookResponseList = readBookResponseList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadBookResponses that = (ReadBookResponses) o;
        return Objects.equals(readBookResponseList, that.readBookResponseList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(readBookResponseList);
    }

    @Override
    public String toString() {
        return "ReadBookResponses{" +
                "readBookResponseList=" + readBookResponseList +
                '}';
    }
}
