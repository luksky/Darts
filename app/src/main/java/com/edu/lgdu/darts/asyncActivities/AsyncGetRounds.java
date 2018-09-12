package com.edu.lgdu.darts.asyncActivities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.edu.lgdu.darts.ContestActivity;
import com.edu.lgdu.darts.R;
import com.edu.lgdu.darts.RoundActivity;
import com.edu.lgdu.darts.ServiceGenerator;
import com.edu.lgdu.darts.objects.ContestObject;
import com.edu.lgdu.darts.objects.RoundObject;
import com.edu.lgdu.darts.services.RoundService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class AsyncGetRounds extends AsyncTask<Void, Void, ArrayList> {
    RoundService roundService;
    ArrayList<RoundObject> roundObjectArrayList = new ArrayList<>();
    String contestName;
    String login;
    String password;
    ProgressBar progressBar;
    Context context;
    ContestActivity contestActivity;

    final ArrayList<RoundObject> body = new ArrayList<>();

    public AsyncGetRounds(ContestActivity contestActivity,String contestName, Context context,ProgressBar progressBar) {
        this.contestActivity=contestActivity;
        this.contestName = contestName;
        this.progressBar=progressBar;
        this.login = contestActivity.getLogin();
        this.password = contestActivity.getPassword();
        this.context=context;
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
        roundObjectArrayList.addAll(body);
        Intent i = new Intent(context, RoundActivity.class);
        Bundle b=new Bundle();
        b.putString("contestName", contestName);
        b.putString("login",login);
        b.putString("password",password);
        i.putExtra("roundObjectArrayList",roundObjectArrayList);
        i.putExtras(b);
        context.startActivity(i);
        progressBar.setVisibility(View.INVISIBLE);
        super.onPostExecute(body);
    }
}
