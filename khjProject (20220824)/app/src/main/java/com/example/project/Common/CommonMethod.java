package com.example.project.Common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.project.DTO.MemberDTO;
import com.example.project.DTO.RecentDTO;
import com.example.project.DTO.StoreDTO;

import java.util.ArrayList;

public class CommonMethod {
  public static String ipConfig = "http://192.168.0.40:8989";
    //public static String ipConfig = "http://192.168.0.7:8080";

    //public static MemberDTO loginDTO = new MemberDTO("aaa", "aaa@naver.com", "01012345678", "aaa","http://192.168.0.24:8989/app/resources/dream03.jpg");
    public static RecentDTO recentDTO = new RecentDTO("http://192.168.0.48:8989/search/resources/purple.jpg");
    //public static RecentDTO recentDTO = new RecentDTO("http://192.168.0.7:8080/search/resources/purple.jpg");
    //public static MoneyDTO moneyDTO = new MoneyDTO("500,000원");
    //public static CleanDTO cleanDTO = new CleanDTO("한울세탁소", "35.153447", "126.887794", "광주광역시 서구 농성1동 280-4" );
    public static MemberDTO loginDTO = null;
    //public static PS_SearchDTO ps_searchDTO = null;
    public static ArrayList<StoreDTO> storeDTOS = null;
    public static int Qrin = 0;
    //public static String machineseq = "";

  // 핸드폰 내부 저장공간에 데이터 저장하기
  public static void saveInfo(Context context, String key, String value){
    SharedPreferences pref = context.getSharedPreferences("myinfo",
            Activity.MODE_PRIVATE);
    SharedPreferences.Editor editor = pref.edit();
    editor.putString(key, value);
    editor.commit();
  }

  // 핸드폰 내부 저장공간에서 데이터 가져오기
  public static String loadInfo(Context context, String key){
    SharedPreferences pref = context.getSharedPreferences("myinfo",
            Activity.MODE_PRIVATE);
    String val = "";
    if(pref != null){
      val = pref.getString(key, "");
    }
    return val;
  }


}
