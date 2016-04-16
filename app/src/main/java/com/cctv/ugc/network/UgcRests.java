package com.cctv.ugc.network;

import com.cctv.ugc.model.Response.VideoTimelineResponse;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Rest(rootUrl = UrlConstants.REST_ROOT, converters = {MappingJackson2HttpMessageConverter.class}, interceptors = {RestLogInterceptor.class})
public interface UgcRests {

    @Get(UrlConstants.RECOMMEND_TIMELINE_URL+"&offset={offset}&page_size={pageSize}")
    VideoTimelineResponse fetchRecommendTimeline(int offset, int pageSize);

}

