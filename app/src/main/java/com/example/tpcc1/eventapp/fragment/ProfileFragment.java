package com.example.tpcc1.eventapp.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tpcc1.eventapp.R;
import com.example.tpcc1.eventapp.EditProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private ImageView imgProfile;
    private TextView username,name,email,gender;
    private Button btnProfile;
    SharedPreferences mSettings;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        btnProfile = (Button) view.findViewById(R.id.btn_change_profile);
        imgProfile = (ImageView) view.findViewById(R.id.img_profile);
        username = (TextView) view.findViewById(R.id.txt_username);
        name = (TextView) view.findViewById(R.id.txt_name);
        email = (TextView) view.findViewById(R.id.txt_email);
        gender = (TextView) view.findViewById(R.id.txt_gender);
        mSettings = getActivity().getSharedPreferences("AppEvent_Settings",
                Context.MODE_PRIVATE);
        //get setting
        username.setText(mSettings.getString("username","-"));
        name.setText(mSettings.getString("firstName","-") + " " + mSettings.getString("lastName","-"));
        email.setText(mSettings.getString("email","-"));
        gender.setText(mSettings.getString("gender","-"));
        //pertama kali
        if(mSettings.getString("imagePath",null) != null){
            imgProfile.setImageURI(Uri.parse(mSettings
                    .getString("imagePath","-")));
        }
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}