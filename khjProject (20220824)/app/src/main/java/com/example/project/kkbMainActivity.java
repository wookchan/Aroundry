package com.example.project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.project.DTO.StoreDTO;
import com.google.android.material.tabs.TabLayout;

public class kkbMainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabs;

    com.example.project.kkbFragment_clicklocation fragment_clicklocation;
    com.example.project.activity_fragment_clickwash activity_fragment_clickwash;

    StoreDTO storeDTO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kkbactivity_main);

        fragment_clicklocation = new com.example.project.kkbFragment_clicklocation();
        activity_fragment_clickwash= new com.example.project.activity_fragment_clickwash();

        getSupportFragmentManager().beginTransaction().replace(R.id.contain, fragment_clicklocation).commit();


        // 탭을 동적으로 생성한다
        tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("정보"));
        tabs.addTab(tabs.newTab().setText("세탁기"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                if(position == 0){
                    getSupportFragmentManager().beginTransaction().replace(R.id.contain, fragment_clicklocation).commit();
                }else {
                  getSupportFragmentManager().beginTransaction().replace(R.id.contain, activity_fragment_clickwash).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public void getStoreDto(StoreDTO storeDTO){
        this.storeDTO = storeDTO;
    }
}

