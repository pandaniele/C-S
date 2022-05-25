package com.example.server;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
 Button btn;
 TextView textView;
 RequestQueue queue;
 StringRequest stringRequest;
String url="https://run.mocky.io/v3/491ca12e-a44e-46fe-a3a8-39a2519fc811";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button)findViewById(R.id.button);
        textView=(TextView)findViewById(R.id.txt);
        //CI METTO IL JSON
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    private void getData() {
        //CREAZIONE CODA DI RICHIESTE -OGNI ACTIVITY HA UNA CKDA GESTITA DALLO SCHEDULING

        //QUEUE=> ATTIVITA' IN BREACKGROUND DI QUESTA ATTIVITA'
        queue = Volley.newRequestQueue(this);
        //CREAZIONE RICHIESTA RISORSA
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //visualizzare risultatio della richiesta.
                textView.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //messaggio di errore
                textView.setText("Errore");
            }
        });
        //AVVIA LA RICHIESTA
        queue.add(stringRequest);        //AGGIUNTA RICHIESTA

    }
}