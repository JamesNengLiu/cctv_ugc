package com.cctv.ugc.network;

import com.cctv.ugc.model.Response.LoginResponse;
import com.cctv.ugc.model.Response.VideoTimelineResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;


@EBean(scope = EBean.Scope.Singleton )
public class UgcClient {

    @RestService
    UgcRests rests;

    ObjectMapper mapper;

    public UgcClient() {
        mapper = new ObjectMapper();
    }

    public LoginResponse login(String username, String password){
        LoginResponse response = new LoginResponse();
        try {
            String result = rests.login(username);
            response = mapper.readValue(result, LoginResponse.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    public VideoTimelineResponse fetchVideoTimeline(int offset, int count){
        VideoTimelineResponse response = new VideoTimelineResponse();
        try {
            response = rests.fetchRecommendTimeline(offset, count);
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
