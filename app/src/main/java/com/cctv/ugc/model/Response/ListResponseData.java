package com.cctv.ugc.model.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class ListResponseData<T> {
    @JsonProperty("count")
    private int count;

    @JsonProperty("page_count")
    private int pageCount;

    @JsonProperty("list")
    private T body;

    @JsonProperty("key_words")
    private Set<String> keyword;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Set<String> getKeyword() {
        return keyword;
    }

    public void setKeyword(Set<String> keyword) {
        this.keyword = keyword;
    }
}
