package com.example.project.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.DTO.MemberDTO;
import com.example.project.R;

import java.util.ArrayList;


public  class AndroidAdapter extends RecyclerView.Adapter<AndroidAdapter.ViewHolder>{

    Context context;
    ArrayList<MemberDTO> arrayList;



    LayoutInflater inflater;



    // 4. 생성자를 만들어 메인에게서 넘겨받은것을 연결
    public AndroidAdapter(Context context, ArrayList<MemberDTO> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

        inflater = LayoutInflater.from(this.context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.fragment_main_menu_mypage, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("main:adater", "" + position);


        MemberDTO  memberDTO = arrayList.get(position);
        // dtos에 있는 데이터를 순서대로 불러온다
        MemberDTO dto = arrayList.get(position); // 다섯개라고 가정하면 position은 0~4
        // 불러온 데이터를 ViewHolder에 만들어 놓은 setDto를 사용하여 셋팅한다
        holder.setDto(dto);



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    // 리사이클러뷰 내용 모두 지우기
    public void removeAllItem(){
        arrayList.clear();
    }

    // 특정 인덱스 항목 가져오기
    public MemberDTO memberDTO(int position) {
        return arrayList.get(position);
    }

    // 특정 인덱스 항목 셋팅하기
    public void setItem(int position, MemberDTO memberDTO){
        arrayList.set(position, memberDTO);
    }
    // arrayList 통째로 셋팅하기
    public void setItems(ArrayList<MemberDTO> arrayList){
        this.arrayList = arrayList;
    }

    // 1. xml 파일에서 사용된 모든변수와 사용할 레이아웃을 클래스에서 선언한다 : 별 5개
    public class ViewHolder extends RecyclerView.ViewHolder{
        //1-1. singerview.wml에 사용된 모든 위젯을 정의한다
        public TextView page_name2, page_money2, page_money3, page_qrcode2, page_new2;
        public ImageView page_name, page_money1, page_qrcode, page_new;
        public ImageView page_image;


        // 1-2. singerview.xml에서 정의한 아이디를 찾아 연결시킨다(생성자)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            page_name = itemView.findViewById(R.id.page_name);
            page_name2 = itemView.findViewById(R.id.page_name2);
            page_money1 = itemView.findViewById(R.id.page_money1);
            page_money2 = itemView.findViewById(R.id.page_money2);
            page_money3 = itemView.findViewById(R.id.page_money3);
            page_qrcode = itemView.findViewById(R.id.page_qrcode);
            page_qrcode2 = itemView.findViewById(R.id.page_qrcode2);
            page_new = itemView.findViewById(R.id.page_new);
            page_new2 = itemView.findViewById(R.id.page_new2);
            page_image = itemView.findViewById(R.id.page_image);


        }

        // 1-3. 함수를 만들어서 singerview에 데이터를 연결시킨다
        public void setDto(MemberDTO dto){
           page_name2.setText(dto.getUserid());



           Glide.with(itemView).load(page_image.getDrawable()).into(page_image);
            Glide.with(itemView).load(page_name.getDrawable()).into(page_name);

            }

        }

    }

