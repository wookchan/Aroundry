
package com.example.project;


import static com.example.project.Common.CommonMethod.loginDTO;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.project.Adapter.AndroidAdapter;
import com.example.project.DTO.MemberDTO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivitysave extends AppCompatActivity{

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private MainMenuHomeFragment fragmentHome = new MainMenuHomeFragment();
    private MainMenuMapFragment fragmentMap = new MainMenuMapFragment();
    private Fragmentlogin fragmentlogin = new Fragmentlogin();
    private MainMenuMypageFragment fragmentMypage = new MainMenuMypageFragment();
    private washFragment washFragment = new washFragment();

    AndroidAdapter adapter;

    ArrayList<MemberDTO> dtos;

    public int menuNum = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.menu_frame_layout, fragmentHome).commitAllowingStateLoss();



        if (savedInstanceState == null) {

            MainMenuMapFragment mainFragment = new MainMenuMapFragment();
            getSupportFragmentManager().beginTransaction()

                    .commit();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());


        // 반드시 만들어서 adapter에 넘겨야 한다
        dtos = new ArrayList<>();


        // 어댑터 객체 생성
        adapter = new AndroidAdapter(MainActivitysave.this, dtos);




    }

    public void changeFragment(){
        if(menuNum == 3){
            fragmentManager.beginTransaction().replace(R.id.menu_frame_layout, fragmentMypage).commit();
        }else if(menuNum == 4){
            fragmentManager.beginTransaction().replace(R.id.menu_frame_layout, fragmentMypage).commit();
        }

    }



    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();



            switch (menuItem.getItemId()) {
                case R.id.menu_home:
                    transaction.replace(R.id.menu_frame_layout, fragmentHome).commitAllowingStateLoss();
                    break;
                case R.id.menu_map:
                    transaction.replace(R.id.menu_frame_layout, fragmentMap).commitAllowingStateLoss();
                    break;

                case R.id.menu_wash:
                    if(loginDTO == null){
                        transaction.replace(R.id.menu_frame_layout, fragmentlogin).commitAllowingStateLoss();
                        menuNum = 3;
                    }else {
                        transaction.replace(R.id.menu_frame_layout, washFragment).commitAllowingStateLoss();
                    }

                    break;
                case R.id.menu_my_page:
                    if(loginDTO == null){
                        transaction.replace(R.id.menu_frame_layout, fragmentlogin).commitAllowingStateLoss();
                        menuNum = 4;
                    }else {
                        transaction.replace(R.id.menu_frame_layout, fragmentMypage).commitAllowingStateLoss();
                    }
                    break;

            }

            return true;
        }
    }



}



