package com.example.my_project.Adapter.Setak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.my_project.DATA.HW_SetakDTO;
import com.example.my_project.R;

public class HW_Listinformation extends AppCompatActivity {
    private static final String TAG = "HW_Listinformation";
    TextView setakinfor, setakinfor2;
    Context context;
    HW_Listinfor1 hwListinfor1;
    HW_Listinfor2 hwListinfor2;
    int viewses = 0;
    HW_Listinformation home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hw_activity_list_information);
        setakinfor = findViewById(R.id.setakinfor);
        setakinfor2 = findViewById(R.id.setakinfor2);
        Intent intent = getIntent();
        HW_SetakDTO DTO = (HW_SetakDTO) intent.getSerializableExtra("HW_DTO");
        String userid = intent.getStringExtra("userid");
        intent.putExtra("DTO",DTO);
        intent.putExtra("userid",userid);
        Log.d(TAG, "DTO: "+DTO.getConven());
        hwListinfor1 = new HW_Listinfor1();
        hwListinfor2 = new HW_Listinfor2();

        getSupportFragmentManager().beginTransaction().replace(R.id.clickcontain, hwListinfor1).commit();
        setakinfor.setTypeface(null, Typeface.BOLD);
        if (viewses != 1 || viewses == 0) {
            viewses = 1;

            setakinfor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setakinfor2.setTypeface(null, Typeface.NORMAL);
                    setakinfor.setTypeface(null, Typeface.BOLD);
                    getSupportFragmentManager().beginTransaction().replace(R.id.clickcontain, hwListinfor1).commit();
                }
            });
        }

        if (viewses != 2) {
            viewses = 2;

            setakinfor2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setakinfor.setTypeface(null, Typeface.NORMAL);
                    setakinfor2.setTypeface(null, Typeface.BOLD);
                    getSupportFragmentManager().beginTransaction().replace(R.id.clickcontain, hwListinfor2).commit();
                }
            });
        }
    }
}
