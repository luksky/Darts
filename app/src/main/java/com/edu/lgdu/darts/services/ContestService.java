package com.edu.lgdu.darts.services;

import com.edu.lgdu.darts.objects.ContestObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ContestService {
    @POST("/api/getPlayerContests")
    Call<ArrayList<ContestObject>> resp(@Query("login") String login);
}
