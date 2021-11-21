package com.kingsley.payoneercheckout.data.api;

import com.kingsley.payoneercheckout.data.model.NetworkResponse;
import com.kingsley.payoneercheckout.data.model.Result;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ApiHelperImpl implements ApiHelper {

    private final ApiService apiService;


    @Inject
    public ApiHelperImpl(ApiService apiService) {
        this.apiService = apiService;
    }


    @Override
    public Single<NetworkResponse> getCheckoutOptions() {
        return apiService.getCheckoutOptions();
    }
}
