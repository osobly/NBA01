package tsp.lucas.nba01.Activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import tsp.lucas.nba01.FutureDay_Games;
import tsp.lucas.nba01.PastDay_Games;
import tsp.lucas.nba01.R;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        final CalendarView mCalendarView = findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @TargetApi(Build.VERSION_CODES.O)
            @Override
            public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {
                String s = "" + dayOfMonth;
                if(dayOfMonth < 10){
                     s = "0" + s;
                }
                month = month +1;
                String m = "" + month;
                if (month < 10){
                    m = "0" + m;
                }
                String date = year + m + s;
                long Caldate = mCalendarView.getDate();
                Date d = new Date(Caldate);
                DateFormat df = new SimpleDateFormat("yyyy"+ "MM" + "dd");
                String d2 = df.format(d);
                int aujint = Integer.parseInt(d2);
                int selecint = Integer.parseInt(date);
                if (aujint > selecint) {
                    PastDay_Games task = new PastDay_Games(getApplicationContext());
                    task.execute(date);
                }
                else {
                    FutureDay_Games task = new FutureDay_Games(getApplicationContext());
                    task.execute(date);
                }
            }
        });
    }






}
