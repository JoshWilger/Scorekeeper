package com.example.scorekeeper_v1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerView> {

    ArrayList<Player> playersList = new ArrayList<>();

    public PlayerAdapter(ArrayList<Player> playersList) {
        this.playersList = playersList;
    }

    @NonNull
    @Override
    public PlayerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_player,parent,false);

        return new PlayerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerView holder, int position) {
        Player player = playersList.get(position);
        holder.textPlayerName.setText(player.getPlayerName());
        holder.textPlayerScore.setText(player.getPlayerScore());
        holder.textPlayerColor.setText(player.getPlayerColor());
    }

    @Override
    public int getItemCount() {
        return playersList.size();
    }

    public class PlayerView extends RecyclerView.ViewHolder{

        TextView textPlayerName, textPlayerScore, textPlayerColor;
        public PlayerView(@NonNull View itemView) {
            super(itemView);

            textPlayerName = (TextView)itemView.findViewById(R.id.text_player_name);
            textPlayerScore = (TextView)itemView.findViewById(R.id.text_player_score);
            textPlayerColor = (TextView)itemView.findViewById(R.id.text_player_color);

        }
    }
}
