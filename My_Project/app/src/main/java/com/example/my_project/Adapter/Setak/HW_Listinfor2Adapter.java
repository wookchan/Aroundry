package com.example.my_project.Adapter.Setak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_project.Adapter.HW_SetakAdapter;
import com.example.my_project.AsyncT.ListTask;
import com.example.my_project.AsyncT.StoreTask;
import com.example.my_project.DATA.HW_SetakDTO;
import com.example.my_project.DATA.HW_StoreDTO;
import com.example.my_project.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HW_Listinfor2Adapter extends RecyclerView.Adapter<HW_Listinfor2Adapter.ViewHolder>{
    private static final String TAG = "hw_listinfor1Adapter ";
    
    Context context;
    LayoutInflater inflater;
    ArrayList<Object> list =new ArrayList<>();
    ArrayList<HW_StoreDTO> dtos;
    DecimalFormat decimalFormat = new DecimalFormat(",###");;

    public HW_Listinfor2Adapter(ArrayList<HW_StoreDTO> dtos, Context context) {
        this.dtos = dtos;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }
    public void addDTO(HW_StoreDTO dto) {
        dtos.add(dto);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.hw_listinfor2_adapter,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        HW_StoreDTO dto =  dtos.get(position);

        holder.setDTO(dto);
    }

    @Override
    public int getItemCount() {

        return dtos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView listinfor2_number,listinfor2_counting,listinfor2_cost;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listinfor2_number = itemView.findViewById(R.id.listinfor2_number);
            listinfor2_counting = itemView.findViewById(R.id.listinfor2_counting);
            listinfor2_cost = itemView.findViewById(R.id.listinfor2_cost);
           
        }
        public void setDTO(HW_StoreDTO dto) {

            listinfor2_number.setText(dtos.get(getPosition()).getMachineseq() + "번 세탁기");
            listinfor2_counting.setText(dtos.get(getPosition()).getUsing()+" 회 사용됨");
            listinfor2_cost.setText(decimalFormat.format(dtos.get(getPosition()).getUsing()*5000) + " 원");

        }
    }
}