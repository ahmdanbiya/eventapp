package com.example.tpcc1.eventapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.tpcc1.eventapp.db.DatabaseHelper;
import com.example.tpcc1.eventapp.model.EventResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DetailEventActivity extends AppCompatActivity {

    private TextView txtEventName, textEventDesc, txtEventLocation, txtEventTime;
    private ImageView imgEvent;
    private Button btnConfirm;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        setTitle("Detail Event");
        Intent intent = getIntent();
        final EventResult events = (EventResult) intent.getSerializableExtra("event");
        final DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);
        txtEventName = (TextView) findViewById(R.id.txt_event_name);
        textEventDesc = (TextView) findViewById(R.id.txt_event_description);
        txtEventLocation = (TextView) findViewById(R.id.txt_event_place);
        txtEventTime = (TextView) findViewById(R.id.txt_time);
        imgEvent = (ImageView) findViewById(R.id.img_event);
        btnConfirm = (Button) findViewById(R.id.btn_confirm);

        txtEventName.setText(events.getEventname() + " (" + events.getEventquota() + ")");
        textEventDesc.setText(events.getEventdesc());
        txtEventLocation.setText(events.getEventlocation());
        txtEventTime.setText(events.getStarttime() + " s/d " + events.getEndtime());
        Glide
                .with(this)
                .load(events.getEventpicture())
                .into(imgEvent);
        //simpan gambar di internal storage
        Glide.with(this)
                .asBitmap()
                .load(events.getEventpicture())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource,
                                                @Nullable com.bumptech.glide.request.transition.Transition
                                                        <? super Bitmap> transition) {
                        saveImage(resource, events.getId());
                    }

                });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailEventActivity.this);
                builder.setTitle("Konfirmasi Kehadiran");
                builder.setPositiveButton("Konfirmasi",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                events.setEventpicture("JPEG_" + events.getId() + ".jpg");
                                databaseHelper.addEvent(events);
                                Toast.makeText(DetailEventActivity.this,
                                        "event telah dikonfirmasi", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //do nothing
                            }
                        });
                builder.show();
            }
        });

        mapFragment = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map));
        if (mapFragment != null) {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap map) {
                    map.addMarker(new MarkerOptions()
                            .position(new LatLng(Double.parseDouble(events.getEventlatitude()),
                                    Double.parseDouble(events.getEventlongitude())))
                            .title(events.getEventlocation())
                            .snippet(events.getEventname()));
                    map.animateCamera(CameraUpdateFactory
                            .newLatLngZoom(new LatLng(Double.parseDouble(events.getEventlatitude()),
                                    Double.parseDouble(events.getEventlongitude())), 15));
                    map.getUiSettings().setZoomControlsEnabled(true);
                    map.setMinZoomPreference(11);
                }
            });
        } else {
            Toast.makeText(this, "Error - Map Fragment was null!!", Toast.LENGTH_SHORT).show();
        }
    }

    private String saveImage(Bitmap image, String fileName) {
        String savedImagePath = null;
        String imageFileName = "JPEG_" + fileName + ".jpg";
        File storageDir = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + "");
        boolean success = true;
        if (!storageDir.exists()) {
            success = storageDir.mkdirs();
        }
        if (success) {
            File imageFile = new File(storageDir, imageFileName);
            savedImagePath = imageFile.getAbsolutePath();
            try {
                OutputStream fOut = new FileOutputStream(imageFile);
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                fOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Add the image to the system gallery
            galleryAddPic(savedImagePath);
            //Toast.makeText(DetailEventActivity.this, "IMAGE SAVED", Toast.LENGTH_LONG).show();
        }
        return savedImagePath;
    }

    private void galleryAddPic(String imagePath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(imagePath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
    }
}