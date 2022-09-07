package com.example.project;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class washFragment extends Fragment {
    String TAG ="main:washFragment";

    //Toolbar toolbar;
    TabLayout tabs;
    Fragment selFragment = null;

    MainActivity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_main_menu_wash, container, false);

        activity = (MainActivity) getActivity();

        // 프래그먼트 생성
        MainWashFragAll mainWashFragAll = new MainWashFragAll();
        MainWashFragMy mainWashFragMy = new MainWashFragMy();

        // 먼저 화면에 프래그먼트1이 보이게 초기화
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainwashcontain, mainWashFragAll).commit();

        // 탭을 동적으로 생성한다
        tabs = rootView.findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("세탁소 찾기"));
        tabs.addTab(tabs.newTab().setText("내가 사용중인 세탁기"));

        // 탭 레이아웃에 리스너를 달아준다
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            // 탭이 선택되었을때
            @Override  //                 선택된 탭
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();   // 0, 1
                Log.d(TAG, "onTabSelected: " + position);

                if (position == 0) {
                    selFragment = mainWashFragAll;
                } else if (position == 1) {
                    selFragment = mainWashFragMy;
                }

                getFragmentManager().beginTransaction()
                        .replace(R.id.mainwashcontain, selFragment).commit();
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

        return rootView;
    }


}
