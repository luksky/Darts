package com.edu.lgdu.darts.asyncActivities;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import com.edu.lgdu.darts.ContestActivity;
import com.edu.lgdu.darts.MainActivity;
import com.edu.lgdu.darts.objects.ContestObject;
import com.edu.lgdu.darts.services.ContestService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsyncLogin extends AsyncTask<Void, Void, List> {
    private ContestService contestService;
    private String login;
    private String password;
    private ArrayList<ContestObject> contestObjects=new ArrayList<>();
    Context context;
    ProgressBar progressBar;

    public AsyncLogin(ContestService contestService, MainActivity mainActivity, Context context) {
        this.contestService = contestService;
        this.login = mainActivity.getLogin();
        this.password = mainActivity.getPassword();
        this.progressBar=mainActivity.getProgressBar();
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList doInBackground(Void... voids) {
        final Call<ArrayList<ContestObject>> contestObjectCall = contestService.resp(login);
        try {
            Response<ArrayList<ContestObject>> execute = contestObjectCall.execute();
            if (execute.isSuccessful()) {


                final ArrayList<ContestObject> body = execute.body();
                contestObjects.addAll(body);

                //   System.out.println("hh "+contestObjects.get(0).getId());

                // user object available
            } else {
                // error response, no access to resource?
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return contestObjects;
    }

    @Override
    protected void onPostExecute(List s) {
        progressBar.setVisibility(View.INVISIBLE);
        ArrayList<String> contestNameList= new ArrayList<>();
        for(int i =0;i<contestObjects.size();i++){
            contestNameList.add(contestObjects.get(i).getContestName());
        }
        Intent i = new Intent(context, ContestActivity.class);
        Bundle b=new Bundle();
        b.putString("login",login);
        b.putString("password",password);
        b.putStringArrayList("contestNameList",contestNameList);
        i.putExtras(b);
        context.startActivity(i);
        super.onPostExecute(s);
    }
}
