package com.example.my_project.Adapter.Setak;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_project.DATA.HW_SetakDTO;
import com.example.my_project.R;

import java.util.ArrayList;

public class HW_Listinfor1Adapter extends RecyclerView.Adapter<HW_Listinfor1Adapter.ViewHolder> {
    private static final String TAG = "hw_listinfor1Adapter ";
    HW_SetakDTO DTO;
    Context context;
    LayoutInflater inflater;
    ArrayList<String> list =new ArrayList<>();
    ArrayList<Integer> list2 =new ArrayList<>();


    public HW_Listinfor1Adapter(HW_SetakDTO DTO, Context context) {
        this.DTO = DTO;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

   public void addDTO(String s) {
        list.add(s);
        Log.d(TAG, "s: " +list.get(0));
   }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.hw_listinfor_adapter,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      //  str2.get(position);
      //  holder.setDTO(str2,plus2);
       list.get(position);
       holder.setDTO(list);
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView listinfor1_img;
        TextView listinfor1_name;
        LinearLayout listinfor1_parentlayout;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                listinfor1_img = itemView.findViewById(R.id.listinfor1_img);
                listinfor1_name = itemView.findViewById(R.id.listinfor1_name);
                listinfor1_parentlayout = itemView.findViewById(R.id.listinfor1_parentlayout);
            }

            public void setDTO(ArrayList<String> list) {
                listinfor1_name.setText(list.get(getPosition()));
                ///CCTV,오락시설,화장실,콘센트,와이파이,노래방
                if(list.get(getPosition()).equals("CCTV")) {
                    listinfor1_img.setImageResource(R.drawable.cctv);
                }
                else if(list.get(getPosition()).equals("오락시설")) {
                    listinfor1_img.setImageResource(R.drawable.game);
                }
                else if(list.get(getPosition()).equals("화장실")) {
                    listinfor1_img.setImageResource(R.drawable.toilet);
                }
                else if(list.get(getPosition()).equals("콘센트")) {
                    listinfor1_img.setImageResource(R.drawable.concent);
                }  
                else if(list.get(getPosition()).equals("와이파이")) {
                    listinfor1_img.setImageResource(R.drawable.wifi);
                }  
                else if(list.get(getPosition()).equals("노래방")) {
                    listinfor1_img.setImageResource(R.drawable.sing);
                }
            }
    }
}

