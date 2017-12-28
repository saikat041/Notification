package com.example.saika.notification;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by saika on 04-12-2017.
 */

public class NotificationListner extends NotificationListenerService{

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        db = FirebaseFirestore.getInstance();
        Map<String, Object> noti = new HashMap<>();
        Notification notification=sbn.getNotification();
        Log.i("saikat","New Notification from "+sbn.getPackageName());
       noti.put("from",sbn.getPackageName());
       noti.put("title",notification.extras.get(notification.EXTRA_TITLE).toString());
       noti.put("body",notification.extras.get(notification.EXTRA_TEXT).toString());
       FirebaseUser firabaseUser=FirebaseAuth.getInstance().getCurrentUser();
       if(firabaseUser!=null) {
           db.collection(firabaseUser.getEmail()).add(noti).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
               @Override
               public void onSuccess(DocumentReference documentReference) {
                   Log.i("saikat", "sucess");
               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   Log.i("saikat", "failure");
               }
           });
       }
       else{
           Log.i("saikat","Not Logged in");
       }

    }


}
