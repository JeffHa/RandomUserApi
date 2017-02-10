package com.funsies.jeffrey.randomuserapi.retrofit;

import com.funsies.jeffrey.randomuserapi.data.RandomUserResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomUserService {

    @GET("api/")
    Observable<RandomUserResponse> getRandomPerson();

    @GET("api/")
    Observable<RandomUserResponse> getRandomPeople(@Query("results") Integer howMany);
}
