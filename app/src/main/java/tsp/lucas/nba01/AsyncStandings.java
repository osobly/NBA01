package tsp.lucas.nba01;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import tsp.lucas.nba01.Activities.MainActivity;
import tsp.lucas.nba01.Activities.Standings;

public class AsyncStandings extends AsyncTask<Void,Integer,List<Team>> {
    private static final String TAG = "AsyncStandings";
    private Context context;

    public AsyncStandings(Context context) {
        this.context = context;
    }

    @Override
    protected List<Team> doInBackground(Void... voids) {
        try {
            List<Team> Standings = new ArrayList<>();
            String response = "";
            String url = "http://data.nba.net/10s/prod/v1/current/standings_conference.json";
            URLConnection conn = new URL(url).openConnection();
            InputStream in = conn.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
            String s = "";
            while ((s = buffer.readLine()) != null) {
                response += s;
            }
            JSONObject data = new JSONObject(response);
            JSONObject conference = data.getJSONObject("league").getJSONObject("standard").getJSONObject("conference");
            JSONArray Eastarray = conference.getJSONArray("east");
            JSONArray Westarray = conference.getJSONArray("west");
            for (int i = 0; i < 15; i++) {
                JSONObject eastteamjson = Eastarray.getJSONObject(i);
                JSONObject westteamjson = Westarray.getJSONObject(i);
                Team teamwest = new Team(westteamjson.getInt("teamId"),getTeambyid(westteamjson.getInt("teamId")),westteamjson.getInt("win"), westteamjson.getInt("loss"), westteamjson.getDouble("winPctV2"));
                Team teameast = new Team(eastteamjson.getInt("teamId"),getTeambyid(eastteamjson.getInt("teamId")),eastteamjson.getInt("win"), eastteamjson.getInt("loss"), eastteamjson.getDouble("winPctV2"));
                Standings.add(teamwest);
                Standings.add(teameast);
            }

            return Standings;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPostExecute(List<Team> teams) {
        super.onPostExecute(teams);
        Intent intent = new Intent(context, Standings.class);
        if (teams != null) {
            for (int i = 0; i < teams.size(); i++) {
                intent.putExtra("team" + i, (Parcelable) teams.get(i));
            }
        }

        context.startActivity(intent);
    }

    private String getTeambyid(int id) throws IOException, JSONException {
        String response = "";
        String url = "http://data.nba.net/10s/prod/v2/2018/teams.json";
        URLConnection conn = new URL(url).openConnection();
        InputStream in = conn.getInputStream();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
        String s = "";
        while ((s = buffer.readLine()) != null) {
            response += s;
        }
        JSONObject data = new JSONObject(response);
        JSONObject league = data.getJSONObject("league");
        JSONArray standard = league.getJSONArray("standard");
        for (int i = 0; i < standard.length() ; i++) {
            JSONObject teamjson = standard.getJSONObject(i);
            if (teamjson.getInt("teamId") == id) {
                return teamjson.getString("fullName");
            }
        }
        return null;
    }
}
