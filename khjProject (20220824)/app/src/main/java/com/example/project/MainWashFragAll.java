package com.example.project;

import static com.example.project.Common.CommonMethod.Qrin;
import static com.example.project.Common.CommonMethod.loadInfo;
import static com.example.project.Common.CommonMethod.loginDTO;
import static com.example.project.Common.CommonMethod.storeDTOS;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.project.ATask.MainWashSelect;
import com.example.project.Adapter.MainWashAdapter;
import com.example.project.DTO.MainWashDTO;
import com.example.project.DTO.StoreDTO;

import java.util.ArrayList;

public class MainWashFragAll extends Fragment {
    private static final String TAG = "main:MainWashFragAll";

    ArrayList<String> arrayListGu, arrayListList, arrayListStoreid;

    ArrayAdapter<String> arrayAdapterGu;
    ArrayAdapter<String> arrayAdapterList ;
    RecyclerView mainWashRecycler;
    SwipeRefreshLayout swipeall;

    ArrayList<StoreDTO> dtos;
    ArrayList<MainWashDTO> mainWashDTOS;
    MainWashAdapter adapter;

    TextView tv_place, tv_money;
    String storeid = "";

    Spinner spinner_gu = null, spinner_list = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_wash_allrecycler, container, false);

        // 반드시 만들어서 adapter에 넘겨야 한다
        dtos = storeDTOS;
        mainWashDTOS = new ArrayList<>();

        tv_place = rootView.findViewById(R.id.tv_place);
        tv_money = rootView.findViewById(R.id.tv_money);
        swipeall = rootView.findViewById(R.id.swipeall);

        mainWashRecycler = rootView.findViewById(R.id.mainWashRecycler);
        // recyclerView 에서 반드시 아래와 같이 초기화를 해줘야 함
        LinearLayoutManager layoutManager = new LinearLayoutManager
                (requireContext(), RecyclerView.VERTICAL, false);
        mainWashRecycler.setLayoutManager(layoutManager);
        // 어댑터 객체 생성
//        adapter = new MainWashAdapter(getActivity(), dtos);
//        mainWashRecycler.setAdapter(adapter);


        spinner_gu = rootView.findViewById(R.id.spiner_gu);
        spinner_list = rootView.findViewById(R.id.spiner_list);

        arrayListGu = new ArrayList<>();
        arrayListGu.add("전체");
        arrayListGu.add("동구");
        arrayListGu.add("서구");
        arrayListGu.add("남구");
        arrayListGu.add("북구");
        arrayListGu.add("광산구");

        arrayAdapterGu = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,  arrayListGu);

        spinner_gu.setAdapter(arrayAdapterGu);
        spinner_gu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getContext(),arrayListGu.get(position)+"가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                arrayListList = new ArrayList<>();
                arrayListStoreid = new ArrayList<>();

                if(arrayListGu.get(position).equals("전체")){
                    for(int i=0; i<storeDTOS.size(); i++){
                        arrayListList.add(storeDTOS.get(i).getLocation());
                        arrayListStoreid.add(storeDTOS.get(i).getStoreid());
                    }
                } else if(arrayListGu.get(position).equals("동구")){
                    for(int i=0; i<storeDTOS.size(); i++){
                        if(storeDTOS.get(i).getAddress().contains("동구")){
                            arrayListList.add(storeDTOS.get(i).getLocation());
                            arrayListStoreid.add(storeDTOS.get(i).getStoreid());
                        }

                    }
                } else if(arrayListGu.get(position).equals("서구")){
                    for(int i=0; i<storeDTOS.size(); i++){
                        if(storeDTOS.get(i).getAddress().contains("서구")){
                            arrayListList.add(storeDTOS.get(i).getLocation());
                            arrayListStoreid.add(storeDTOS.get(i).getStoreid());
                        }

                    }
                } else if(arrayListGu.get(position).equals("남구")){
                    for(int i=0; i<storeDTOS.size(); i++){
                        if(storeDTOS.get(i).getAddress().contains("남구")){
                            arrayListList.add(storeDTOS.get(i).getLocation());
                            arrayListStoreid.add(storeDTOS.get(i).getStoreid());
                        }

                    }
                } else if(arrayListGu.get(position).equals("북구")){
                    for(int i=0; i<storeDTOS.size(); i++){
                        if(storeDTOS.get(i).getAddress().contains("북구")){
                            arrayListList.add(storeDTOS.get(i).getLocation());
                            arrayListStoreid.add(storeDTOS.get(i).getStoreid());
                        }

                    }
                } else if(arrayListGu.get(position).equals("광산구")){
                    for(int i=0; i<storeDTOS.size(); i++){
                        if(storeDTOS.get(i).getAddress().contains("광산구")){
                            arrayListList.add(storeDTOS.get(i).getLocation());
                            arrayListStoreid.add(storeDTOS.get(i).getStoreid());
                        }

                    }
                }

                arrayAdapterList = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayListList);
                spinner_list.setAdapter(arrayAdapterList);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinner_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getContext(),arrayListList.get(position)+"가 선택되었습니다.", Toast.LENGTH_SHORT).show();

                storeid = arrayListStoreid.get(position);

                Log.d(TAG, "storeid: " + storeid);

                for (int i=0; i<storeDTOS.size(); i++){
                   if(storeid.equals(storeDTOS.get(i).getStoreid())){
                       tv_place.setText(storeDTOS.get(i).getAddress() + "(" + storeDTOS.get(i).getLocation() + ")");
                       tv_money.setText(loginDTO.getPoint());
                       break;
                   }
                }

                adapter = new MainWashAdapter(getContext(), mainWashDTOS);
                mainWashRecycler.setAdapter(adapter);

                MainWashSelect mainWashSelect = new MainWashSelect(mainWashDTOS, adapter, storeid);
                mainWashSelect.execute();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        swipeall.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MainWashSelect mainWashSelect = new MainWashSelect(mainWashDTOS, adapter, storeid);
                mainWashSelect.execute();

                swipeall.setRefreshing(false);
                tv_money.setText(loginDTO.getPoint());
            }
        });

        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();

        if(Qrin == 1){
            Log.d(TAG, "Qrin : 들어옴");

            setSelSpine();
            Qrin = 0;
        }
    }

    public void setSelSpine(){
        String QRstoreid = loadInfo(getContext(), "storeid");
        if(QRstoreid != ""){
            storeid = QRstoreid;
            Log.d(TAG, "qrstoreid : " + storeid);
        }

        for (int i=0; i<storeDTOS.size(); i++){
            if(storeid.equals(storeDTOS.get(i).getStoreid())){
                Log.d(TAG, "listnum : " + i);
                spinner_list.setSelection(i);
                break;
            }
        }

    }


}
