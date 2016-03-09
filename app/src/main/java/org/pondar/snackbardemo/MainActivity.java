package org.pondar.snackbardemo;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String currentName = ""; //nothing entered

    //This is the backup where we save the name in case the user hits the undo button
    String backup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final View parent = findViewById(R.id.layout);
        Button button = (Button) findViewById(R.id.saveButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.lastEntered);
                EditText editText = (EditText) findViewById(R.id.editText);
                backup = new String(currentName); //creating a backup

                currentName =  editText.getText().toString();
                textView.setText(currentName);
                //Now setup our snackbar and show it

                Snackbar snackbar = Snackbar
                        .make(parent, "Name saved", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //This code will ONLY be executed in case that
                                //the user has hit the UNDO button
                                TextView textView = (TextView) findViewById(R.id.lastEntered);
                                currentName = new String(backup); //get backup
                                textView.setText(currentName);
                                Snackbar snackbar = Snackbar.make(parent, "Old name restored!", Snackbar.LENGTH_SHORT);

                                //Show the user we have restored the name - but here
                                //on this snackbar there is NO UNDO - so not SetAction method is called
                                snackbar.show();
                            }
                        });

                snackbar.show();
            }
        });
    }
}
