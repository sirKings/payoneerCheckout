package com.kingsley.payoneercheckout.ui;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kingsley.payoneercheckout.data.model.NetworkResponse;
import com.kingsley.payoneercheckout.data.model.Result;
import com.kingsley.payoneercheckout.data.repository.Repository;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

@HiltViewModel
public class MainViewModel extends ViewModel {

    public static String TAG = "MainViewModel";

    private final Repository repository;
    private MutableLiveData<List<Result>> results;
    private MutableLiveData<Boolean> loading;
    private MutableLiveData<String> loadingError;

    @Inject
    public MainViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<Result>> getCheckoutOptions() {
        if (results == null) {
            results = new MutableLiveData<>();
            loadResult();
        }
        return results;
    }

    public LiveData<Boolean> getLoadingStatus(){
        if(loading == null){
            loading = new MutableLiveData<>(false);
        }
        return loading;
    }

    public LiveData<String> getLoadingError(){
        if(loadingError == null){
            loadingError = new MutableLiveData<>();
        }
        return loadingError;
    }

    private void loadResult() {
        loading.postValue(true);
        // Do an asynchronous operation to fetch users.
        Disposable subscribe = repository.getCheckoutOptions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError);
    }


    private void handleResults(NetworkResponse response) {
        results.postValue(response.getNetwork().getItems());
        loading.postValue(false);
    }

    private void handleError(Throwable error) {
        loading.postValue(false);
        String errorMsg;
        Log.e("Error", error.getLocalizedMessage());
        if(error instanceof HttpException) {
            HttpException httpException = (HttpException) error;
            if(httpException.code() == 400){
                Log.d(TAG, "onError: BAD REQUEST");
                errorMsg = "BAD REQUEST";
            }else if(httpException.code() == 401){
                Log.d(TAG, "onError: NOT AUTHORIZED");
                errorMsg = "NOT AUTHORIZED";
            }else if(httpException.code() == 403){
                Log.d(TAG, "onError: FORBIDDEN");
                errorMsg = "FORBIDDEN";
            }else if(httpException.code() == 404){
                Log.d(TAG, "onError: NOT FOUND");
                errorMsg = "NOT FOUND";
            }else if(httpException.code() == 500){
                Log.d(TAG, "onError: INTERNAL SERVER ERROR");
                errorMsg = "INTERNAL SERVER ERROR";
            }else if(httpException.code() == 502){
                Log.d(TAG, "onError: BAD GATEWAY");
                errorMsg = "BAD GATEWAY";
            }else{
                errorMsg = "SOME THING WENT WRONG";
            }
        }else{
            errorMsg =  error.getLocalizedMessage();
        }
        loadingError.postValue(errorMsg);
    }
}
