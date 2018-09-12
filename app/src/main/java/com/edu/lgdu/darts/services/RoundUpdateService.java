package com.edu.lgdu.darts.services;

import com.edu.lgdu.darts.objects.RoundObject;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RoundUpdateService {
    @POST("/api/updateRound")
    Call<RoundObject> resp(@Query("id") long id, @Query("amount") int amount,  @Query("contest") String contest, @Query("player") String player);
}
