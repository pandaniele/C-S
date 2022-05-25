package com.example.server;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Login extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    Button inserisci;
    Button search;
    GestoreFile gf;
TextView ttt;

    RequestQueue queue;
    StringRequest stringRequest;
    String url="https://run.mocky.io/v3/491ca12e-a44e-46fe-a3a8-39a2519fc811";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        gf=new GestoreFile();

        ttt=(TextView)findViewById(R.id.textView);

        login=(Button)findViewById(R.id.button3);

        search=(Button)findViewById(R.id.button4);

        inserisci=(Button)findViewById(R.id.button2);

        username=(EditText)findViewById(R.id.editTextTextPersonName);

        password=(EditText)findViewById(R.id.editTextTextPersonName2);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), Api.class);

                startActivity(i);
            }
        });


        inserisci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  StringBuilder g= new StringBuilder();
                g.append(username.getText().toString());
                g.append(",");
                g.append(password.getText().toString());
                g.append('\n');
                if(validazione()==1) {
                    String hh = gf.scriviFile("archivio.txt", g.toString(), getApplicationContext());
                    Toast.makeText(getApplicationContext(), hh, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "ATTENZIONE: CAMPI VUOTI", Toast.LENGTH_SHORT).show();
                }

            }
        });




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(validazione()==1){

        if(gf.readFile("archivio.txt", getApplicationContext(), username.getText().toString(), password.getText().toString())==1){
            chiamata();
            search.setVisibility(View.VISIBLE);

        }
        else {
            Toast.makeText(getApplicationContext(), "ATTENZIONE: CREDENZIALI ERRATE", Toast.LENGTH_SHORT).show();
        }
            }
             else {
                Toast.makeText(getApplicationContext(), "REINSERISCI I CAMPI" +
                        " CORRETAMENTE", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }
    public int validazione(){
        if(username.getText().toString().length()>0 && password.getText().toString().length()>0){
      return 1;
        }
        else{
            return 0;
        }
    }

    private void chiamata() {
        //CREAZIONE CODA DI RICHIESTE -OGNI ACTIVITY HA UNA CKDA GESTITA DALLO SCHEDULING

        //QUEUE=> ATTIVITA' IN BREACKGROUND DI QUESTA ATTIVITA'
        queue = Volley.newRequestQueue(this);
        //CREAZIONE RICHIESTA RISORSA
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //visualizzare risultatio della richiesta.
                ttt.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //messaggio di errore
                ttt.setText("Errore");
            }
        });
        //AVVIA LA RICHIESTA
        queue.add(stringRequest);        //AGGIUNTA RICHIESTA

    }



}