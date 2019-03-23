package com.bapspatil.moodtracker.adapter;
/*
 ** Created by Bapusaheb Patil {@link https://bapspatil.com}
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bapspatil.moodtracker.R;
import com.bapspatil.moodtracker.util.Constants;

import java.util.ArrayList;

public class MoodsAdapter extends RecyclerView.Adapter<MoodsAdapter.MoodViewHolder> {

    private Context mContext;
    private ArrayList<Integer> mMoods;
    private ArrayList<String> mComments;

    public MoodsAdapter(Context context, ArrayList<Integer> moods, ArrayList<String> comments) {
        this.mContext = context;
        this.mMoods = moods;
        this.mComments = comments;
    }

    @NonNull
    @Override
    public MoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mood, viewGroup, false);
        return new MoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoodViewHolder moodViewHolder, int i) {
        switch (i) {
            case 0:
                moodViewHolder.daysTextView.setText(R.string.today);
                break;
            case 1:
                moodViewHolder.daysTextView.setText(R.string.yesterday);
                break;
            default:
                String daysAgoText = i + " " + mContext.getString(R.string.days_ago);
                moodViewHolder.daysTextView.setText(daysAgoText);
        }

        int mood = mMoods.get(i);
        LinearLayout.LayoutParams leftLayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams rightLayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        float moodWeight;
        switch (mood) {
            case 0:
                moodWeight = 0.2f;
                break;
            case 1:
                moodWeight = 0.4f;
                break;
            case 2:
                moodWeight = 0.6f;
                break;
            case 3:
                moodWeight = 0.8f;
                break;
            case 4:
                moodWeight = 1.0f;
                break;
            default:
                moodWeight = 0.8f;
        }
        leftLayoutParams.weight = moodWeight;
        rightLayoutParams.weight = 1.0f - moodWeight;
        moodViewHolder.leftFrameLayout.setLayoutParams(leftLayoutParams);
        moodViewHolder.rightFrameLayout.setLayoutParams(rightLayoutParams);

        moodViewHolder.leftFrameLayout.setBackgroundResource(Constants.moodColorsArray[mood]);

        final String comment = mComments.get(i);
        if (comment != null && !comment.isEmpty()) {
            moodViewHolder.commentButton.setVisibility(View.VISIBLE);
            moodViewHolder.commentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, comment, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            moodViewHolder.commentButton.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mMoods.size();
    }

    public class MoodViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout leftFrameLayout;
        private FrameLayout rightFrameLayout;
        private ImageButton commentButton;
        private TextView daysTextView;

        public MoodViewHolder(View itemView) {
            super(itemView);

            leftFrameLayout = itemView.findViewById(R.id.left_frame_layout);
            rightFrameLayout = itemView.findViewById(R.id.right_frame_layout);
            commentButton = itemView.findViewById(R.id.btn_show_comment);
            daysTextView = itemView.findViewById(R.id.tv_days);
        }
    }
}
