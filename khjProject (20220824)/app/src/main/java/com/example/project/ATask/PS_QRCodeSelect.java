package com.example.project.ATask;

import static com.example.project.Common.CommonMethod.ipConfig;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.project.DTO.MemberDTO;

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
import java.nio.charset.Charset;

public class PS_QRCodeSelect extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "main:PS_QRCodeSelect";


    // 우리는 무조건 생성자를 만들어서 데이터를 넘겨받는다
    String name, point;

    public PS_QRCodeSelect(String name/*, String point*/) {
        this.name = name;
//        this.point = point;
    }


// 1. doInBackground 실행전에 설정부분 : 초기화 설정
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    // 반드시 선언해야 할것들 : 무조건 해야함 복,붙
    HttpClient httpClient;       // 클라이언트 객체
    HttpPost httpPost;           // 클라이언트에 붙일 본문
    HttpResponse httpResponse;   // 서버에서의 응답을 받는 부분
    HttpEntity httpEntity;       // 응답내용

    // 2. 실질적으로 작업을 하는곳
    @Override                   // 첫번째 파라메터
    protected Void doInBackground(Void... voids) {

        try {
            // 무조건 해야함 : 복,붙
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));
            // 여기가 우리가 수정해야 하는 부분 : 서버로 보내는 데이터
            // builder에 문자열 및 파일 첨부하는곳
            builder.addTextBody("name", name, ContentType.create("Multipart/related", "UTF-8"));
           // builder.addTextBody("point", point, ContentType.create("Multipart/related", "UTF-8"));

            // 전송
            // 전송 Url : 우리가 수정해야 하는 부분
            String postURL = ipConfig + "/laundry/psQRCode";

            // 그대로 복,붙
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost); // 보내고 응답받는 부분
            httpEntity = httpResponse.getEntity();  // 응답내용을 저장
            inputStream = httpEntity.getContent();  // 응답내용을 inputStream에 넣음

            // 응답처리 : DTO 형태 :
            //loginDTO = readMessage(inputStream);
            //Log.d(TAG, "확인맨: PS_QRCodeSelect : doInBackground: " +loginDTO.getName());
            //Log.d(TAG, "확인맨: PS_QRCodeSelect : doInBackground: " +loginDTO.getPoint());

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


// 2-1. 작업중에 데이터를 받는곳
    @Override                       // 두번째 파라메터
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    // 3. doInBackground 실행(작업)후에 오는부분
    @Override                   // 세번째 파라메터
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }

    // 하나의 DTO형태로 데이터를 받을때 파싱하는 부분
    private MemberDTO readMessage(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader
                (new InputStreamReader(inputStream, "utf-8"));
        Log.d(TAG, "확인맨: PS_QRCodeSelect : readMessage : 11");
        Log.d(TAG, "확인맨: PS_QRCodeSelect : readMessage : 11");

        String point = "";
        Log.d(TAG, "readMessage: " + name );
        reader.beginObject();
        while (reader.hasNext()){
            String readStr = reader.nextName();
            if(readStr.equals("name")){
                name = reader.nextString();
                //name="강민재";
                Log.d(TAG, "readMessage: " + name + ", " +point);
            }else if(readStr.equals("point")) {
                point = reader.nextString();
            }else {
                reader.skipValue();
            }
        } // while
        reader.endObject();
        Log.d(TAG, "readMessage: " + name + ", " +point);

        return new MemberDTO(name, point);
    }

}
