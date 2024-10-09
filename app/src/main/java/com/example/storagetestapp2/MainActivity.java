package com.example.storagetestapp2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText textmsg;
    static final int READ_BLOCK_SIZE = 100;

    @SuppressLint("MissingInflated")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textmsg = (EditText) findViewById(R.id.editText1);
    }

    public void WriteBtn(View v){
        try{
            FileOutputStream fileOut = openFileOutput("C:\\Users\\Dmytri\\AndroidStudioProjects\\StorageTestApp1\\app\\src\\main\\java\\com\\example\\storagetestapp1\\mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOut);

            outputWriter.write(textmsg.getText().toString());
            outputWriter.close();

            Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void ReadBtn(View v){
        try{
            FileInputStream fileIn = openFileInput("C:\\Users\\Dmytri\\AndroidStudioProjects\\StorageTestApp1\\app\\src\\main\\java\\com\\example\\storagetestapp1\\mytextfile.txt");
            InputStreamReader inputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;

            while((charRead = inputRead.read(inputBuffer))>0){
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                s += readString;
            }

            inputRead.close();

            textmsg.setText(s);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}