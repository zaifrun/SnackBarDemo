package org.pondar.snackbardemo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
        Button button = findViewById(R.id.saveButton);
        //setting up the click listener.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = findViewById(R.id.lastEntered);
                EditText editText = findViewById(R.id.editText);
                backup = currentName; //creating a backup

                //the following two lines hide the keyboard after clicking the button
                //which is what you want!
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(parent.getWindowToken(), 0);

                //save the text entered in the current name field.
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
                                TextView textView = findViewById(R.id.lastEntered);
                                currentName = backup; //get backup
                                textView.setText(currentName);
                                Snackbar snackbar = Snackbar.make(parent, "Old name restored!", Snackbar.LENGTH_SHORT);

                                //Show the user we have restored the name - but here
                                //on this snackbar there is NO UNDO - so no SetAction method is called
                                //if you wanted, you could include a REDO on the second action button
                                //for instance.
                                snackbar.show();
                            }
                        });

                snackbar.show();
            }
        });
    }
}
