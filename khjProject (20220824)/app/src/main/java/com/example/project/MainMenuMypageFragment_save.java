/*
package com.example.project;


import static com.example.project.Common.CommonMethod.loginDTO;
import static com.example.project.Common.CommonMethod.moneyDTO;
import static com.example.project.Common.CommonMethod.recentDTO;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;


public class MainMenuMypageFragment_save extends Fragment {


    private View view;
    ImageView page_name, page_money1, page_qrcode, page_new, page_image;
    TextView page_name2, page_money2;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_menu_mypage, container, false);


        page_name = (ImageView) view.findViewById(R.id.page_name);
        page_money1 = (ImageView) view.findViewById(R.id.page_money1);
        page_qrcode = (ImageView) view.findViewById(R.id.page_qrcode);
        page_new = (ImageView) view.findViewById(R.id.page_new);
        page_image = (ImageView) view.findViewById(R.id.page_image);
        page_name2 = (TextView) view.findViewById(R.id.page_name2);
        page_money2 = (TextView) view.findViewById(R.id.page_money2);

        Glide
            .with(this)
            .load(loginDTO.getProfileimage())
            .circleCrop()
            .placeholder(R.drawable.person)
            .into(page_name);

        page_name2.setText(loginDTO.getName());
        page_money2.setText(moneyDTO.getMoney());

        Glide
                .with(this)
                .load(recentDTO.getRecent())
                .circleCrop()
                .placeholder(R.drawable.cleaning)
                .into(page_image);







        page_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), detail_pageActivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        page_money1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Money_Detail.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        page_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PS_QRScannerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        page_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), kkbMainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        return view;
    }


}
*/
