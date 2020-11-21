package com.example.listeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleDialog.ExampleDialogListener {
    MyDBHandler dbHandler;
    ListView listsListView;

    ArrayList<String> maListe = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listsListView = findViewById(R.id.listViewLists);
        dbHandler = new MyDBHandler(this, null, null, 1);
        maListe = dbHandler.databaseToString();

        final ArrayAdapter<String> listViewAdapterMaListe = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, maListe);
        listsListView.setAdapter(listViewAdapterMaListe);

        listsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                displayContent(maListe.get(position));
            }
        });

        listsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String nomList = maListe.get(position);
                openDialog(nomList);
                return true;
            }
        });

    }

    public void addOrEditList(View view) {
        Intent intent = new Intent(this,AddOrEditListActivity.class);
        intent.putExtra("nomList","");
        startActivity(intent);
    }

    public void displayContent(String nomListe) {
        Intent intent = new Intent(this,DisplayListActivity.class);
        intent.putExtra("nomListe", nomListe );
        startActivity(intent);
    }


    @Override
    public void deleteList(String nomList) {
        dbHandler.deleteList(nomList);
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "You deleted " + nomList + " with success.", Toast.LENGTH_LONG).show();

    }

    private void openDialog(String nomList) {
        ExampleDialog exampleDialog = new ExampleDialog();
        Bundle bundle = new Bundle();
        bundle.putString("nomList", nomList);
        exampleDialog.setArguments(bundle);
        exampleDialog.show(getSupportFragmentManager(), "Example dialog");

    }

    @Override
    public void finish() {
        Intent intent = getIntent();
        super.finish();
        startActivity(intent);
    }
}
