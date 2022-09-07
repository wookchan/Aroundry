/*
package com.example.project;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

*/
/*
package com.example.project;

import static com.example.project.Common.CommonMethod.loginDTO;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class LoginResultActivity extends AppCompatActivity {

    TextView tvname, tvemail, tvphonenumber;
    ImageView ivimageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginresult);

        tvname = findViewById(R.id.tvemail);
        tvemail = findViewById(R.id.tvname);
        tvphonenumber = findViewById(R.id.tvphonenumber);
        ivimageView = findViewById(R.id.ivimageview);

        tvname.setText(loginDTO.getName());
        tvemail.setText(loginDTO.getEmail());
        tvphonenumber.setText(loginDTO.getPhonenumber());

        if(loginDTO.getProfileimage().equals("NoImage")){
            ivimageView.setImageResource(R.drawable.guest);
        }else{
            Glide.with(LoginResultActivity.this)
                    .load(loginDTO.getProfileimage())
                    .circleCrop()
                    .into(ivimageView);
        }
*//*

public class LoginResultActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();


    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.menu_wash:
                    transaction.replace(R.layout.fragment_main_menu_wash).commitAllowingStateLoss();

                    break;


            }
            return true;
        }
    }
}*/
