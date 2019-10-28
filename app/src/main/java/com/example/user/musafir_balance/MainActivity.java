package com.example.user.musafir_balance;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private EditText mobileno,amountinput;
    private Button rechrge;
    private int balanceFromDB;
    private String addressFromDB;
    private String nameFromDB;
    private String phonenoFromDB;
    private String userID;
    private int amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mobileno=(EditText)findViewById(R.id.Mobileno);
        amountinput=(EditText)findViewById(R.id.amount);
        rechrge=(Button) findViewById(R.id.rechrge);

        rechrge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userID="+88"+mobileno.getText().toString();
                amount=Integer.parseInt(amountinput.getText().toString());

                FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds:dataSnapshot.getChildren()){
                            balanceFromDB=ds.child(userID).getValue(user.class).getBalance();
                            nameFromDB=ds.child(userID).getValue(user.class).getName();
                            addressFromDB=ds.child(userID).getValue(user.class).getAddress();
                            phonenoFromDB=ds.child(userID).getValue(user.class).getPhoneno();


                        }

                        user u=new user(nameFromDB,phonenoFromDB,addressFromDB);
                        u.setBalance(balanceFromDB+amount);

                        FirebaseDatabase.getInstance().getReference("Users").child(userID).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                startActivity(new Intent(MainActivity.this, RechrgeSuccessful.class));
                            }
                        });


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });



    }
}
