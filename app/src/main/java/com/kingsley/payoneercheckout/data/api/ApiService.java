package com.kingsley.payoneercheckout.data.api;

import com.kingsley.payoneercheckout.data.model.NetworkResponse;
import com.kingsley.payoneercheckout.data.model.Result;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiService {
    @GET("optile/checkout-android/develop/shared-test/lists/listresult.json")
    Single<NetworkResponse> getCheckoutOptions();
}
