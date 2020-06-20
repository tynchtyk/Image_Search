package com.example.image_search.Service.Network;


import android.util.Log;

import com.example.image_search.BuildConfig;
import com.example.image_search.Service.Model.Response_Data;

import java.io.IOException;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {
    public static ApiInterface createSearchService() {
        return getClient().create(ApiInterface.class);
    }

    private static Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl(ApiConstants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(makeOkHttpClient())
                .build();
    }

    private static OkHttpClient makeOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {

                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .header(ApiConstants.API_HEADER_AUTHORIZATION, ApiConstants.API_KEY)
                                .method(original.method(), original.body())
                                .build();
                        //Log.e("APIFACTORY", String.valueOf(chain.proceed(request)));
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(makeLoggingInterceptor())
                .build();
    }


    private static HttpLoggingInterceptor makeLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        // enable logging
        boolean isDebug = BuildConfig.DEBUG;
        loggingInterceptor.setLevel(isDebug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        return loggingInterceptor;
    }

}
