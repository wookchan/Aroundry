package com.example.my_project.AsyncT;

import static com.example.my_project.DATA.CommonMethod.ipConfig;
import static com.example.my_project.DATA.CommonMethod.loginDTO;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.my_project.DATA.HW_OwnerDTO;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class LoginTask extends AsyncTask<Void,Void,Void> {
    private static final String TAG = "loginTask";
    ArrayList<HW_OwnerDTO> dtos;
    String id, pw;

    public LoginTask(String id, String pw,ArrayList<HW_OwnerDTO> dtos) {
        this.dtos = dtos;
        this.id = id;
        this.pw = pw;
    }

    // 반드시 선언해야 할것들 : 무조건 해야함 복,붙
    HttpClient httpClient;       // 클라이언트 객체
    HttpPost httpPost;           // 클라이언트에 붙일 본문
    HttpResponse httpResponse;   // 서버에서의 응답을 받는 부분
    HttpEntity httpEntity;       // 응답내용


    @Override
    protected Void doInBackground(Void... voids) {
        Log.d(TAG, "try: 진입 "+id+","+pw);
        try {
            // 무조건 해야함 : 복,붙
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            // 여기가 우리가 수정해야 하는 부분 : 서버로 보내는 데이터
            // builder에 문자열 및 파일 첨부하는곳
            builder.addTextBody("id", id, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("pw", pw, ContentType.create("Multipart/related", "UTF-8"));

            String postURL = ipConfig+"/laundry/Hwownerlogin";
            // 그대로 복,붙
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost); // 보내고 응답받는 부분
            httpEntity = httpResponse.getEntity();  // 응답내용을 저장
            inputStream = httpEntity.getContent();  // 응답내용을 inputStream에 넣음

            // 응답처리 : DTO 형태 :
            loginDTO = readMessage(inputStream);

        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (httpEntity != null) {
                httpEntity = null;
            }
            if (httpResponse != null) {
                httpResponse = null;
            }
            if (httpPost != null) {
                httpPost = null;
            }
            if (httpClient != null) {
                httpClient = null;
            }
        }
        return null;

    }
    // 3. doInBackground 실행(작업)후에 오는부분
    @Override                   // 세번째 파라메터
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }

    private HW_OwnerDTO readMessage(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "utf-8"));

        String ownerid="", name="", phone="", password="", profileurl="";
        reader.beginObject();
        while (reader.hasNext()){
            String readStr = reader.nextName();
            if(readStr.equals("ownerid")){
                ownerid = reader.nextString();
            }
            else if(readStr.equals("password")){
                password = reader.nextString();
            }
            else if(readStr.equals("phone")){
                phone = reader.nextString();
            }
            else if(readStr.equals("name")){
                name = reader.nextString();
            }
            else if(readStr.equals("profileurl")){
                profileurl = reader.nextString();
            }
            else {
                reader.skipValue();
            }
        } // while
        reader.endObject();
        Log.d(TAG, "readMessage: " + id + ", " +name);

        return new HW_OwnerDTO(ownerid,password,phone,name,profileurl);
    }



}
