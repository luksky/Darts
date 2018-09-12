package com.edu.lgdu.darts.asyncActivities;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.edu.lgdu.darts.RoundActivity;
import com.edu.lgdu.darts.ServiceGenerator;
import com.edu.lgdu.darts.objects.RoundObject;
import com.edu.lgdu.darts.services.RoundCreateService;
import com.edu.lgdu.darts.services.RoundRemoveService;
import com.edu.lgdu.darts.services.RoundUpdateService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class AsyncUpdateRound extends AsyncTask<Void, Void, List> {

    String login;
    String password;
    ProgressBar progressBar;
    int amount;
    String contestName;
    String photoPath;
    ArrayList<RoundObject> roundObjectArrayList=new ArrayList<>();
    Intent intent;
    RoundActivity roundActivity;
    long lastRoundObjectId;
    public AsyncUpdateRound(String amount, RoundActivity roundActivity, Intent intent,  long lastRoundObjectId) {
        this.roundActivity=roundActivity;
        this.amount=Integer.valueOf(amount);
        this.progressBar=roundActivity.getProgressBar();
        this.login = roundActivity.getLogin();
        this.password = roundActivity.getPassword();
        this.contestName=roundActivity.getContestName();
        this.roundObjectArrayList=roundActivity.getRoundObjectArrayList();
        this.intent=intent;
        this.lastRoundObjectId=lastRoundObjectId;
    }
    RoundUpdateService roundUpdateService;
    @Override
    protected ArrayList<RoundObject> doInBackground(Void... voids) {
        roundUpdateService = ServiceGenerator.createService(RoundUpdateService.class, login, password);
        RoundObject body;
        final Call<RoundObject> removeObjectCall = roundUpdateService.resp(lastRoundObjectId, amount, contestName,login);
        try {
            Response<RoundObject> execute = removeObjectCall.execute();
            if (execute.isSuccessful()) {

                body = execute.body();
                roundObjectArrayList.remove(roundObjectArrayList.size()-1);
                roundObjectArrayList.add(body);


                //   System.out.println("hh "+contestObjects.get(0).getId());

                // user object available
            } else {
                // error response, no access to resource?
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return roundObjectArrayList;
    }

    @Override
    protected void onPostExecute(List list) {
        super.onPostExecute(list);
        progressBar.setVisibility(View.INVISIBLE);
        roundActivity.finish();
        roundActivity.startActivity(intent);

    }
}
