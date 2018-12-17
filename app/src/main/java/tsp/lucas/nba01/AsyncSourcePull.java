package tsp.lucas.nba01;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import tsp.lucas.nba01.Activities.YouTubePlayerActivity;

public class AsyncSourcePull extends AsyncTask<String, Integer, String> {
    private static final String TAG = "AsyncSourcePull";
    private Context context;

    public AsyncSourcePull(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            String date = strings[0];
            String HT = strings[1];
            String AT = strings[2];
            String video = "";

            Connection.Response response= Jsoup.connect("https://m.youtube.com/playlist?list=PL5j8RirTTnK5rfAPFJFwaqJvLweQynhjq")
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Linux; Android 8.1.0; Redmi Note 5 Build/OPM1.171019.011; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/70.0.3538.110 Mobile Safari/537.36 GSA/8.49.4.21.arm64")
                    .referrer("http://www.google.com")
                    .timeout(12000)
                    .followRedirects(true)
                    .execute();
            Document doc = response.parse();
            Element scripts = doc.select("div#initial-data").first();
            int sizes = scripts.toString().length();
            String jsonstring = scripts.toString().substring(30, sizes-11);
            JSONObject JsonObj = new JSONObject(jsonstring);
            JSONObject contents = JsonObj.getJSONObject("contents").getJSONObject("singleColumnBrowseResultsRenderer");
            JSONObject tab = contents.getJSONArray("tabs").getJSONObject(0);
            JSONObject ok = tab.getJSONObject("tabRenderer").getJSONObject("content").getJSONObject("sectionListRenderer").getJSONArray("contents").getJSONObject(0).getJSONObject("itemSectionRenderer").getJSONArray("contents").getJSONObject(0).getJSONObject("playlistVideoListRenderer");
            JSONArray listvideo = ok.getJSONArray("contents");
            Log.d(TAG, "doInBackground: " + date);
            for (int i = 0 ; i<listvideo.length(); i++) {
                String videoid = listvideo.getJSONObject(i).getJSONObject("playlistVideoRenderer").get("videoId").toString();
                String videotitle = listvideo.getJSONObject(i).getJSONObject("playlistVideoRenderer").getJSONObject("title").getJSONArray("runs").getJSONObject(0).get("text").toString();
                Log.d(TAG, "doInBackground: " + (videotitle.contains(date) && (videotitle.contains(HT + " vs " + AT) || videotitle.contains(AT + " vs " + HT))));
                if (videotitle.contains(date) && (videotitle.contains(HT + " vs " + AT) || videotitle.contains(AT + " vs " + HT))) {
                    video = videoid;

                    Log.d(TAG, "doInBackground: ok");
                }

            }

            Log.d(TAG, "doInBackground: " + video);
            return (video);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String video) {
        super.onPostExecute(video);
        Log.d(TAG, "onPostExecute: ok" + video);
        Intent intent = new Intent(context, YouTubePlayerActivity.class);
        intent.putExtra("video", video);
        context.startActivity(intent);
    }
}