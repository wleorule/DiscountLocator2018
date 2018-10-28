package com.air.ws.webservice;

import com.air.ws.webservice.responses.AirWebServiceResponse;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface AirWebService {
    @FormUrlEncoded
    @POST("stores.php")
    Call<AirWebServiceResponse> getStores(@Field("method") String method);

    @FormUrlEncoded
    @POST("discounts.php")
    Call<AirWebServiceResponse> getDiscounts(@Field("method") String method);
}
