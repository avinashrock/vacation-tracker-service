package io.swagger.api.factories;

import io.swagger.api.LeaveApiService;
import io.swagger.api.impl.LeaveApiServiceImpl;


public class LeaveApiServiceFactory {
    private final static LeaveApiService service = new LeaveApiServiceImpl();

    public static LeaveApiService getLeaveApi() {
        return service;
    }
}
