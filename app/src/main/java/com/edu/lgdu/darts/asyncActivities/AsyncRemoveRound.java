package com.edu.lgdu.darts.asyncActivities;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.edu.lgdu.darts.RoundActivity;
import com.edu.lgdu.darts.ServiceGenerator;
import com.edu.lgdu.darts.objects.ContestObject;
import com.edu.lgdu.darts.objects.RemoveObject;
import com.edu.lgdu.darts.services.RoundRemoveService;
import com.edu.lgdu.darts.services.RoundService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class AsyncRemoveRound extends AsyncTask<Void, Void, List> {
    RoundRemoveService roundRemoveService;
    Long lastRoundObjectId;
    String login;
    String password;
    ProgressBar progressBar;
    RemoveObject removeObject;
    public AsyncRemoveRound(RoundActivity roundActivity) {
        this.progressBar=roundActivity.getProgressBar();
        this.login = roundActivity.getLogin();
        this.password = roundActivity.getPassword();
        this.lastRoundObjectId=roundActivity.getLastRoundObjectId();
    }

    @Override
    protected List doInBackground(Void... voids) {
        roundRemoveService= ServiceGenerator.createService(RoundRemoveService.class, login, password);
        final Call<RemoveObject> removeObjectCall = roundRemoveService.resp(lastRoundObjectId);
        try {
            Response<RemoveObject> execute = removeObjectCall.execute();
            if (execute.isSuccessful()) {


                final RemoveObject body = execute.body();


                //   System.out.println("hh "+contestObjects.get(0).getId());

                // user object available
            } else {
                // error response, no access to resource?
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
