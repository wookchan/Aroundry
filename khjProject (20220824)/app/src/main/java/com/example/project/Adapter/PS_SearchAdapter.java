package com.example.project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.DTO.StoreDTO;
import com.example.project.R;
import com.example.project.kkbMainActivity;

import java.util.ArrayList;

public class PS_SearchAdapter extends
        RecyclerView.Adapter<PS_SearchAdapter.ViewHolder>{

    private static final String TAG = "main:PS_SearchAdapter";

    // 3. 메인한테 넘겨 받는것
    Context context;
    ArrayList<StoreDTO> dtos, filterList;
    LayoutInflater inflater;

    int position1;


    // 4. 생성자를 만들어 메인에게서 넘겨받은것을 연결
    public PS_SearchAdapter(Context context, ArrayList<StoreDTO> dtos) {
        this.context = context;
        this.dtos = dtos;

        inflater = LayoutInflater.from(this.context);


    }

    // 5. 메소드는 여기에 만든다
    ///////////////////////////////////////////////////////////////////
    //filter 와 관련된 filteredList 함수
    public void filterList(ArrayList<StoreDTO> filteredListin) {
        filterList = filteredListin;
        dtos = filteredListin;
        notifyDataSetChanged();
    }

    // 6. 화면을 인플레이트 시킨다
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.ps_sch_example_item, parent, false);

        return new ViewHolder(itemView);
    }

    // 7. 인플레이트 시킨 화면에 데이터를 셋팅시킨다
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // dtos에 있는 데이터를 순서대로 불러온다
        StoreDTO dto = dtos.get(position); // 다섯개라고 가정하면 position은 0~4
        // 불러온 데이터를 ViewHolder에 만들어 놓은 setDto를 사용하여 셋팅한다
        holder.setDto(dto);

    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    // 1. xml 파일에서 사용된 모든변수와 사용할 레이아웃을 클래스에서 선언한다 : 별 5개
    public class ViewHolder extends RecyclerView.ViewHolder{
        // 1-1. ps_sch_example_item.xml 에 사용된 모든 위젯을 정의한다
        TextView sch_distance, sch_loc, sch_rest;
        LinearLayout sch_linear;
        ImageView sch_iv;

        // 1-2. example_item.xml 에서 정의한 아이디를 찾아 연결시킨다(생성자)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sch_linear = itemView.findViewById(R.id.sch_linear);
            sch_loc = itemView.findViewById(R.id.sch_loc);
            sch_distance = itemView.findViewById(R.id.sch_distance);
            sch_rest = itemView.findViewById(R.id.sch_rest);
            sch_iv = itemView.findViewById(R.id.sch_iv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StoreDTO dto = null;
                    if(filterList != null) {
                        dto = filterList.get(getAdapterPosition());
                    }else {
                        dto = dtos.get(getAdapterPosition());
                    }
                    Intent intent = new Intent(v.getContext(), kkbMainActivity.class);
                    intent.putExtra("dto", dto);
                    context.startActivity(intent);
                }
            });
        }

        // 1-3. 함수를 만들어서 example_item 에 데이터를 연결시킨다
        public void setDto(StoreDTO dto){
            sch_loc.setText(dto.getLocation());
            sch_distance.setText(dto.getDistance() + " km");
            sch_rest.setText("사용가능 " + dto.getRest_cnt() + "대");

            // 만약에 이미지가 없으면 : NoImage
            if(dto.getImageurl().equals("")){
                sch_iv.setImageResource(R.drawable.guest); // 기본이미지
            }else if(dto.getImageurl().contains("http://")){
                String[] imageurl = dto.getImageurl().split(",");
                 Glide.with(context)
                    .load(imageurl[0])
                    //.circleCrop()
                    .centerCrop()
                    .into(sch_iv);
            }else if(dto.getImageurl().contains("https://")){
                String[] imageurl = dto.getImageurl().split(",");
                Glide.with(context)
                        .load(imageurl[0])
                        //.circleCrop()
                        .centerCrop()
                        .into(sch_iv);
            }
        }
    }


}
