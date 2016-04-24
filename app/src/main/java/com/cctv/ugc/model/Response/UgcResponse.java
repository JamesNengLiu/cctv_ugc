package com.cctv.ugc.model.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yangyang on 16/4/24.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UgcResponse {

    @JsonProperty("result")
    private int result;

    @JsonProperty("desc")
    private String desc;

    public UgcResponse() {
        this.result = -1;
    }

    public boolean ok(){
        return result >=0;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
