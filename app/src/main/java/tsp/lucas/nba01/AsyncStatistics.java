package tsp.lucas.nba01;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import tsp.lucas.nba01.Activities.Statistics;

public class AsyncStatistics extends AsyncTask<String,Integer,List<Player>> {
    private static final String TAG = "AsyncStatistics";
    private Context context;
    private String stattype;

    public AsyncStatistics(Context context) {
        this.context = context;
    }

    @Override
    protected List<Player> doInBackground(String... strings) {
        try {
            int numbershown = Integer.parseInt(strings[0]);
            stattype = strings[1];
            String url = "https://stats.nba.com/stats/leagueleaders?LeagueID=00&PerMode=PerGame&StatCategory=" + stattype + "&Season=2018-19&SeasonType=Regular+Season&Scope=RS";
            String response = "";
            URLConnection conn = new URL(url).openConnection();
            InputStream in = conn.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
            String s = "";
            List<Player> rankings = new ArrayList<>();
            while ((s = buffer.readLine()) != null) {
                response += s;
            }
            JSONObject data = new JSONObject(response);
            JSONObject resultset = data.getJSONObject("resultSet");
            JSONArray rowSet = resultset.getJSONArray("rowSet");
            for (int j = 0; j < numbershown; j++) {
                JSONArray playerjson = rowSet.getJSONArray(j);
                Log.d(TAG, "doInBackground: " +  Float.valueOf(playerjson.getString(8)));
                Player player = new Player(playerjson.getString(2), Float.valueOf(playerjson.getString(8)),Float.valueOf(playerjson.getString(17)),Float.valueOf(playerjson.getString(18)),Float.valueOf(playerjson.getString(19)),Float.valueOf(playerjson.getString(20)),Float.valueOf(playerjson.getString(22)));
                rankings.add(player);
            }
            return rankings;

        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Player> players) {
        super.onPostExecute(players);
        Intent intent = new Intent(context, Statistics.class);
        if (players != null) {
            intent.putExtra("numberofplayers", players.size());
            for (int i = 0; i < players.size(); i++) {
                intent.putExtra("player" + i, (Parcelable) players.get(i));
            }
        }
        intent.putExtra("stattype", stattype);

        context.startActivity(intent);
    }
}
