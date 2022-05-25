package com.example.server;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class GestoreFile {
    String nomeFile;

    public GestoreFile(String nomeFile) {
        this.nomeFile = nomeFile;
    }

    public GestoreFile() {

    }

    public int readFile(String nf, Context c, String u, String p) {

        int verified=0;


        try {
            BufferedReader fileDaLeggere = new BufferedReader(new InputStreamReader(c.openFileInput(nf)));
            //eccezioni.
            String testoDaRestituire = "";


            while ((testoDaRestituire = fileDaLeggere.readLine()) != null) {



                String[] separated = testoDaRestituire.split(",");

              if(separated[0].equals(u) && separated[1].equals(p)){

                  verified=1;
              }

            }
        }catch (FileNotFoundException e) {
            //ECCEZZIONE CONTROLLATA (FILE INESISTENTE)
            //LOG.E RRORE nel logcat in ROSSO colore
            Log.e("GESTORE", "file inesistente");

            //C'E' UN PROBLEMA CHE PUO' BLOCCARE IL PROGRAMMA
        } catch (IOException e) {
            Log.e("GESTORE", "ERRORE nella lettura del File");
        }


return verified;
    }


    public String scriviFile(String nomefile, String testo, Context c)
    {
        FileOutputStream fileO; //
        String esito;


        try {

            fileO=c.openFileOutput(nomefile,Context.MODE_PRIVATE);

            fileO.write(testo.getBytes(StandardCharsets.UTF_8));

            fileO.close();

            esito="file scitto correttamente";
        }
        catch (FileNotFoundException e) {
            esito="attenzione non sono riuscito a creare il file";
        }

        catch (IOException e) {
            esito="errore in fase di scrittura del file";
        }

        return esito;
    }



    }


