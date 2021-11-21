package com.kingsley.payoneercheckout.data.repository;

import com.kingsley.payoneercheckout.data.api.ApiHelper;
import com.kingsley.payoneercheckout.data.model.NetworkResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class Repository {

    private final ApiHelper apiHelper;

    @Inject
    public Repository(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    public Single<NetworkResponse> getCheckoutOptions(){
        return apiHelper.getCheckoutOptions();
    }
}
