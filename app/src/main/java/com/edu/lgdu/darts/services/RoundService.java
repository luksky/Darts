package com.edu.lgdu.darts.services;

import com.edu.lgdu.darts.objects.ContestObject;
import com.edu.lgdu.darts.objects.RoundObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RoundService {
    @POST("/api/getRoundsByName")
    Call<ArrayList<RoundObject>> resp(@Query("login") String login,@Query("contestName") String contestNme);
}
