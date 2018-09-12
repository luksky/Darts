package com.edu.lgdu.darts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.edu.lgdu.darts.asyncActivities.AsyncCreateRound;
import com.edu.lgdu.darts.asyncActivities.AsyncGetRounds;
import com.edu.lgdu.darts.asyncActivities.AsyncLogin;
import com.edu.lgdu.darts.asyncActivities.AsyncRefreshRounds;
import com.edu.lgdu.darts.asyncActivities.AsyncRemoveRound;
import com.edu.lgdu.darts.asyncActivities.AsyncUpdateRound;
import com.edu.lgdu.darts.objects.RoundObject;
import com.edu.lgdu.darts.services.ContestService;
import com.edu.lgdu.darts.services.RoundService;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class RoundActivity extends AppCompatActivity {

    String login;
    String password;
    String contestName;
    ProgressBar progressBar;
    RoundActivity roundActivity=this;
    Context tableLayoutContext;
    ArrayList<RoundObject> roundObjectArrayList;
    Long lastRoundObjectId;
    private TableLayout tableLayout;
    private Context context=this.getContext();

    public ArrayList<RoundObject> getRoundObjectArrayList() {
        return roundObjectArrayList;
    }

    public void addRoundObjectArrayList(RoundObject roundObject) {
        this.roundObjectArrayList.add(roundObject);
    }

    public Long getLastRoundObjectId() {
        return lastRoundObjectId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getContestName() {
        return contestName;
    }

    public TableLayout getTableLayout() {
        return tableLayout;
    }

    public Context getContext() {
        return context;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public Context getTableLayoutContext() {
        return tableLayoutContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounds);
        Bundle b = getIntent().getExtras();

        login = b.getString("login");
        password = b.getString("password");
        contestName=b.getString("contestName");
        roundObjectArrayList=(ArrayList<RoundObject>)getIntent().getSerializableExtra("roundObjectArrayList");
        tableLayout=findViewById(R.id.tableLayout);
        progressBar=findViewById(R.id.progressBar);
        tableLayoutContext=tableLayout.getContext();
        for (int i = 0; i < roundObjectArrayList.size(); i++) {
            View tableRow = View.inflate(tableLayoutContext, R.layout.activity_row_rounds, null);
//            tableLayout.addView(tableRow);
            TextView history_display_no = (TextView) tableRow.findViewById(R.id.history_display_no);
            TextView history_player_login = (TextView) tableRow.findViewById(R.id.history_player_login);
            TextView history_display_amount = (TextView) tableRow.findViewById(R.id.history_display_amount);
            TextView history_display_total = (TextView) tableRow.findViewById(R.id.history_display_total);
            TextView history_display_photo = (TextView) tableRow.findViewById(R.id.history_display_photo);

            history_display_no.setText(String.valueOf(i+1));
            history_player_login.setText(String.valueOf(roundObjectArrayList.get(i).getLogin()));
            history_display_amount.setText(String.valueOf(roundObjectArrayList.get(i).getAmount()));
            history_display_total.setText(String.valueOf(roundObjectArrayList.get(i).getFullAmount()));
            history_display_photo.setText(String.valueOf(roundObjectArrayList.get(i).getPhotoPath()));
            if(roundObjectArrayList.get(i).getLogin().equals(login)){
                tableRow.setBackgroundColor(Color.CYAN);
            }
            tableLayout.addView(tableRow);
        }

        lastRoundObjectId=roundObjectArrayList.get(roundObjectArrayList.size()-1).getId();


        final Button removeButton=findViewById(R.id.btn_remove_round);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                AsyncRemoveRound asyncRemoveRound= new AsyncRemoveRound(roundActivity);
                asyncRemoveRound.execute();
                roundObjectArrayList.remove(roundObjectArrayList.size()-1);
                progressBar.setVisibility(View.INVISIBLE);
                finish();
                startActivity(getIntent());
            }
        });
        final TextInputEditText inputAmount=findViewById(R.id.input_amount);
        final Button addButton=findViewById(R.id.btn_add_round);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                Intent intent=getIntent();
                AsyncCreateRound asyncCreateRound=new AsyncCreateRound(inputAmount.getText().toString(),roundActivity,intent);
                asyncCreateRound.execute();

            }
        });


        final ImageButton refreshButton=findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                Intent intent=getIntent();
                AsyncRefreshRounds asyncRefreshRounds= new AsyncRefreshRounds(roundActivity,intent);
                asyncRefreshRounds.execute();
            }
        });

    }


}
