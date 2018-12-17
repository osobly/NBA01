package tsp.lucas.nba01.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import tsp.lucas.nba01.Player;
import tsp.lucas.nba01.R;

public class Statistics extends AppCompatActivity {
    private static final String TAG = "Statistics";
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        mListView = (ListView) findViewById(R.id.listviewstatistics);
        Intent incomingIntent = getIntent();
        ArrayList<Player> players = new ArrayList<>();
        int size = incomingIntent.getIntExtra("numberofplayers", 0);
        for (int i = 0; i < size ; i++) {
            Player player = incomingIntent.getParcelableExtra("player"+i);
            players.add(player);
        }
        String stattype = incomingIntent.getStringExtra("stattype");
        final CustomAdapter adapter = new CustomAdapter(players, stattype, getApplicationContext());
        mListView.setAdapter(adapter);


    }

    private class CustomAdapter extends ArrayAdapter<Player> {

        private ArrayList<Player> dataSet;
        Context mContext;
        private String stattype;

        private class ViewHolder {
            TextView rank;
            TextView playername;
            TextView stat;
        }

        public CustomAdapter(ArrayList<Player> data, String stattype, Context context) {
            super(context, R.layout.row_item_statistics, data);
            this.dataSet = data;
            this.mContext=context;
            this.stattype = stattype;

        }


        private int lastPosition = -1;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Player dataModel = getItem(position);
            ViewHolder viewHolder;

            final View result;

            if (convertView == null) {

                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.row_item_statistics, parent, false);
                viewHolder.rank = (TextView) convertView.findViewById(R.id.playerrank);
                viewHolder.playername = (TextView) convertView.findViewById(R.id.playername);
                viewHolder.stat = (TextView) convertView.findViewById(R.id.playerstat);

                result=convertView;

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }

            viewHolder.rank.setText(Integer.toString(position + 1));
            viewHolder.playername.setText(dataModel.getName());
            switch (stattype) {
                case "pts":
                    viewHolder.stat.setText(String.valueOf(dataModel.getPts()));
                    break;
                case "reb":
                    viewHolder.stat.setText(String.valueOf(dataModel.getReb()));
                    break;
                case "ast":
                    viewHolder.stat.setText(String.valueOf(dataModel.getAst()));
                    break;
                case "stl":
                    viewHolder.stat.setText(String.valueOf(dataModel.getStl()));
                    break;
                case "blk":
                    viewHolder.stat.setText(String.valueOf(dataModel.getBlk()));
                    break;
            }

            return convertView;
        }
    }
}
