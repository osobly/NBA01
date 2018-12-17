package tsp.lucas.nba01.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import tsp.lucas.nba01.R;
import tsp.lucas.nba01.YouTubeConfig;

public class YouTubePlayerActivity extends YouTubeBaseActivity {

    private YouTubePlayerView mYouTubePlayerView;
    private YouTubePlayer.OnInitializedListener mOnInitializedListener;
    private static final String TAG = "YouTubeBaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_player);

        mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.YouTubePlayer);
        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Intent incomingIntent = getIntent();
                String video = incomingIntent.getStringExtra("video");
                Log.d(TAG, "onInitializationSuccess: " + video);
                youTubePlayer.loadVideo(video);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        mYouTubePlayerView.initialize(YouTubeConfig.getApiKey(), mOnInitializedListener);

    }
}
