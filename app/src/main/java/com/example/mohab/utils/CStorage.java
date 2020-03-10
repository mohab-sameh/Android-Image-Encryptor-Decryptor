package com.example.mohab.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.net.URI;

import androidx.annotation.NonNull;

public class CStorage {

    private FirebaseStorage storage;
    private StorageReference reference;
    private StorageTask storageTask;
    public CStorage() {
        storage = FirebaseStorage.getInstance();
        reference = storage.getReference().child("Teeth");
    }

    public void uploadFile(final Context context, byte[] data) {
        UploadTask uploadTask = reference.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(context,"File did not Upload",Toast.LENGTH_SHORT);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
               // Uri downloadUrl = taskSnapshot.getDownloadUrl();
               // sendMsg("" + downloadUrl, 2);
                //Log.d("downloadUrl-->", "" + downloadUrl);
                Toast.makeText(context,"File Uploaded",Toast.LENGTH_SHORT);
            }
        });

    }

    public void getFile() {

    }
}
