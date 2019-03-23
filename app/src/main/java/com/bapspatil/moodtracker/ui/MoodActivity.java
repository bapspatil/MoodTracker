package com.bapspatil.moodtracker.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bapspatil.moodtracker.util.Constants;
import com.bapspatil.moodtracker.R;
import com.bapspatil.moodtracker.data.SharedPreferencesHelper;
import com.bapspatil.moodtracker.receiver.UpdateDayReceiver;

import java.util.Calendar;

public class MoodActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private RelativeLayout parentRelativeLayout;
    private ImageView moodImageView;
    private ImageButton addCommentButton;
    private ImageButton moodHistoryButton;
    private GestureDetectorCompat mDetector;

    private SharedPreferences mPreferences;
    private int currentDay;
    private int currentMoodIndex;
    private String currentComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        parentRelativeLayout = findViewById(R.id.parent_relative_layout);
        moodImageView = findViewById(R.id.iv_mood);
        addCommentButton = findViewById(R.id.btn_add_comment);
        moodHistoryButton = findViewById(R.id.btn_mood_history);

        mDetector = new GestureDetectorCompat(this, this);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        currentDay = mPreferences.getInt(SharedPreferencesHelper.KEY_CURRENT_DAY, 1);
        currentMoodIndex = mPreferences.getInt(SharedPreferencesHelper.KEY_CURRENT_MOOD, 3);
        currentComment = mPreferences.getString(SharedPreferencesHelper.KEY_CURRENT_COMMENT, "");

        changeUiForMood(currentMoodIndex);

        scheduleAlarm();

        addCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(MoodActivity.this);
                editText.setHint("Comment");
                AlertDialog alertDialog = new AlertDialog.Builder(MoodActivity.this)
                        .setView(editText)
                        .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!editText.getText().toString().isEmpty()) {
                                    SharedPreferencesHelper.saveComment(editText.getText().toString(), currentDay, mPreferences);
                                }
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });

        moodHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoodActivity.this, MoodHistoryActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onDown(MotionEvent e) {
        // Not needed for this project.
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // Not needed for this project.
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // Not needed for this project.
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        // Not needed for this project.
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // Not needed for this project.
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        // Swiping from bottom to top or, swiping up!
        if (e1.getY() - e2.getY() > 50) {
            if (currentMoodIndex < 4) {
                currentMoodIndex++;
                changeUiForMood(currentMoodIndex);
                SharedPreferencesHelper.saveMood(currentMoodIndex, currentDay, mPreferences);
            }
        }
        // Swiping from top to bottom, or swiping down!
        else if (e1.getY() - e2.getY() < 50) {
            if (currentMoodIndex > 0) {
                currentMoodIndex--;
                changeUiForMood(currentMoodIndex);
                SharedPreferencesHelper.saveMood(currentMoodIndex, currentDay, mPreferences);
            }
        }
        return true;
    }

    private void changeUiForMood(int moodIndex) {
        moodImageView.setImageResource(Constants.moodImagesArray[moodIndex]);
        parentRelativeLayout.setBackgroundResource(Constants.moodColorsArray[moodIndex]);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, Constants.moodSoundsArray[moodIndex]);
        mediaPlayer.start();
    }

    private void scheduleAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, UpdateDayReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
            );
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }
}
