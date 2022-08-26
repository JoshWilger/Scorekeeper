package com.example.scorekeeper_v1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlayersActivity extends AppCompatActivity {

    RecyclerView recyclerPlayers;
    ArrayList<Player> playersList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        recyclerPlayers = findViewById(R.id.recycler_players);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerPlayers.setLayoutManager(layoutManager);

        playersList = (ArrayList<Player>) getIntent().getExtras().getSerializable("list");

        recyclerPlayers.setAdapter(new PlayerAdapter(playersList));
    }
}