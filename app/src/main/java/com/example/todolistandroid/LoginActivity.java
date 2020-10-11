package com.example.todolistandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public EditText emailInput;
    public EditText passwordInput;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        this.emailInput = (EditText) this.findViewById(R.id.email);
        this.passwordInput = (EditText) this.findViewById(R.id.password);
        this.button = (Button) this.findViewById(R.id.button);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputs = emailInput.getText().toString() + " " + passwordInput.getText().toString();
                // if(emailInput.getText().toString().equals("walid") && passwordInput.getText().toString().equals("walid")){
                    Intent intent = new Intent(getBaseContext(), List.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getBaseContext().startActivity(intent);
                /*}else {
                    Toast.makeText(getBaseContext(), "Wrong credentials :"+inputs , Toast.LENGTH_LONG ).show();
                }*/
            }
        });
    }

}
