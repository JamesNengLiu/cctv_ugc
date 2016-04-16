package com.cctv.ugc.network;

import android.text.TextUtils;

import com.cctv.ugc.Util.DebugLog;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Rest log interceptor
 */
public class RestLogInterceptor implements ClientHttpRequestInterceptor {

    public static final String SET_TICKET = "Set-Ticket";

    public static final char TICKET_SPLITTER = ';';

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        DebugLog.log("RestLogInterceptor", "Request url:" + httpRequest.getURI().toString());
        if(DebugLog.DBG){
            HttpHeaders requestHeaders = httpRequest.getHeaders();
//            requestHeaders.set("Ticket", "vwixpxVv85mydCtLisfc8SiXSBY9jh43");
            DebugLog.log("RestLogInterceptor", "Request headers:" + requestHeaders);
        }
        ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, bytes);
        if(DebugLog.DBG) {
            DebugLog.log("RestLogInterceptor", "Response status code:" + response.getStatusCode());
        }
        return response;
    }
}
