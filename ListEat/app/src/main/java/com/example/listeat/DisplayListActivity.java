package com.example.listeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayListActivity extends AppCompatActivity {

    TextView textViewDisplayedListTitle;
    ListView listViewContentList;
    MyDBHandler dbHandler;
    ArrayList<String> listFromDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);

        textViewDisplayedListTitle = findViewById(R.id.textViewDisplayedListTitle);
        listViewContentList = findViewById(R.id.listViewContentList);

        dbHandler = new MyDBHandler(this, null, null, 1);
        String nomListe = getIntent().getStringExtra("nomListe");
        textViewDisplayedListTitle.setText(nomListe);

        listFromDB = dbHandler.getListFromDB(nomListe);
        //Récupération de la liste correspondant au nom "nomListe"
        final ArrayAdapter<String> displayedList = new ArrayAdapter<String>(DisplayListActivity.this, android.R.layout.simple_list_item_1, listFromDB );
        listViewContentList.setAdapter(displayedList);

    }

    public void addOrEditListFromDisplayList(View view) {
        Intent intent = new Intent(this,AddOrEditListActivity.class);
        intent.putExtra("nomList", textViewDisplayedListTitle.getText().toString());
        intent.putExtra("mesIngredients", listFromDB);
        startActivity(intent);
    }
}