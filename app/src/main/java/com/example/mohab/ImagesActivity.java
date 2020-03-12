package com.example.mohab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mohab.Adpters.Image_Adapter;
import com.example.mohab.utils.CImage;
import com.example.mohab.utils.CStorage;
import com.example.mohab.utils.MyEncrypter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends AppCompatActivity {

    private  RecyclerView recyclerView;
    private Image_Adapter adapter;
    private CStorage cStorage;
    private List<CImage> images;
    private MyEncrypter encrypter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cStorage = new CStorage();
        images = new ArrayList<>();
        encrypter = new MyEncrypter();

        cStorage.getDatabaseReference().child("Teeth").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        images.add(snapshot.getValue(CImage.class));

                    try {
                        cStorage.getFile(encrypter, snapshot);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                adapter = new Image_Adapter(ImagesActivity.this, images);

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ImagesActivity.this, databaseError.getMessage()
                        ,Toast.LENGTH_SHORT).show();
            }
        });


    }
}
