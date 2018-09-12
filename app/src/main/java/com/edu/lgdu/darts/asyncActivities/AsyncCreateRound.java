package com.edu.lgdu.darts.asyncActivities;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.edu.lgdu.darts.RoundActivity;
import com.edu.lgdu.darts.ServiceGenerator;
import com.edu.lgdu.darts.objects.RemoveObject;
import com.edu.lgdu.darts.objects.RoundObject;
import com.edu.lgdu.darts.services.RoundCreateService;
import com.edu.lgdu.darts.services.RoundRemoveService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class AsyncCreateRound  extends AsyncTask<Void, Void, List> {
    RoundRemoveService roundRemoveService;
    String login;
    String password;
    ProgressBar progressBar;
    int amount;
    String contestName;
    String photoPath;
    ArrayList<RoundObject> roundObjectArrayList=new ArrayList<>();
    Intent intent;
    RoundActivity roundActivity;
    public AsyncCreateRound(String amount, RoundActivity roundActivity, Intent intent) {
        this.roundActivity=roundActivity;
        this.amount=Integer.valueOf(amount);
        this.progressBar=roundActivity.getProgressBar();
        this.login = roundActivity.getLogin();
        this.password = roundActivity.getPassword();
        this.contestName=roundActivity.getContestName();
        this.roundObjectArrayList=roundActivity.getRoundObjectArrayList();
        this.intent=intent;
    }
    RoundCreateService roundCreateService;
    @Override
    protected ArrayList<RoundObject> doInBackground(Void... voids) {
        roundCreateService = ServiceGenerator.createService(RoundCreateService.class, login, password);
        RoundObject body;
        final Call<RoundObject> removeObjectCall = roundCreateService.resp(amount,photoPath, contestName,login);
        try {
            Response<RoundObject> execute = removeObjectCall.execute();
            if (execute.isSuccessful()) {

                body = execute.body();
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
