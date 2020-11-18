package com.example.applibrosfirebase20202;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.applibrosfirebase20202.adapters.LibroAdapter;
import com.example.applibrosfirebase20202.models.LibroModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    protected FirebaseFirestore db;
    private String TAG = "LFLIBRO";
    final private String collection = "books";
    private ListView lv_main_notas;
    private Button btn_main_nuevo;
    private ArrayList<LibroModel> list;
    private LibroAdapter adapter;
    private LibroModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();
        lv_main_notas = findViewById(R.id.lv_main_notas);
        btn_main_nuevo = findViewById(R.id.btn_main_nuevo);

        btn_main_nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });

        list = new ArrayList<>();

        db.collection(collection)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                model = document.toObject(LibroModel.class);
                                list.add(model);
                            }
                            adapter = new LibroAdapter(getApplicationContext(), list);
                            lv_main_notas.setAdapter(adapter);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });



        lv_main_notas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), String.valueOf(list.get(i).getTitulo()), Toast.LENGTH_LONG).show();
                /*model = list.get(i);
                Intent detalle = new Intent(MainActivity.this, DetalleActivity.class);
                detalle.putExtra("model", model);
                startActivity(detalle);*/
            }
        });
    }

    private void goToRegister(){
        Intent nuevo = new Intent(this, RegistroActivity.class);
        startActivity(nuevo);
    }
}