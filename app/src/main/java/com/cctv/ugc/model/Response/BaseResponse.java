package com.cctv.ugc.model.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"errInfo"})
public class BaseResponse<T> {
    public BaseResponse() {
        super();
        this.code = -1;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @JsonProperty("data")
    private T data;

    private int code;
    public boolean ok(){
        return 0==code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
