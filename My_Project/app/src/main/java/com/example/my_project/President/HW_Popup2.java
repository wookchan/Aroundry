package com.example.my_project.President;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_project.R;
public class HW_Popup2 extends AppCompatActivity {
    private static final String TAG = "HW_Popup2";
    float pressedTime;
    EditText popup2originpw,popup2chpw,popup2chpwchk;
    TextView popup2originpwchk,popup2chkblank,popup2chpwchktext;
    int chkstack=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_hw_popup2);
        Intent intent = getIntent();
        String pw="";
        String originpw = intent.getStringExtra("pw");
        Log.d(TAG, "비밀번호는: "+originpw);
        // 원래 비밀번호 비밀번호 일치여부
        popup2originpw = findViewById(R.id.popup2originpw);
        // 바꿀 비밀번호
        popup2chpw = findViewById(R.id.popup2chpw);
        // 바꿀 비밀번호  확인
        popup2chpwchk = findViewById(R.id.popup2chpwchk);

        // 비밀번호 불일치 시 뜰 텍스트
        popup2originpwchk = findViewById(R.id.popup2originpwchk);
        // 변경 비밀번호 텍스트
        popup2chkblank = findViewById(R.id.popup2chkblank);
        // 변경 비밀번호 확인 텍스트
        popup2chpwchktext = findViewById(R.id.popup2chpwchktext);







        //취소 버튼 눌렷을떄
        findViewById(R.id.popup2cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( pressedTime ==0) {
                    Toast.makeText(HW_Popup2.this, "한번 더 누를 시 창이 닫힙니다.", Toast.LENGTH_SHORT).show();
                    pressedTime = System.currentTimeMillis();
                }

                else {
                    int seconds = (int) (System.currentTimeMillis() - pressedTime);
                    /*2초*/
                    if(seconds > 2000) {
                        Toast.makeText(HW_Popup2.this, "한번 더 누를시 창이 닫힙니다.", Toast.LENGTH_SHORT).show();
                        pressedTime = 0;
                    }
                    else {
                        finish();
                    }
                }
            }
        });

    }
    //백버튼 막기
    @Override
    public void onBackPressed() {
        if ( pressedTime ==0) {
            Toast.makeText(HW_Popup2.this, "한번 더 누를 시 창이 닫힙니다.", Toast.LENGTH_SHORT).show();
            pressedTime = System.currentTimeMillis();
        }

        else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);
            /*2초*/
            if(seconds > 2000) {
                Toast.makeText(HW_Popup2.this, "한번 더 누를시 창이 닫힙니다.", Toast.LENGTH_SHORT).show();
                pressedTime = 0;
            }
            else {
                super.onBackPressed();
            }
        }

    }
}