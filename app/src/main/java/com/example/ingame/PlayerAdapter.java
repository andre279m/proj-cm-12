package com.example.ingame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    Context context;
    ArrayList<PlayerModel> list;

    public PlayerAdapter(Context context, ArrayList<PlayerModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.highscore_row_rview,parent,false);
        return new PlayerAdapter.PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        PlayerModel player = list.get(position);
        holder.name.setText(player.getName());
        holder.rank.setText(Integer.toString(player.getRank()));
        holder.score.setText(Integer.toString(player.getScore()));
    }

    @Override
    public int getItemCount() {
        return (list == null) ? 0 : list.size();
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder{

        TextView rank,name,score;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            rank = itemView.findViewById(R.id.rank);
            name = itemView.findViewById(R.id.name);
            score = itemView.findViewById(R.id.score);

        }
    }


}
