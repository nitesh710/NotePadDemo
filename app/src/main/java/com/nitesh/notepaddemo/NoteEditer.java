package com.nitesh.notepaddemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class NoteEditer extends AppCompatActivity {

    EditText editNote, editer;
    public String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editNote = findViewById(R.id.editNote);
        editer = findViewById(R.id.editer);

        final Intent intent = getIntent();
        int counter = intent.getIntExtra("Note",0);

        filename = "note" + counter + ".txt";

        editNote.setText(filename);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save(filename);
                Intent intent1 = new Intent(NoteEditer.this,MainActivity.class);
                intent1.putExtra("Filename", filename);
                setResult(200, intent1);
                finish();
            }
        });
    }

    public void Save(String filename) {
        try{
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(filename,0));
            out.write(editer.getText().toString());
            out.close();
            Toast.makeText(this, "Note Saved!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
