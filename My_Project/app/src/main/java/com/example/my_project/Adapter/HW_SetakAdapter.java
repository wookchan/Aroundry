package com.example.my_project.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.example.my_project.Adapter.Setak.HW_Listinformation;
import com.example.my_project.DATA.HW_SetakDTO;
import com.example.my_project.HW_Home;
import com.example.my_project.Fragment.HW_ListFragment;
import com.example.my_project.R;

import java.util.ArrayList;

public class HW_SetakAdapter extends RecyclerView.Adapter<HW_SetakAdapter.ViewHolder> {
    private static final String TAG = "Main:setakAdapter";

    HW_Home homeActivity;
    Context context;
    ArrayList<HW_SetakDTO> dtos;
    HW_ListFragment listfragment;
    LayoutInflater inflater;
    AlertDialog alertDialog;
    String userid;

    public HW_SetakAdapter(Context context, ArrayList<HW_SetakDTO> dtos,String userid) {
        this.context = context;
        this.dtos = dtos;
        inflater = LayoutInflater.from(this.context);
        this.userid = userid;
    }

    //dto 추가
    public void addDTO(HW_SetakDTO dto) {
        dtos.add(dto);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.hw_activity_homeviewer,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        HW_SetakDTO dto = dtos.get(position); // dto의 데이터를 순서대로 배치함
        holder.setDTO(dto); // 엄연히 리스트인듯 함


    }


    @Override
    public int getItemCount() {

        return dtos.size();

    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout parentLayout;
        ImageView homeimages;
        TextView homelocation,homeitems;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout =itemView.findViewById(R.id.parentLayout);
            homeimages = itemView.findViewById(R.id.homeimages);
            homelocation = itemView.findViewById(R.id.homelocation);
            homeitems = itemView.findViewById(R.id.homeitems);
            /*listfragment =  FragmentManager.findFragment(homeActivity.findViewById(R.id.listfragment));
            clicklocation =  FragmentManager.findFragment(itemView.findViewById(R.id.clickcontain));*/
        }
        public void setDTO(HW_SetakDTO dto) {
            //Log.d(TAG, "setDTO : " + dto.getImgpath());
            //위치정보
            homelocation.setText(dto.getTitlelocation());
            //개수
            homeitems.setText("세탁기 "+dto.getMachine() + " 대");
            //이미지 파일 설정
            if(dto.getImgpath().equals("NoImage") || dto.getImgpath().toUpperCase().equals("(NULL)")) {
                homeimages.setImageResource(R.drawable.nullimg); // 이미지없음
            }
            else if(dto.getImgpath().contains("drawable")   )   {
                Log.d(TAG, "setDTO : 들어옴");

                String name = dto.getImgpath().replace("R.drawable.", "");
                int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName()); // R.drawable.xxxx

                Glide.with(context).load(id).into(homeimages);

            }
            else if(dto.getImgpath().contains("http://")   )   {
                Glide.with(context).load(dto.getImgpath()).centerCrop().into(homeimages);
                Log.d(TAG, "homeimages: "+homeimages);
            }
            else if(!dto.getImgpath().contains("https://") || !(dto.getImgpath().contains("drawble"))) {
                Glide.with(context).load(dto.getImgpath()).centerCrop().into(homeimages);
                Log.d(TAG, "homeimages: "+homeimages);
            }
            else {
                Toast.makeText(homeimages.getContext(), "이미지 에러", Toast.LENGTH_SHORT).show();
            }
           //혹시 모름

           parentLayout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(context, HW_Listinformation.class);
                   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                   //CLEAR_TOP : 새로운 인텐트위의 것들을 없앰
                   //intent.putExtra("HW_Location",dto.getLocation());
                   //intent.putExtra("HW_Imgpath",dto.getImgpath());
                   intent.putExtra("HW_DTO", dto);
                   intent.putExtra("userid", userid);
                   // 받을떄 (putExtra)
                   // HW_SetakDTO HW_setakDTO = (HW_SetakDTO) intent.getSerializableExtra("HW_DTO");
                   context.startActivity(intent);

                  // Toast.makeText(context.getApplicationContext(), "새끼기열 "+parentLayout, Toast.LENGTH_SHORT).show();
                  /* Bundle bundle = new Bundle();
                   bundle.putString("getLocation",dto.getLocation());
                   homeActivity.getSupportFragmentManager().beginTransaction().replace(R.id.listfragment,clicklocation).commit();*/
               }
           });

        }
    }

}
