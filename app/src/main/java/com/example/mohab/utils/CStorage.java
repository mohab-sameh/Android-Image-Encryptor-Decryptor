package com.example.mohab.utils;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

import androidx.annotation.NonNull;

public class CStorage {

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseDatabase database;
    private StorageTask storageTask;
    private DatabaseReference databaseReference;

    public CStorage() {
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        storageReference = storage.getReference();
        databaseReference = database.getReference();
    }

    public void uploadFile(final Context context, byte[] data) {
        UploadTask uploadTask = storageReference.child("Teeth").child("cpts_enc").putBytes(data);

        storageTask = uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(context,"File did not Upload",Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String uploadID = databaseReference.child("Teeth").push().getKey();

                CImage cImage = new CImage(storageReference.child("Teeth")
                        .child(uploadID).getDownloadUrl().toString()
                        ,"cpts_enc");
                databaseReference.child("Teeth").child(uploadID).setValue(cImage);

                Toast.makeText(context,"File Uploaded",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getFile(MyEncrypter encrypter, DataSnapshot snapshot) throws FileNotFoundException {
        CImage cImage = snapshot.getValue(CImage.class);

        File from  = new File(cImage.getImageUrl(), cImage.getName());
        String my_key="O17GAyS6whmFjpor";
        String my_spec_key = "G4Fg6gP7e0C9wpEq";

        try {
            encrypter.decryptToFile(my_key, my_spec_key, new FileInputStream(from),
                    new FileOutputStream(from));

        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StorageTask getStorageTask() {
        return storageTask;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }
}
