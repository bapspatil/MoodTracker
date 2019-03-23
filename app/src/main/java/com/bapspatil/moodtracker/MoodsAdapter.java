package com.bapspatil.moodtracker;
/*
 ** Created by Bapusaheb Patil {@link https://bapspatil.com}
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MoodsAdapter extends RecyclerView.Adapter<MoodsAdapter.MoodViewHolder> {
    private Context mContext;
    private int mCurrentDay;
    private int[] mMoods;
    private String[] mComments;

    public MoodsAdapter(Context context, int currentDay, int[] moods, String[] comments) {
        this.mContext = context;
        this.mCurrentDay = currentDay;
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
            case 5:
                moodViewHolder.daysTextView.setText(R.string.yesterday);
                break;
            case 6:
                moodViewHolder.daysTextView.setText(R.string.today);
                break;
            default:
                String daysAgoText = i + mContext.getString(R.string.days_ago);
                moodViewHolder.daysTextView.setText(daysAgoText);
        }

        int mood = mMoods[i];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        float weight;
        switch (mood) {
            case 0:
                weight = 0.2f;
                break;
            case 1:
                weight = 0.4f;
                break;
            case 2:
                weight = 0.6f;
                break;
            case 3:
                weight = 0.8f;
                break;
            case 4:
                weight = 1.0f;
                break;
            default:
                weight = 0.8f;
        }
        layoutParams.weight = weight;
        moodViewHolder.parentLinearLayout.setLayoutParams(layoutParams);
        moodViewHolder.parentLinearLayout.setBackgroundResource(Constants.moodColorsArray[mood]);

        final String comment = mComments[i];
        if (comment != null && !comment.isEmpty()) {
            moodViewHolder.commentButton.setVisibility(View.VISIBLE);
            moodViewHolder.commentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, comment, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            moodViewHolder.commentButton.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mMoods.length;
    }

    public class MoodViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout parentLinearLayout;
        private ImageButton commentButton;
        private TextView daysTextView;

        public MoodViewHolder(View itemView) {
            super(itemView);

            parentLinearLayout = itemView.findViewById(R.id.parent_linear_layout);
            commentButton = itemView.findViewById(R.id.btn_show_comment);
            daysTextView = itemView.findViewById(R.id.tv_days);
        }
    }
}
