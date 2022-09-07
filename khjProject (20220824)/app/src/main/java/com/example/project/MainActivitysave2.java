/*
package com.example.project;

import static com.example.project.Common.CommonMethod.loginDTO;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.project.Adapter.AndroidAdapter;
import com.example.project.Adapter.PS_SearchAdapter;
import com.example.project.DTO.MemberDTO;
import com.example.project.DTO.PS_SearchDTO;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private MainMenuHomeFragment fragmentHome = new MainMenuHomeFragment();
    private MainMenuMapFragment fragmentMap = new MainMenuMapFragment();
    private Fragmentlogin fragmentlogin = new Fragmentlogin();
    private MainMenuMypageFragment fragmentMypage = new MainMenuMypageFragment();
    private washFragment washFragment = new washFragment();

    AndroidAdapter adapter;

    ArrayList<MemberDTO> dtos;

    public int menuNum = -1;

    private static final String TAG = "main:PS_MainActivity";

    EditText editText;

    Toolbar toolbar;
    TabLayout tabs;
    PS_Fragment1 PSFragment1;
    Fragment2 fragment2;

    Fragment selFragment = null;

    ArrayList<PS_SearchDTO> filteredList;

    public ArrayList<PS_SearchDTO> mPSSearchDTOS;
    private PS_SearchAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ps_sch_activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.maincontain, fragmentHome).commitAllowingStateLoss();
     */
/*   if (savedInstanceState == null) {

            MainMenuMapFragment mainFragment = new MainMenuMapFragment();
            getSupportFragmentManager().beginTransaction().commit();
        }
*//*

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());


        // 반드시 만들어서 adapter에 넘겨야 한다
        dtos = new ArrayList<>();


        // 어댑터 객체 생성
        adapter = new AndroidAdapter(MainActivity.this, dtos);




        editText = findViewById(R.id.edittext);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            */
/* 검색 *//*

            @Override
            public void afterTextChanged(Editable s) {
                mPSSearchDTOS = PSFragment1.getmExampleList();
                filter(s.toString());
            }


        });

        */
/*
         * Tab 부분
         *
         * *//*


        // 액션바가 보이지 않게 하기 위해
        // 먼저 theme에 가서 NoActionBar로 수정한다

        // 내가 만든 툴바를 액션바로 지정한다
      */
/*  toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 기존 타이틀 글자가 안보이게 한다
        ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);*//*


        // 프래그먼트 생성
        PSFragment1 = new PS_Fragment1();
        fragment2 = new Fragment2();
        */
/*        fragment3 = new Fragment3();*//*



        // 먼저 화면에 프래그먼트1이 보이게 초기화
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.maincontain, PSFragment1).commit();

        // 탭을 동적으로 생성한다
        tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("가까운거리"));
        tabs.addTab(tabs.newTab().setText("즐겨찾기"));
        */
/*        tabs.addTab(tabs.newTab().setText("연락처"));*//*


        // 탭 레이아웃에 리스너를 달아준다
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            // 탭이 선택되었을때
            @Override  //                 선택된 탭
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();   // 0, 1, 2
                Log.d(TAG, "onTabSelected: " + position);
                if(position == 0){
                    selFragment = PSFragment1;
                }else if(position == 1){
                    selFragment = fragment2;
                }*/
/*else if(position == 2){
                    selFragment = fragment3;
                }*//*

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.maincontain, selFragment).commit();
            }
            // 탭이 선택되지 않았을때
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            // 탭이 재선택 되었을떄
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

    */
/* filter 함수(검색) - item 1,2,3 안에서 검색되게 설정
     * 1, 3은 범위로 검색되게 설정하는 방법도 있다.
     * 검색자체에 filter 를 더하면 편의성이 높아질 수도
     * *//*

    private void filter(String text) {
        filteredList = new ArrayList<>();
        mAdapter = new PS_SearchAdapter(this,filteredList);

        for (PS_SearchDTO item : mPSSearchDTOS) {
            if (item.getAddress().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            } else if (item.getLocation().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            } else if (item.getAddress().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        PSFragment1.editSetAdapter(filteredList);
        //mAdapter.filterList(filteredList);
    }

    public void arraylist_frag1(ArrayList<PS_SearchDTO> arr){
        filteredList = arr;
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
                    transaction.replace(R.id.maincontain, fragmentHome).commitAllowingStateLoss();
                    break;
                case R.id.menu_map:
                    transaction.replace(R.id.maincontain, fragmentMap).commitAllowingStateLoss();
                    break;

                case R.id.menu_wash:
                    if(loginDTO == null){
                        transaction.replace(R.id.maincontain, fragmentlogin).commitAllowingStateLoss();
                        menuNum = 3;
                    }else {
                        transaction.replace(R.id.maincontain, washFragment).commitAllowingStateLoss();
                    }

                    break;
                case R.id.menu_my_page:
                    if(loginDTO == null){
                        transaction.replace(R.id.maincontain, fragmentlogin).commitAllowingStateLoss();
                        menuNum = 4;
                    }else {
                        transaction.replace(R.id.maincontain, fragmentMypage).commitAllowingStateLoss();
                    }
                    break;

            }

            return true;
        }
    }

}
*/
