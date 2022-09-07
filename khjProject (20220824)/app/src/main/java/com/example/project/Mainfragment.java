

package com.example.project;

import static com.example.project.Common.CommonMethod.loginDTO;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.project.Adapter.AndroidAdapter;
import com.example.project.Adapter.PS_SearchAdapter;
import com.example.project.DTO.MemberDTO;
import com.example.project.DTO.StoreDTO;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Mainfragment extends Fragment {

    AndroidAdapter adapter;

    ArrayList<MemberDTO> dtos;

    public int menuNum = -1;

    private static final String TAG = "main:PS_MainActivity";

    EditText editText;

    //Toolbar toolbar;
    TabLayout tabs;
    PS_Fragment1 PSFragment1;
    Fragment2 fragment2;
    Fragment selFragment = null;

    ArrayList<StoreDTO> filteredList;

    public ArrayList<StoreDTO> mPSSearchDTOS;
    public ArrayList<StoreDTO> mPSSearchDTOS2;
    private PS_SearchAdapter mAdapter;
    MainActivity activity;

    int position = 0;




    private Fragmentlogin fragmentlogin = new Fragmentlogin();
/*   private FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.ps_sch_activity_main, container, false);

        activity = (MainActivity) getActivity();


        // 프래그먼트 생성
        PSFragment1 = new PS_Fragment1();
        fragment2 = new Fragment2();

        // 먼저 화면에 프래그먼트1이 보이게 초기화
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.maincontain, PSFragment1).commit();


        // 반드시 만들어서 adapter에 넘겨야 한다
        dtos = new ArrayList<>();


        // 어댑터 객체 생성
        adapter = new AndroidAdapter(activity, dtos);

        editText = rootView.findViewById(R.id.edittext);
        position = 0;
        Log.d(TAG, "onTabSelectedOut: " + position);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            /* 검색 */
            @Override
            public void afterTextChanged(Editable s) {
                mPSSearchDTOS = PSFragment1.getmExampleList();
                mPSSearchDTOS2 = fragment2.getmExampleList();

                if(mPSSearchDTOS != null && position != 1){
                    filter(s.toString());

                }else if(mPSSearchDTOS2 != null){
                    filter_bookmark(s.toString());
                }

            }


        });


        // 탭을 동적으로 생성한다
        tabs = rootView.findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("가까운거리"));
        tabs.addTab(tabs.newTab().setText("즐겨찾기"));

        // 탭 레이아웃에 리스너를 달아준다
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            // 탭이 선택되었을때
            @Override  //                 선택된 탭
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();   // 0, 1, 2
                Log.d(TAG, "onTabSelected: " + position);
                if (position == 0) {
                    selFragment = PSFragment1;

                } else if (position == 1) {
                    if(loginDTO == null) {
                        selFragment = fragmentlogin;

                    }else if(loginDTO != null){
                        selFragment = fragment2;
                    }

                }

                getFragmentManager().beginTransaction()
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

        return rootView;
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    //filter 함수(검색)
    private void filter(String text) {
        filteredList = new ArrayList<>();
        mAdapter = new PS_SearchAdapter(activity,filteredList);

        for (StoreDTO item : mPSSearchDTOS) {
            if (item.getLocation().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        PSFragment1.editSetAdapter(filteredList);
        mAdapter.filterList(filteredList);

    }

    //filter 함수(검색)
    private void filter_bookmark(String text) {
        filteredList = new ArrayList<>();
        mAdapter = new PS_SearchAdapter(activity,filteredList);

        for (StoreDTO item : mPSSearchDTOS2) {
            if (item.getLocation().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        fragment2.editSetAdapter(filteredList);
        mAdapter.filterList(filteredList);

    }

    public void arraylist_frag1(ArrayList<StoreDTO> arr){
        filteredList = arr;
    }


}

