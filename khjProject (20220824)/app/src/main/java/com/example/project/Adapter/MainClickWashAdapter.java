package com.example.project.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.DTO.MainWashDTO;
import com.example.project.R;

import java.util.ArrayList;

public class MainClickWashAdapter extends
        RecyclerView.Adapter<MainClickWashAdapter.ViewHolder>{

    private static final String TAG = "main:MainWashAdapter";

    // 3. 메인한테 넘겨 받는것
    Context context;
    ArrayList<MainWashDTO> dtos;
    LayoutInflater inflater;

    // 4. 생성자를 만들어 메인에게서 넘겨받은것을 연결
    public MainClickWashAdapter(Context context, ArrayList<MainWashDTO> dtos) {
        this.context = context;
        this.dtos = dtos;

        inflater = LayoutInflater.from(this.context);
    }

    // 5. 메소드는 여기에 만든다
    ///////////////////////////////////////////////////////////////////


    // 6. 화면을 인플레이트 시킨다
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.fragment_main_clickwash_item, parent, false);

        return new ViewHolder(itemView);
    }

    // 7. 인플레이트 시킨 화면에 데이터를 셋팅시킨다
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // dtos에 있는 데이터를 순서대로 불러온다
        MainWashDTO dto = dtos.get(position); // 다섯개라고 가정하면 position은 0~4
        // 불러온 데이터를 ViewHolder에 만들어 놓은 setDto를 사용하여 셋팅한다
        holder.setDto(dto);

    }

    @Override
    public int getItemCount() {
        return dtos.size();
    }

    // 1. xml 파일에서 사용된 모든변수와 사용할 레이아웃을 클래스에서 선언한다 : 별 5개
    public class ViewHolder extends RecyclerView.ViewHolder{
        // 1-1. _item.xml 에 사용된 모든 위젯을 정의한다
        TextView tv_number, tv_remaintime, tv_yesno;
        ImageView laundryimage;

        // 1-2. example_item.xml 에서 정의한 아이디를 찾아 연결시킨다(생성자)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_number = itemView.findViewById(R.id.tv_number);
            tv_remaintime = itemView.findViewById(R.id.tv_remaintime);
            tv_yesno = itemView.findViewById(R.id.tv_yesno);
            laundryimage = itemView.findViewById(R.id.laundryimage);

        }

        // 1-3. 함수를 만들어서 example_item 에 데이터를 연결시킨다
        public void setDto(MainWashDTO dto){
            tv_number.setText(dto.getMachineid());

            if(!dto.getResttime().equals("")){
                tv_remaintime.setText(dto.getResttime());
                tv_yesno.setText("사용중");
                tv_yesno.setBackgroundColor(Color.RED);
                laundryimage.setImageResource(R.drawable.laundrymove);

            }else{
                tv_remaintime.setText("0");
                tv_yesno.setText("사용가능");
                tv_yesno.setBackgroundColor(Color.GREEN);
                laundryimage.setImageResource(R.drawable.laundrystop);

            }


        }
    }


}
