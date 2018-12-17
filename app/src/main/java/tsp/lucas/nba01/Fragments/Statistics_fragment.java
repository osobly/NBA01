package tsp.lucas.nba01.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tsp.lucas.nba01.Activities.Standings;
import tsp.lucas.nba01.Activities.Statistics;
import tsp.lucas.nba01.AsyncStandings;
import tsp.lucas.nba01.AsyncStatistics;
import tsp.lucas.nba01.Player;
import tsp.lucas.nba01.R;
import tsp.lucas.nba01.SectionsStatePagerAdapter;
import tsp.lucas.nba01.Team;

public class Statistics_fragment extends Fragment {
    private static final String TAG = "Statistics";
    private Button BtnStandings;
    private TextInputLayout numberinput;
    private TextInputLayout statinput;
    private Button BtnStats;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup viewpager, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistics_layout, viewpager, false);
        Log.d(TAG, "onCreateView: Started.");
        BtnStandings = (Button) view.findViewById(R.id.BtnStandings);
        numberinput = view.findViewById(R.id.numberinput);
        statinput = view.findViewById(R.id.statinput);
        BtnStats = (Button) view.findViewById(R.id.BtnStats);

        BtnStandings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncStandings task = new AsyncStandings(getContext());
                task.execute();
            }
        });

        BtnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validenumberinput() | !validestatinput()) {
                    return;
                }
                AsyncStatistics task = new AsyncStatistics(getContext());
                task.execute(numberinput.getEditText().getText().toString(),statinput.getEditText().getText().toString());
            }
        });

        return view;
    }

    private boolean validenumberinput() {
        int temp = Integer.parseInt(numberinput.getEditText().getText().toString().trim());
        if (0 < temp && temp < 240) {
            numberinput.setError(null);
            return true;
        }
        else {
            numberinput.setError("Invalid number");
            return false;
        }
    }

    private boolean validestatinput() {
        String temp = statinput.getEditText().getText().toString().trim();
        String[] possibilities = {"pts", "reb", "ast", "stl", "blk"};
        List<String> ok = Arrays.asList(possibilities);
        if (ok.contains(temp)) {
            statinput.setError(null);
            return true;
        }
        else {
            statinput.setError("Invalid stat type");
            return false;
        }
    }


}

