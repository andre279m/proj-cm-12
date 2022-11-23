package com.example.ingame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HighscoreAdapter extends BaseAdapter {
    Context context;
    String nameList[];
    int scoreList[];
    LayoutInflater layoutInflater;

    public HighscoreAdapter(Context context, String[] nameList, int[] scoreList) {
        this.context = context;
        this.nameList = nameList;
        this.scoreList = scoreList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return scoreList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View highScoreView = layoutInflater.inflate(R.layout.activity_custom_list_view, null);
        TextView nameView = (TextView) highScoreView.findViewById(R.id.name);
        TextView scoreView = (TextView) highScoreView.findViewById(R.id.score);
        nameView.setText(nameList[position]);
        scoreView.setText(Integer.toString(scoreList[position]));

        return highScoreView;
    }
}
