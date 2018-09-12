package com.edu.lgdu.darts.asyncActivities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.edu.lgdu.darts.ContestActivity;
import com.edu.lgdu.darts.RoundActivity;
import com.edu.lgdu.darts.ServiceGenerator;
import com.edu.lgdu.darts.objects.RoundObject;
import com.edu.lgdu.darts.services.RoundService;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class AsyncRefreshRounds extends AsyncTask<Void, Void, ArrayList> {
    String login;
    String password;
    ProgressBar progressBar;
    String contestName;
    RoundService roundService;
    ArrayList<RoundObject> roundObjectArrayList;
    Intent intent;
    RoundActivity roundActivity;
    ArrayList<RoundObject> body = new ArrayList<>();
    public AsyncRefreshRounds(RoundActivity roundActivity, Intent intent) {
        this.roundActivity=roundActivity;
        this.progressBar=roundActivity.getProgressBar();
        this.login = roundActivity.getLogin();
        this.password = roundActivity.getPassword();
        this.contestName=roundActivity.getContestName();
        this.roundObjectArrayList=roundActivity.getRoundObjectArrayList();
        this.intent=intent;
    }


    @Override
    protected ArrayList<RoundObject> doInBackground(Void... voids) {
        roundService = ServiceGenerator.createService(RoundService.class, login, password);
        final Call<ArrayList<RoundObject>> roundObjectCall = roundService.resp(login, contestName);

        try {
            Response<ArrayList<RoundObject>> execute = roundObjectCall.execute();
            if (execute.isSuccessful()) {


                body.addAll(execute.body());

                // user object available
            } else {
                // error response, no access to resource?
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return body;
    }

    @Override
    protected void onPostExecute(ArrayList list) {
        System.out.println("onPostStart");
        roundObjectArrayList.clear();
        roundObjectArrayList.addAll(body);
        progressBar.setVisibility(View.INVISIBLE);
        roundActivity.finish();
        roundActivity.startActivity(intent);
        super.onPostExecute(body);
    }
}
