package com.example.user.musafir_balance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RechrgeSuccessful extends AppCompatActivity {
    private Button cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechrge_successful);
        cont=(Button)findViewById(R.id.cntinue);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RechrgeSuccessful.this, MainActivity.class));
            }
        });

    }
}
