package com.example.project.ATask;

import static com.example.project.Common.CommonMethod.ipConfig;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.project.Adapter.AndroidAdapter;
import com.example.project.DTO.MemberDTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MemberSelect extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "main:MemberSelect";

    ArrayList<MemberDTO> dtos;
    AndroidAdapter adapter;

    // 우리는 무조건 생성자를 만들어서 데이터를 넘겨받는다
    public MemberSelect(ArrayList<MemberDTO> dtos, AndroidAdapter adapter) {
        this.dtos = dtos;
        this.adapter = adapter;
    }

    // 반드시 선언해야 할것들 : 무조건 해야함 복,붙
    HttpClient httpClient;       // 클라이언트 객체
    HttpPost httpPost;           // 클라이언트에 붙일 본문
    HttpResponse httpResponse;   // 서버에서의 응답을 받는 부분
    HttpEntity httpEntity;       // 응답내용

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            // 무조건 해야함 : 복,붙
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            // 여기가 우리가 수정해야 하는 부분 : 서버로 보내는 데이터
            // builder에 문자열 및 파일 첨부하는곳

            // 전송
            // 전송 Url : 우리가 수정해야 하는 부분
            String postURL = ipConfig + "/app/anMemberSelect";

            // 그대로 복,붙
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost); // 보내고 응답받는 부분
            httpEntity = httpResponse.getEntity();  // 응답내용을 저장
            inputStream = httpEntity.getContent();  // 응답내용을 inputStream에 넣음

            // 응답처리 : 데이터가 ArrayList<DTO> 형태 :
            readJsonStream(inputStream);
        }catch (Exception e){
            e.getMessage();
        }finally {
            if(httpEntity != null){
                httpEntity = null;
            }
            if(httpResponse != null){
                httpResponse = null;
            }
            if(httpPost != null){
                httpPost = null;
            }
            if(httpClient != null){
                httpClient = null;
            }
        }

        return null;
    }

    // 3. doInBackground 실행(작업)후에 오는부분
    @Override                   // 세번째 파라메터
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        // 화면갱신
        adapter.notifyDataSetChanged();
    }

    // ArrayList<DTO>로 넘어왔을때
    private void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader
                (new InputStreamReader(inputStream, "utf-8"));
        try {
            reader.beginArray();
            while (reader.hasNext()){
                dtos.add(readMessage(reader));
            }
            reader.endArray();

        }catch (Exception e){
            e.getMessage();
        }finally {
            reader.close();
        }

    }
    // 하나의 DTO형태로 데이터를 받을때 파싱하는 부분
    private MemberDTO readMessage(JsonReader reader) throws IOException {
        String id="", name="", phonenumber="", email="", profileimage="";

        reader.beginObject();
        while (reader.hasNext()){
            String readStr = reader.nextName();
            if(readStr.equals("id")){
                id = reader.nextString();
            }else if(readStr.equals("name")){
                name = reader.nextString();
            }else if(readStr.equals("phonenumber")){
                phonenumber = reader.nextString();
            }else if(readStr.equals("email")){
                email = reader.nextString();
            }else if(readStr.equals("profileimage")){
                profileimage = ipConfig + "/app/resources/" + reader.nextString();
            }else {
                reader.skipValue();
            }
        } // while
        reader.endObject();
        Log.d(TAG, "readMessage: " + id + ", " +name);

        return new MemberDTO(id, name, phonenumber, email, profileimage);
    }

}

