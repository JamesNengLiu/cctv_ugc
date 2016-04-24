package com.cctv.ugc.model.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yangyang on 16/4/24.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse extends UgcResponse{

    @JsonProperty("username")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
