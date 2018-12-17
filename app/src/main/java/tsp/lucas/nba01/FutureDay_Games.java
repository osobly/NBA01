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

public class FutureDay_Games extends AsyncTask<String, Integer, List<Game>> {
    private static final String TAG = "FutureDay_Games";
    private Context context;
    private String mdate;

    public FutureDay_Games(Context context) {
        this.context = context;
    }

    @Override
    protected List<Game> doInBackground(String... strings) {
        try {
            String date = strings[0];
            this.mdate = date;
            Log.d(TAG, "doInBackground: " + date);
            List<Game> games = new ArrayList<>();
            String response = "";
            String url = "http://data.nba.net/10s/prod/v1/" + date + "/scoreboard.json";
            URLConnection conn = new URL(url).openConnection();
            InputStream in = conn.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
            String s = "";
            while ((s = buffer.readLine()) != null) {
                response += s;
            }
            JSONObject data = new JSONObject(response);
            JSONArray array = data.getJSONArray("games");
            for (int i=0; i < array.length(); i++) {
                JSONObject gamejson = array.getJSONObject(i);
                Log.d(TAG, "doInBackground: " + gamejson.getJSONObject("hTeam").getInt("teamId"));
                String hTeam = getTeamfromId(gamejson.getJSONObject("hTeam").getInt("teamId"));
                String vTeam = getTeamfromId(gamejson.getJSONObject("vTeam").getInt("teamId"));
                Game game = new Game(gamejson.getInt("gameId"),date,gamejson.getString("startTimeEastern"), hTeam,vTeam);
                games.add(game);
            }
            return games;
        }
        catch (Exception e) {
            Log.d(TAG, "doInBackground: " + e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Game> games) {
        super.onPostExecute(games);
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("date", mdate);
        intent.putExtra("type", false);
        if (games != null) {
            intent.putExtra("numberofgames", games.size());
            for (int i = 0; i < games.size(); i++) {
                intent.putExtra("game" + i, (Parcelable) games.get(i));
            }
        }
        else {
            intent.putExtra("numberofgames" , 0);
        }

        context.startActivity(intent);
    }

    private String getTeamfromId(int id) throws IOException, JSONException {
        String response = "";
        URLConnection conn = new URL("http://data.nba.net/10s/prod/v2/2018/teams.json").openConnection();
        InputStream in = conn.getInputStream();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
        String s = "";
        while ((s = buffer.readLine()) != null) {
            response += s;
        }
        JSONObject data = new JSONObject(response);
        JSONObject league = data.getJSONObject("league");
        JSONArray standard = league.getJSONArray("standard");
        for (int i=0; i < standard.length(); i++) {
            JSONObject teamjson = standard.getJSONObject(i);
            if (teamjson.getInt("teamId") == id) {
                Log.d(TAG, "getTeamfromId: " + teamjson.getString("fullName"));
                return teamjson.getString("fullName");
            }
        }
        return null;
    }
}