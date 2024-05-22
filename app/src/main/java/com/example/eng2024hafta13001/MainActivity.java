package com.example.eng2024hafta13001;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FireBaseHelper fb= new FireBaseHelper();
    DatabaseReference dbref= FirebaseDatabase.getInstance().getReference();
    EditText etEmail;
    EditText etName;
    EditText etUserID;
    ListView lv;

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
    etEmail=(EditText) findViewById(R.id.editTextText3);
    etUserID=(EditText)findViewById(R.id.editTextText);
    etName=(EditText)findViewById(R.id.editTextText2);
    lv= (ListView) findViewById(R.id.listView);
        Map<String, String> kayit= new HashMap<>();
        kayit.put("Şampiyon","Kupa Fatihi Trabzonspor");

        dbref.push().setValue(kayit, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error==null)
                    Log.d("Status","OK SUCCESS");
                else
                    Log.d("Status","Fail FAILED");

            }
        });

        lvGuncelle();



    }



    public void lvGuncelle(){
        DataStatus ds= new DataStatus() {
            @Override
            public void DataIsLoaded(User user) {

            }

            @Override
            public void DataIsLoaded(List<User> users) {
                ArrayAdapter<User> adap= new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,users);
                lv.setAdapter(adap);

            }
        };
        fb.getAllUser(ds);


    }


    public void Ekle(View v){
        User user = new User(null,etName.getText().toString(),etEmail.getText().toString());
        fb.addUser(user);
    }

    public void Sil(View v){

    }

    public void Guncelle(View v){


    }

    public void Getir(View v){

    }
}