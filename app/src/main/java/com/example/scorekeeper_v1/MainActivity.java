package com.example.scorekeeper_v1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatSpinner;

import android.graphics.Color;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutList;
    Button buttonSubmitList;

    List<String> colorList = new ArrayList<>();
    ArrayList<Player> playersList = new ArrayList<>();

//    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        layoutList = findViewById(R.id.layout_list);
        buttonSubmitList = findViewById(R.id.button_winners);

        buttonSubmitList.setOnClickListener(this);

        colorList.add("White");
        colorList.add(("Silver"));
        colorList.add(("Gray"));
        colorList.add(("Red"));
        colorList.add(("Yellow"));
        colorList.add(("Lime"));
        colorList.add(("Aqua"));
        colorList.add(("Magenta"));
        colorList.add(("Olive"));
        colorList.add(("Teal"));
        colorList.add(("Purple"));

        addView();

//        txt = (TextView) findViewById(R.id.text1);
//        parseXML();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_player) {
            addView();
        }
        if (id == R.id.action_reset) {
            for (int i = 0; i < layoutList.getChildCount(); i++) {
                View playerView = layoutList.getChildAt(i);
                EditText editTextScore = (EditText) playerView.findViewById(R.id.edit_score);

                editTextScore.setText("0");
            }
        }
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_info) {
            Intent intent = new Intent(this, InfoActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.spinner_color:
                addView();
                break;

            case R.id.button_winners:
                if(checkIfValidAndRead()){
                    Intent intent = new Intent(MainActivity.this,PlayersActivity.class);
                    Bundle bundle = new Bundle();

                    Collections.sort(playersList, Player.sortByScore);
                    bundle.putSerializable("list",playersList);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
        }
    }

    private boolean checkIfValidAndRead() {
        playersList.clear();
        boolean result = true;

        for (int i = 0; i < layoutList.getChildCount(); i++) {

            View playerView = layoutList.getChildAt(i);

            LinearLayout background = (LinearLayout) playerView.findViewById(R.id.background);
            EditText editTextName = (EditText) playerView.findViewById(R.id.edit_player_name);
            EditText editTextScore = (EditText) playerView.findViewById(R.id.edit_score);
            AppCompatSpinner spinnerColor = (AppCompatSpinner) playerView.findViewById(R.id.spinner_color);

            Player player = new Player();

            background.setBackgroundColor(Color.parseColor(colorList.get(spinnerColor.getSelectedItemPosition())));

            if (!editTextName.getText().toString().equals("")) {
                player.setPlayerName(editTextName.getText().toString());
            } else {
                result = false;
                break;
            }

            if (!editTextScore.getText().toString().equals("")) {
                player.setPlayerScore(Integer.toString(Integer.parseInt(editTextScore.getText().toString())));
            } else {
                result = false;
                break;
            }

            if (spinnerColor.getSelectedItemPosition() >= 0) {
                player.setPlayerColor(colorList.get(spinnerColor.getSelectedItemPosition()));
            } else {
                result = false;
                break;
            }

            playersList.add(player);

        }
        if(playersList.size()==0){
            result = false;
            Toast.makeText(this, "Add Players First!", Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show();
        }

        return result;
    }

    private void addView() {

        final View playerView = getLayoutInflater().inflate(R.layout.row_add_player,null,false);

        LinearLayout background = (LinearLayout) playerView.findViewById(R.id.background);
        EditText editText = (EditText)playerView.findViewById(R.id.edit_player_name);
        AppCompatSpinner spinnerColor = (AppCompatSpinner)playerView.findViewById(R.id.spinner_color);
        ImageView imageClose = (ImageView)playerView.findViewById(R.id.image_remove);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,colorList);
        spinnerColor.setAdapter(arrayAdapter);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(playerView);
            }
        });

        spinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                background.setBackgroundColor(Color.parseColor(colorList.get(spinnerColor.getSelectedItemPosition())));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        layoutList.addView(playerView);

    }

    private void removeView(View view){

        layoutList.removeView(view);

    }

/*
    private void parseXML() {
        XmlPullParserFactory parserFactory;
        try {
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            InputStream is = getAssets().open("data.xml");
            parserFactory.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);

//            processParsing(parser);

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processParsing(XmlPullParser parser) throws IOException, XmlPullParserException {
        ArrayList<LinearLayout> list = new ArrayList<>();
        int eventType = parser.getEventType();
        LinearLayout currentLL = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String eltName = null;

            switch (eventType) {
                case XmlPullParser.START_TAG:
                    eltName = parser.getName();
                    if ("LinearLayout".equals(eltName)) {
//                        currentLL = new LinearLayout();
//                        list.add(currentLL);
                        System.out.println("! " + eltName);
                    } else if (currentLL != null) {
                        System.out.println(eltName);
                    }
                    break;
            }
            eventType = parser.next();
        }
    }
*/


}