package com.example.my_project.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.my_project.HW_Home;
import com.example.my_project.President.HW_Popup;
import com.example.my_project.President.HW_Popup2;
import com.example.my_project.President.HW_Popup3;
import com.example.my_project.R;

public class HW_PresidentFragment extends Fragment {
    TextView president_user,president_notice,president_chpw,president_chph;
    ImageView president_profile;
    HW_Home homeActivity;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewgroup = (ViewGroup) inflater.inflate(R.layout.hw_fragment_president,container,false);
        String userid = getArguments().getString("id");
        String username = getArguments().getString("name");
        String profile = getArguments().getString("profile");
        String pw = getArguments().getString("pw");
        String tel = getArguments().getString("tel");

        Bundle bundle = new Bundle();
        bundle.putString("userid",userid);
        homeActivity = (HW_Home) getActivity();
        context = homeActivity.getApplicationContext();

        president_user = viewgroup.findViewById(R.id.president_user);
        president_profile = viewgroup.findViewById(R.id.president_profile);


        //유저 이름 박기 처리
        president_user.setText(username + " 회원 님");
        
        
        //사진처리
        if(profile.equals("NoImage") || profile.toUpperCase().equals("(NULL)")) {
            president_profile.setImageResource(R.drawable.man); // 이미지없음
        }
        else if(profile.contains("drawable")   )   {

            String name = profile.replace("R.drawable.", "");
            int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName()); // R.drawable.xxxx

            Glide.with(context).load(id).into(president_profile);

        }
        else if(profile.contains("http://")   )   {
            Glide.with(context).load(profile).centerCrop().into(president_profile);
        }
        else if(!profile.contains("https://") || !(profile.contains("drawble"))) {
            Glide.with(context).load(profile).centerCrop().into(president_profile);
        }
        else {
            Toast.makeText(president_profile.getContext(), "이미지 에러", Toast.LENGTH_SHORT).show();
        }
        //혹시 모름
        
        // 콩지사항
        viewgroup.findViewById(R.id.president_notice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HW_Popup.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        // 비밀번호 변경
        viewgroup.findViewById(R.id.president_chpw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HW_Popup2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("pw",pw);
                context.startActivity(intent);
            }
        });

        // 핸드폰 번호 변경
        viewgroup.findViewById(R.id.president_chph).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HW_Popup3.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("tel",tel);
                context.startActivity(intent);
            }
        });
        return viewgroup;

    }
}