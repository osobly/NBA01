package tsp.lucas.nba01.Fragments;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;

import tsp.lucas.nba01.R;

public class Soundboard_fragment extends Fragment {
    private static final String TAG = "Settings";
    private MediaPlayer ShaqSound;
    private MediaPlayer AISound;
    private MediaPlayer LBJSound;
    private MediaPlayer KGSound;
    private MediaPlayer ZZSound;
    private MediaPlayer KLSound;

    private ImageButton ShaqBtn;
    private ImageButton AIBtn;
    private ImageButton LBJBtn;
    private ImageButton KGBtn;
    private ImageButton ZZBtn;
    private ImageButton KLBtn;

    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup viewpager, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_layout, viewpager, false);
        Log.d(TAG, "onCreateView: Started.");

        ShaqBtn = (ImageButton) view.findViewById(R.id.ShaqBtn);
        AIBtn =(ImageButton) view.findViewById(R.id.AIBtn);
        LBJBtn =(ImageButton) view.findViewById(R.id.LBJBtn);
        KGBtn = (ImageButton) view.findViewById(R.id.KGBtn);
        ZZBtn = (ImageButton) view.findViewById(R.id.ZZBtn);
        KLBtn = (ImageButton) view.findViewById(R.id.KLBtn);

        ShaqSound = MediaPlayer.create(this.getContext(), R.raw.shaq_canyoudigit);
        AISound = MediaPlayer.create(this.getContext(), R.raw.ai_practice);
        LBJSound = MediaPlayer.create(this.getContext(), R.raw.lebron_southbeach);
        KGSound = MediaPlayer.create(this.getContext(), R.raw.dark);
        ZZSound = MediaPlayer.create(this.getContext(), R.raw.zaza_game7);
        KLSound = MediaPlayer.create(this.getContext(), R.raw.kl_laugh);

        ShaqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShaqSound.isPlaying())
                {
                    ShaqSound.stop();
                    try {
                        ShaqSound.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    ShaqSound.start();
                }
            }
        });

        AIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AISound.isPlaying())
                {
                    AISound.stop();
                    try {
                        AISound.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    AISound.start();
                }
            }
        });

        LBJBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LBJSound.isPlaying())
                {
                    LBJSound.stop();
                    try {
                        LBJSound.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    LBJSound.start();
                }
            }
        });

        KGBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (KGSound.isPlaying())
                {
                    KGSound.stop();
                    try {
                        KGSound.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    KGSound.start();
                }
            }
        });

        ZZBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ZZSound.isPlaying())
                {
                    ZZSound.stop();
                    try {
                        ZZSound.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    ZZSound.start();
                }
            }
        });

        KLBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (KLSound.isPlaying())
                {
                    KLSound.stop();
                    try {
                        KLSound.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    KLSound.start();
                }
            }
        });

        return view;
    }

}

