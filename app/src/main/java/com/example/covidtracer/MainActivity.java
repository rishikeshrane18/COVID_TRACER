package com.example.covidtracer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    public static TextView wccases,wacases,wrcases,wdcases,iccases,iacases,ircases,idcases;

    public static int WORLD_OPT = 1;
    public static int INDIA_OPT = 2;

    public void activityShifter(View view){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        wccases = findViewById(R.id.textView2);
        wacases = findViewById(R.id.textView4);
        wrcases = findViewById(R.id.textView6);
        wdcases = findViewById(R.id.textView8);
        iccases = findViewById(R.id.textView10);
        iacases = findViewById(R.id.textView12);
        ircases = findViewById(R.id.textView14);
        idcases = findViewById(R.id.textView16);


        JsonRetriver task = new JsonRetriver(WORLD_OPT);
        task.execute("https://api.thevirustracker.com/free-api?global=stats");
        JsonRetriver task_india = new JsonRetriver(INDIA_OPT);
        task_india.execute("https://api.thevirustracker.com/free-api?countryTotal=IN");

       /* Calendar calendar = Calendar.getInstance();
        Intent intent1 = new Intent(MainActivity.this,AlarmReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am =(AlarmManager)this.getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),60000,pendingIntent);*/
       setNotification();

    }

    private void setNotification() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,AlarmReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 360000, pendingIntent);
    }

    public static void populate_world(String jsonData){
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            JSONObject data = jsonArray.getJSONObject(0);
            wccases.setText(data.getString("total_cases"));
            wacases.setText(data.getString("total_unresolved"));
            wrcases.setText(data.getString("total_recovered"));
            wdcases.setText(data.getString("total_deaths"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public static void populate_india(String jsonData){
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("countrydata");
            JSONObject data = jsonArray.getJSONObject(0);
            iccases.setText(data.getString("total_cases"));
            iacases.setText(data.getString("total_serious_cases"));
           ircases.setText(data.getString("total_recovered"));
           idcases.setText(data.getString("total_deaths"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
  /*  public static class AlarmReciever extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            long when = System.currentTimeMillis();
            NotificationManager notificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);

            Intent notificationIntent = new Intent(context, MainActivity.class);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(context).setSmallIcon(R.drawable.ic_virus)
                    .setContentTitle("COVID STATS")
                    .setContentText("CHECK COVID TRACER APP FOR UPDATE IN COVID CASES")
                    .setAutoCancel(true)
                    .setWhen(when)
                    .setContentIntent(pendingIntent);
            notificationManager.notify(250,mNotifyBuilder.build());

        }
    }*/
}
  /*  Calendar calendar = Calendar.getInstance();
    Intent intent1 = new Intent(MainActivity.this,AlarmReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
    AlarmManager am =(AlarmManager)this.getSystemService(this.ALARM_SERVICE);
   am.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),2*60*60,pendingIntent);
           }
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .g   Calendar calendar = Calendar.getInstance();
   Intent intent1 = new Intent(MainActivity.this,AlarmReceiver.class);
   PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
   AlarmManager am =(AlarmManager)this.getSystemService(this.ALARM_SERVICE);
   am.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),2*60*60,pendingIntent);
 }
 public class AlarmReceiver extends BroadcastReceiver{
     @Override
     public void onReceive(Context context, Intent intent) {
         long when = System.currentTimeMillis();
         NotificationManager notificationManager = (NotificationManager) context
                 .getSystemservice(Context.NOTIFICATION_SERVICE);
         Intent notificationIntent = new Intent(context,MainActivity.class);
         notificationIntent,setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         PendingIntent pendingIntent = PendingIntent.getActivity(context,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
         NotificationCompat.Builder mnNotifyBuilder = new NotificationCompat.Builder(context).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("TEXT").setContentText("TEXT")
                 .setWhen(when).setContentIntent(pendingIntent)
          notificationManager.notify(25,mnNotifyBuilder.build());
     }
 }etSystemservice(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(context,MainActivity.class);
        notificationIntent,setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mnNotifyBuilder = new NotificationCompat.Builder(context).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("TEXT").setContentText("TEXT")
                .setWhen(when).setContentIntent(pendingIntent)
        notificationManager.notify(25,mnNotifyBuilder.build());
    }
}*/