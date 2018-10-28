package com.air.ws.webservice;

import com.air.ws.webservice.responses.AirWebServiceResponse;
import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;

import air18.foi.hr.database.entities.Discount;
import air18.foi.hr.database.entities.Store;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class AirWebServiceCaller {
    // retrofit object
    Retrofit retrofit;
    // base URL of the web service
    private final String baseUrl = "http://cortex.foi.hr/mtl/courses/air/";

    // constructor
    public AirWebServiceCaller(){

        OkHttpClient client = new OkHttpClient();

        // for debuggint use HttpInterceptor
        //client.interceptors().add(new HttpInterceptor());

        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

    }
    // get all records from a web service
    public void getAll(String method, final Type entityType){

        AirWebService serviceCaller = retrofit.create(AirWebService.class);
        Call<AirWebServiceResponse> call = serviceCaller.getStores(method);
        //TODO: fix get all to work with stores and discounts

        if(call != null){
            call.enqueue(new Callback<AirWebServiceResponse>() {
                @Override
                public void onResponse(Response<AirWebServiceResponse> response, Retrofit retrofit) {
                    try {
                        if(response.isSuccess()){

                            if(entityType == Store.class){
                                System.out.println("Got stores...");
                            } else if(entityType == Discount.class){
                                System.out.println("Got discounts...");
                            } else
                            {
                                System.out.println("Unrecognized class");
                            }
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });
        }

    }
}
