package com.edu.lgdu.darts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.edu.lgdu.darts.asyncActivities.AsyncGetRounds;
import com.edu.lgdu.darts.objects.ContestObject;
import com.edu.lgdu.darts.objects.RoundObject;

import java.util.ArrayList;

public class ContestActivity extends AppCompatActivity {
    ArrayList<String> contestNameList= new ArrayList<>();
    String login;
    String password;


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    ContestActivity contestActivity=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_match);
        Bundle b = getIntent().getExtras();
        contestNameList = b.getStringArrayList("contestNameList");
        login=b.getString("login");
        password=b.getString("password");
        final ProgressBar progressBar=findViewById(R.id.progressBar);
        final ListView returnList = findViewById(R.id.contestsList);
        returnList.setAdapter(new ArrayAdapter<String>(ContestActivity.this,
                android.R.layout.simple_list_item_1, contestNameList));
        progressBar.setVisibility(View.INVISIBLE);
        returnList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                progressBar.setVisibility(View.VISIBLE);
                String contestName=(String)parent.getItemAtPosition(position);
                AsyncGetRounds asyncGetRounds= new AsyncGetRounds(contestActivity,contestName, view.getContext(),progressBar);
                asyncGetRounds.execute();

            }
        });
    }
}
