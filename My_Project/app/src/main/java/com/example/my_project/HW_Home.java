package com.example.my_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.my_project.Adapter.HW_SetakAdapter;
import com.example.my_project.DATA.HW_SetakDTO;
import com.example.my_project.Fragment.HW_CashFragment;
import com.example.my_project.Fragment.HW_ListFragment;
import com.example.my_project.Fragment.HW_PresidentFragment;
import com.example.my_project.Fragment.HW_StoreFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HW_Home extends AppCompatActivity {
    HW_Home Home;
    HW_CashFragment cash;
    HW_PresidentFragment president;
    HW_StoreFragment store;
    HW_SetakAdapter adapter;
    ArrayList<HW_SetakDTO> dtos;
    RecyclerView homerecycle;
    HW_ListFragment List;
    MenuItem item = null;
    //addpater
    //recyclerViewer
    //세탁기 관련 dto * 추 후 테이블로 만들어야 할듯함
    // 디바이스 사이즈
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hw_activity_home);
        Intent intent = getIntent();
        String id = intent.getStringExtra("ownerid");
        String pw = intent.getStringExtra("ownerpw");
        String name = intent.getStringExtra("ownername");
        String tel = intent.getStringExtra("ownertel");
        String profile = intent.getStringExtra("ownerprofile");
        //intent.putExtra("ownerid",id);
        
       /* // 리사이클러뷰 초기화
        dtos = new ArrayList<>();
        homerecycle = findViewById(R.id.homerecycle);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,homerecycle.VERTICAL,false);
        homerecycle.setLayoutManager(layoutManager);

        adapter = new setakAdapter(Home.this,dtos);


        //임시로 만들어둔 dto
        adapter.addDTO(new setakDTO("NoImage","광주 광역시 북구~",3));
        adapter.addDTO(new setakDTO("NoImage","광구 광역시 남구~",4));
        adapter.addDTO(new setakDTO("R.drawable.dmc5blackhall.gif","광구 광역시 남구~",1));
        adapter.addDTO(new setakDTO("R.drawable.dmc5blackhall","광구 광역시 남구~",1));

        homerecycle.setAdapter(adapter);
        adapter.notifyDataSetChanged();*/

        // id 값을 받아서 넘겨야한다 22년 8월 8일
        
        //바텀 네비게이션 초기화
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putString("pw",pw);
        bundle.putString("name",name);
        bundle.putString("tel",tel);
        bundle.putString("profile",profile);
        cash = new HW_CashFragment();
        cash.setArguments(bundle);
        Home = new HW_Home();
        president = new HW_PresidentFragment();
        president.setArguments(bundle);
        store = new HW_StoreFragment();
        store.setArguments(bundle);
        List = new HW_ListFragment();
        List.setArguments(bundle);
        if(item == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contain, List).commit();
        }
        BottomNavigationView bottomnavi = findViewById(R.id.homenavi);
        // 데이터 베이스에서 소유한 가게
        // 리스트를 읽어내서 리사이클러뷰에 추가해서 나열시켜야함
        bottomnavi.setItemIconTintList(null);
        bottomnavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.first_tab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.contain, List).commit();

                        return true;
                    case R.id.second_tab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.contain, cash).commit();
                        return true;
                    case R.id.third_tab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.contain, president).commit();
                        return true;
                }
            return false;

            }
        });
    }

    //백버튼 막기
    @Override
    public void onBackPressed() {
        return;
    }
}
