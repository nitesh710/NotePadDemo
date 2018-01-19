package com.nitesh.notepaddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NoteViewer extends AppCompatActivity {

    TextView textNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_viewer);

        textNote = findViewById(R.id.textNote);

        Intent intent = getIntent();
        String filename = intent.getStringExtra("Filename");

        textNote.setText(Open(filename));

    }

    public Boolean FileExists(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    public String Open(String filename){
        String content = "";

        if (FileExists(filename)){
            try{
                InputStream in = openFileInput(filename);
                if (in != null){
                    InputStreamReader tmp = new InputStreamReader(in);
                    BufferedReader reader = new BufferedReader(tmp);
                    String str;
                    StringBuilder buf = new StringBuilder();
                    while ((str = reader.readLine()) != null){
                        buf.append(str + "\n");
                    }

                    in.close();
                    content = buf.toString();

                    Toast.makeText(this, "Note Opened!", Toast.LENGTH_SHORT).show();

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return content;

    }

}
