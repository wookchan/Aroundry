package com.example.project;


import static com.example.project.Common.CommonMethod.Qrin;
import static com.example.project.Common.CommonMethod.loginDTO;
import static com.example.project.Common.CommonMethod.recentDTO;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

public class MainMenuMypageFragment extends Fragment { //implements NavigationView.OnNavigationItemSelectedListener{


    private View view;
    ImageView page_name, page_money1, page_qrcode, page_new, page_image;
    TextView page_name2, page_money2, user_id;


    Toolbar toolbar;
    Trash_Fragment1 PSFragment1;
    Trash_Fragment2 PSFragment2;
    Trash_Fragment3 PSFragment3;

    NavigationView nav_view;
    DrawerLayout draw_layout;

    MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_menu_mypage, container, false);

        activity = (MainActivity) getActivity();

        nav_view = view.findViewById(R.id.nav_view);
        // implement Listener 할때는 반드시 아래와 같이 정의한다.

       //   nav_view.setNavigationItemSelectedListener(this);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // 클릭한 아이템의 id를 얻는다
                int id = item.getItemId();

                if(id == R.id.nav_home){
                    onFragmentSelected(0);
                }else if(id == R.id.nav_gallery){
                    onFragmentSelected(1);
                }else if(id == R.id.nav_slideshow){
                    onFragmentSelected(2);
                }

                // 메뉴 선택 후 드로어가 사라지게 한다
                draw_layout.closeDrawer(GravityCompat.START);

                return true;
            }
        });


        // toolbar 적용 : theme에 NoActionBar로 변경
        toolbar = view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.setSupportActionBar(toolbar);

        draw_layout = view.findViewById(R.id.draw_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), draw_layout, toolbar,
                R.string.drawer_open, R.string.drawer_close );
        draw_layout.addDrawerListener(toggle);
        toggle.syncState();

        /*// 프래그먼트를 생성해서 프래임레이아웃에 초기화를 시킨다
        PSFragment1 = new Trash_Fragment1();
        PSFragment2 = new Trash_Fragment2();
        PSFragment3 = new Trash_Fragment3();

       getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.contain, PSFragment1).commit();*/

        page_name = (ImageView) view.findViewById(R.id.page_name);
        page_money1 = (ImageView) view.findViewById(R.id.page_money1);
        page_qrcode = (ImageView) view.findViewById(R.id.page_qrcode);
        page_new = (ImageView) view.findViewById(R.id.page_new);
        page_image = (ImageView) view.findViewById(R.id.page_image);
        page_name2 = (TextView) view.findViewById(R.id.page_name2);
        page_money2 = (TextView) view.findViewById(R.id.page_money2);

        Glide
            .with(this)
            .load(loginDTO.getProfileimage())
            .circleCrop()
            .placeholder(R.drawable.person)
            .into(page_name);

        page_name2.setText(loginDTO.getName());
        page_money2.setText(loginDTO.getPoint());

        Glide
                .with(this)
                .load(recentDTO.getRecent())
                .circleCrop()
                .placeholder(R.drawable.cleaning)
                .into(page_image);

        page_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), detail_pageActivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        page_money1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Money_Detail.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        page_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PS_QRScannerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

                changeMyPage();
            }
        });

        page_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), kkbMainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        return view;
    }


    // 메뉴에 있는 아이템이 선택되었을때
    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // 클릭한 아이템의 id를 얻는다
        int id = item.getItemId();

        if(id == R.id.nav_home){
            onFragmentSelected(0);
        }else if(id == R.id.nav_gallery){
            onFragmentSelected(1);
        }else if(id == R.id.nav_slideshow){
            onFragmentSelected(2);
        }

        // 메뉴 선택 후 드로어가 사라지게 한다
        draw_layout.closeDrawer(GravityCompat.START);

        return true;
    }*/

    private void onFragmentSelected(int position) {
        Fragment curFragment = null;

        if(position == 0){
            curFragment = PSFragment1;
            toolbar.setTitle("첫번째 화면");
        }else if(position == 1){
            curFragment = PSFragment2;
            toolbar.setTitle("두번째 화면");
        }else if(position == 2){
            curFragment = PSFragment3;
            toolbar.setTitle("세번째 화면");
        }
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.contain, curFragment).commit();

    }

    // qrcode 이후 세번째 탭으로 간다 매소드
    public void changeMyPage(){
        Qrin = 1;
        activity.bottomNavigationView.setSelectedItemId(R.id.menu_wash);
    }

/*    // 뒤로가기를 눌렀을때 만약 드로어 창이 열려있으면 드로어 창을 닫고
    // 아니면 그냥 뒤로가기 원래 작업을 한다(여기서는 앱종료)
    @Override
    public void onBackPressed() {
        if(draw_layout.isDrawerOpen(GravityCompat.START)){
            draw_layout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }*/
}
