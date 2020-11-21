package com.example.weathermaptd2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String maCle = "0d49b72b6e091b3b6c38802dda18151e";
    String cle1 = "b6907d289e10d714a6e88b30761fae22";
    String urlDEb = "https://samples.openweathermap.org/data/2.5/weather?";
    EditText nomVille;
    TextView temperature;
    Double temp=0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nomVille =findViewById(R.id.nomVille);
        temperature =findViewById(R.id.temparature);
    }

    public void questWeather(View view) throws MalformedURLException {
        final String urlComplet = "http://api.openweathermap.org/data/2.5/weather?" +"q=" + nomVille.getText().toString() + "&appid=" + maCle;

        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                String data="";
                try {
                    URL monUrl = new URL(urlComplet);
                    Log.d("PLOP",urlComplet);
                    HttpURLConnection cnx = (HttpURLConnection) monUrl.openConnection();
                    InputStream monObjet =  cnx.getInputStream();
                    InputStreamReader lecture = new InputStreamReader(monObjet);
                    BufferedReader buff=new BufferedReader(lecture);
                    String ligne;
                    while ((ligne=buff.readLine())!=null){
                        data += ligne;
                    }
                    buff.close();
                    lecture.close();
                    JSONObject object =  new JSONObject(data);
                    temp = object.getJSONObject("main").getDouble("temp");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                temperature.setText(temp.toString());
            }
        }.execute();



    }
}
