package com.example.mohab.utils;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class MyEncrypter {
    private final static int READ_WRITE_BLOCK_BUFFER = 1024;
    private final static String ALGO_IMAGE_ENCRYPTOR = "AES/CBC/PKCS5Padding";
    private final static String ALGO_SECRET_KEY = "AES";

    public static void encryptToFile(String keyStr, String specStr, InputStream in, OutputStream out) throws InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IOException {
        try{
            IvParameterSpec iv = new IvParameterSpec(specStr.getBytes("UTF-8"));
            SecretKeySpec keySpec = new SecretKeySpec(keyStr.getBytes("UTF-8"), ALGO_SECRET_KEY);


            Cipher c = Cipher.getInstance(ALGO_IMAGE_ENCRYPTOR);
            c.init(Cipher.ENCRYPT_MODE, keySpec, iv);
            out = new CipherOutputStream(out, c);
            int count = 0;

            byte[] buffer = new byte[READ_WRITE_BLOCK_BUFFER];

            while((count = in.read(buffer)) > 0)
                out.write(buffer, 0, count);
        }
        finally {
            out.close();
        }
    }
    public static byte[] encryptReturnFile(String keyStr, String specStr, InputStream in, OutputStream out) throws InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IOException {
        try{
            IvParameterSpec iv = new IvParameterSpec(specStr.getBytes("UTF-8"));
            SecretKeySpec keySpec = new SecretKeySpec(keyStr.getBytes("UTF-8"), ALGO_SECRET_KEY);


            Cipher c = Cipher.getInstance(ALGO_IMAGE_ENCRYPTOR);
            c.init(Cipher.ENCRYPT_MODE, keySpec, iv);
            out = new CipherOutputStream(out, c);
            int count = 0;
            byte[] buffer = new byte[READ_WRITE_BLOCK_BUFFER];


            while((count = in.read(buffer)) > 0)
                out.write(buffer, 0, count);

            return buffer;
        }
        finally {
            out.close();
        }
    }

    public static void decryptToFile(String keyStr, String specStr, InputStream in, OutputStream out) throws InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IOException {
        try{
            IvParameterSpec iv = new IvParameterSpec(specStr.getBytes("UTF-8"));
            SecretKeySpec keySpec = new SecretKeySpec(keyStr.getBytes("UTF-8"), ALGO_SECRET_KEY);


            Cipher c = Cipher.getInstance(ALGO_IMAGE_ENCRYPTOR);
            c.init(Cipher.DECRYPT_MODE, keySpec, iv);
            out = new CipherOutputStream(out, c);
            int count = 0;
            byte[] buffer = new byte[READ_WRITE_BLOCK_BUFFER];
            while((count = in.read(buffer)) > 0)
                out.write(buffer, 0, count);
        }
        finally {
            out.close();
        }
    }
}