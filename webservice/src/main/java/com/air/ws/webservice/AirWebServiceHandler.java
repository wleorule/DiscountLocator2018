package com.air.ws.webservice;

public interface AirWebServiceHandler {
    void onDataArrived(Object result, boolean ok, long timestamp);
}
