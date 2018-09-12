package com.edu.lgdu.darts.services;

import com.edu.lgdu.darts.objects.RemoveObject;
import com.edu.lgdu.darts.objects.RoundObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RoundRemoveService {
    @POST("/api/setRound")
    Call<RemoveObject> resp(@Query("Id") Long id);
}
