package tsp.lucas.nba01.Fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import tsp.lucas.nba01.AsyncSourcePull;
import tsp.lucas.nba01.Game;

import java.util.*;

import tsp.lucas.nba01.Activities.CalendarActivity;
import tsp.lucas.nba01.R;


public class Gameview_fragment extends Fragment {
    private static final String TAG = "Gameview";

    private Button BtnCalendar;
    private ImageButton BtnAvant;
    private ImageButton BtnArriere;
    private RelativeLayout mRelativeLayout;
    private String date = null;
    private int today;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup viewpager, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gameview_layout, viewpager, false);
        final RelativeLayout mRelativeLayout = (RelativeLayout) view.findViewById(R.id.gameview_layout);
        Log.d(TAG, "onCreateView: Started.");

        BtnArriere = (ImageButton) view.findViewById(R.id.BtnArriere);
        BtnAvant = (ImageButton) view.findViewById(R.id.BtnAvant);
        BtnCalendar = (Button) view.findViewById(R.id.BtnCalendar);




        Intent incomingIntent = getActivity().getIntent();
        if (incomingIntent.hasExtra("date")) {
            date = incomingIntent.getStringExtra("date");
            int numberofgames = incomingIntent.getIntExtra("numberofgames", 0);
            Log.d(TAG, "onCreateView: " + numberofgames);
            List<Game> games = new ArrayList<>();
            for (int i = 0; i < numberofgames ; i++) {
                Game game = incomingIntent.getParcelableExtra("game" + i);
                Log.d(TAG, "onCreateView: " + game.getHour());

                games.add(game);
            }
            Boolean type = incomingIntent.getBooleanExtra("type",false);
            if (type) {
                mRelativeLayout.addView(setupScrollView(games,type));
            }
            else {
                mRelativeLayout.addView(setupScrollView(games,type));
            }
            String legitdate = date.substring(4,6) + "." + date.substring(6,8) + "." + date.substring(0,4) ;
            BtnCalendar.setText(legitdate);
        }


        BtnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalendarActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


    private ScrollView setupScrollView(List<Game> DaysGames, Boolean b) {

        ScrollView mscrollview = new ScrollView(this.getContext());
        RelativeLayout.LayoutParams scrollviewparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        scrollviewparams.addRule(RelativeLayout.BELOW,R.id.calendartable);
        mscrollview.setLayoutParams(scrollviewparams);

        TableLayout mtablelayout = new TableLayout(this.getContext());
        ScrollView.LayoutParams tablelayoutparams = new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.MATCH_PARENT);
        mtablelayout.setLayoutParams(tablelayoutparams);


        for (int j=0 ; j< DaysGames.size();j++){
            final String jours = date.substring(4,6) + "." + date.substring(6,8) + "." + date.substring(0,4) ;
            final String HT = DaysGames.get(j).getHomeTeam();
            final String AT = DaysGames.get(j).getAwayTeam();

            TableRow mtablerow = new TableRow(this.getContext());
            TableLayout.LayoutParams tablerowparams= new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
            mtablerow.setLayoutParams(tablerowparams);



            ImageView AwayTeamImage = new ImageView(getContext());
            AwayTeamImage.setPadding(0,10,0,10);
            String Awimg = "logo_" + AT.toLowerCase().replace(" ", "_");
            Log.d(TAG, "setupScrollView: " + Awimg);
            Drawable Awimage = getContext().getDrawable(getContext().getResources().getIdentifier(Awimg,"drawable", getContext().getPackageName()));
            AwayTeamImage.setImageDrawable(Awimage);
            TableRow.LayoutParams Awimageparams= new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
            Awimageparams.height = 200;
            Awimageparams.width = 200;
            AwayTeamImage.setLayoutParams(Awimageparams);
            mtablerow.addView(AwayTeamImage);

            if (b == true) {
                Button Match = new Button(this.getContext());
                TableRow.LayoutParams params= new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
                params.weight = 60;
                params.height = 200;
                Match.setLayoutParams(params);
                Match.setText(DaysGames.get(j).getscores()[0] + " - " + DaysGames.get(j).getscores()[1]);
                mtablerow.addView(Match);

                Match.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity()," Chargement video ", Toast.LENGTH_SHORT).show();

                        AsyncSourcePull task = new AsyncSourcePull(getContext());
                        task.execute(jours,HT,AT);
                    }
                });
            }
            else {
                TextView Match = new TextView(this.getContext());
                TableRow.LayoutParams params= new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
                params.weight = 60;
                params.height = 200;
                Match.setLayoutParams(params);
                Match.setGravity(17);
                Match.setText(jours + " à " + DaysGames.get(j).getHour());
                mtablerow.addView(Match);
            }

            ImageView HomeTeamImage = new ImageView(getContext());
            HomeTeamImage.setPadding(0,10,0,10);
            String Htimg = "logo_" + HT.toLowerCase().replace(" ", "_");
            Log.d(TAG, "setupScrollView: " + Htimg);
            Drawable Htimage = getContext().getDrawable(getContext().getResources().getIdentifier(Htimg,"drawable", getContext().getPackageName()));
            HomeTeamImage.setImageDrawable(Htimage);
            TableRow.LayoutParams Htimageparams= new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
            Htimageparams.height = 200;
            Htimageparams.width = 200;
            HomeTeamImage.setLayoutParams(Htimageparams);
            mtablerow.addView(HomeTeamImage);

            mtablelayout.addView(mtablerow,j);
        }

        mscrollview.addView(mtablelayout);
        return mscrollview;
    }


}

