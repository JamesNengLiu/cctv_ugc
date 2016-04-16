package com.cctv.ugc.network;

import com.cctv.ugc.model.Response.VideoTimelineResponse;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;


@EBean(scope = EBean.Scope.Singleton )
public class UgcClient {

    @RestService
    UgcRests rests;

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
