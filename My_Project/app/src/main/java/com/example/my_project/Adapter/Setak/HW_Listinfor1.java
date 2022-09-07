package com.example.my_project.Adapter.Setak;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.my_project.DATA.HW_SetakDTO;
import com.example.my_project.R;

import java.util.ArrayList;
import java.util.Collections;

public class HW_Listinfor1 extends Fragment {
    private static final String TAG = "HW_Listinfor1";
    HW_Listinformation homeActivity;
    Context context;
    RecyclerView listinfor1_recycler;
    ImageView listinfor1_titleimg;
    TextView listinfor1_location,listinfor1_titlelocation;
    HW_Listinfor1Adapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_hw__listinfor1, container, false);
        homeActivity = (HW_Listinformation) getActivity();
        context = homeActivity.getApplicationContext();
        Intent intent = homeActivity.getIntent();
        HW_SetakDTO DTO = (HW_SetakDTO) intent.getSerializableExtra("DTO");
        String userid = intent.getStringExtra("userid");

        Log.d(TAG, "HW_Listinfor1.DTO : " + DTO.getConven());
        Log.d(TAG, "HW_Listinfor1.userid: " + userid);
        //타이틀
        listinfor1_titlelocation = viewGroup.findViewById(R.id.listinfor1_titlelocation);
        //자세한 장소
        listinfor1_location = viewGroup.findViewById(R.id.listinfor1_location);

        listinfor1_location.setText(DTO.getLocation());
        listinfor1_titlelocation.setText(DTO.getTitlelocation());


        //이미지 박기 시작
        listinfor1_titleimg =  viewGroup.findViewById(R.id.listinfor1_titleimg);

        if(DTO.getImgpath().equals("NoImage")) {
            listinfor1_titleimg.setImageResource(R.drawable.nullimg); // 이미지없음
        }
        else if(DTO.getImgpath().contains("drawable")   )   {
            Log.d(TAG, "HW리스트인포1 : 들어옴");

            String name = DTO.getImgpath().replace("R.drawable.", "");
            int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName()); // R.drawable.xxxx

            Glide.with(context).load(id).into(listinfor1_titleimg);

        }
        else if(DTO.getImgpath().contains("http://")   )   {
            Glide.with(context).load(DTO.getImgpath()).circleCrop().into(listinfor1_titleimg);

        }
        else if(!DTO.getImgpath().contains("https://") || !(DTO.getImgpath().contains("drawble"))) {
            Glide.with(context).load(DTO.getImgpath()).centerCrop().into(listinfor1_titleimg);
        }
        else {
            Toast.makeText(listinfor1_titleimg.getContext(), "이미지 에러", Toast.LENGTH_SHORT).show();
        }
        // 이미지박기 끝

        listinfor1_recycler = viewGroup.findViewById(R.id.listinfor1_recycler);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(this.context,listinfor1_recycler.HORIZONTAL,false);
        listinfor1_recycler.setLayoutManager(LayoutManager);
        adapter = new HW_Listinfor1Adapter(DTO,context);

//        String[] conven = new String[0];
//        if(!DTO.getConven().isEmpty() ) {
//            conven = DTO.getConven().split(",");
//         //   Log.d(TAG, "plus: "+plus[0]+","+plus[1]);
//            ////CCTV,오락시설,화장실,콘센트,와이파이,노래방
//            for(int i = 0;i<conven.length; i++) {
//                conven[i] = conven[i];
//                Log.d(TAG, "dto empty : plus : " + conven[i]);
//                if(conven[i].equals("CCTV")) {
//                    adapter.addDTO(new String(conven[i]));
//                }
//                if(conven[i].equals("오락시설")) {
//                    adapter.addDTO(new String(conven[i]));
//                }
//                if(conven[i].equals("와이파이")) {
//                    adapter.addDTO(new String(conven[i]));
//                }
//                if(conven[i].equals("화장실")) {
//                    adapter.addDTO(new String(conven[i]));
//                }
//                if(conven[i].equals("콘센트")) {
//                    adapter.addDTO(new String(conven[i]));
//                }
//                if(conven[i].equals("노래방")) {
//                    adapter.addDTO(new String(conven[i]));
//                }
//
//            }
//        }ㅅㅁ
        Log.d(TAG, "f값: "+DTO.getF_coin());
        if(DTO.getF_cctv() != 0) {
            adapter.addDTO(new String("CCTV"));
        }
        if(DTO.getF_game() != 0) {
            adapter.addDTO(new String("오락시설"));
        }
        if(DTO.getF_toilet() != 0) {
            adapter.addDTO(new String("화장실"));
        }
        if(DTO.getF_concent() != 0) {
            adapter.addDTO(new String("콘센트"));
        }
        if(DTO.getF_wifi() != 0) {
            adapter.addDTO(new String("와이파이"));
        }
        if(DTO.getF_coin() !=0) {
            adapter.addDTO(new String("노래방"));
        }
        listinfor1_recycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return viewGroup;
    }
}