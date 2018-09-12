package com.edu.lgdu.darts.services;

import com.edu.lgdu.darts.objects.RemoveObject;
import com.edu.lgdu.darts.objects.RoundObject;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RoundCreateService {
    @POST("/api/setRound")
    Call<RoundObject> resp(@Query("amount") int amount, @Query("photoPath") String photoPath, @Query("contest") String contest, @Query("player") String player);
}
