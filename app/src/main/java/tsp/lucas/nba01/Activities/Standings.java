package tsp.lucas.nba01.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tsp.lucas.nba01.R;
import tsp.lucas.nba01.Team;

public class Standings extends AppCompatActivity {
    private static final String TAG = "Standings";
    private ExpandableListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);
        mListView = findViewById(R.id.listviewstandings);
        Intent incomingIntent = getIntent();
        ArrayList<Team> teamseast = new ArrayList<>();
        ArrayList<Team> teamswest = new ArrayList<>();
        for (int i = 0; i < 30 ; i++) {
            if (i%2 == 0) {
                Team team = incomingIntent.getParcelableExtra("team"+i);
                teamswest.add(team);
            }
            else {
                Team team = incomingIntent.getParcelableExtra("team"+i);
                teamseast.add(team);
            }

        }

        HashMap<String, List<Team>> conferences = new HashMap<>();
        conferences.put("West", teamswest);
        conferences.put("East", teamseast);
        List<String> titles = new ArrayList<>();
        titles.add("West");
        titles.add("East");

        final CustomExpandableListAdapter adapter = new CustomExpandableListAdapter(getApplicationContext(),titles,conferences );
        mListView.setAdapter(adapter);


    }

    public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

        private Context context;
        private List<String> expandableListTitle;
        private HashMap<String, List<Team>> expandableListDetail;

        public CustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                           HashMap<String, List<Team>> expandableListDetail) {
            this.context = context;
            this.expandableListTitle = expandableListTitle;
            this.expandableListDetail = expandableListDetail;
        }

        @Override
        public Object getChild(int listPosition, int expandedListPosition) {
            return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                    .get(expandedListPosition);
        }

        @Override
        public long getChildId(int listPosition, int expandedListPosition) {
            return expandedListPosition;
        }

        @Override
        public View getChildView(int listPosition, final int expandedListPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            final Team teamchild = (Team) getChild(listPosition, expandedListPosition);
            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.row_item_standings, null);
            }
            String dname = "logo_" + teamchild.getName().toLowerCase().replace(" ", "_");
            Drawable dlogo = getDrawable(getResources().getIdentifier(dname,"drawable" , getPackageName()));
            ImageView logo = (ImageView) convertView.findViewById(R.id.logo);
            logo.setImageDrawable(dlogo);
            TextView teamname = (TextView) convertView
                    .findViewById(R.id.teamname);
            teamname.setText(teamchild.getName());
            TextView winloss = (TextView) convertView.findViewById(R.id.winloss);
            winloss.setText(teamchild.getWins() + " - " + teamchild.getLosses() + " | " + teamchild.getWinpercentage());
            return convertView;
        }

        @Override
        public int getChildrenCount(int listPosition) {
            return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                    .size();
        }

        @Override
        public Object getGroup(int listPosition) {
            return this.expandableListTitle.get(listPosition);
        }

        @Override
        public int getGroupCount() {
            return this.expandableListTitle.size();
        }

        @Override
        public long getGroupId(int listPosition) {
            return listPosition;
        }

        @Override
        public View getGroupView(int listPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            String listTitle = (String) getGroup(listPosition);
            if (convertView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.context.
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.list_group, null);
            }
            TextView listTitleTextView = (TextView) convertView
                    .findViewById(R.id.listTitle);
            listTitleTextView.setTypeface(null, Typeface.BOLD);
            listTitleTextView.setText(listTitle);
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int listPosition, int expandedListPosition) {
            return true;
        }
    }


}
