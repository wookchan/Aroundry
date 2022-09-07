package com.example.project.ATask;


import static com.example.project.Common.CommonMethod.ipConfig;
import static com.example.project.Common.CommonMethod.loginDTO;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.project.DTO.MemberDTO;
import com.example.project.DTO.UserDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
import java.util.HashMap;

public class JoinInsert extends AsyncTask<Void, Void, String> {
    private static final String TAG = "main:JoinInsert";

    // 우리는 무조건 생성자를 만들어서 데이터를 넘겨받는다
    String name, email, phonenumber, id, profileimage;
    String state = "";

    public JoinInsert(String name, String email, String phonenumber,
                      String id, String profileimage) {
        this.name = name;
        this.email = email;
        this.name = name;
        this.phonenumber = phonenumber;
        this.id = id;
        this.profileimage = profileimage;
    }

    /*// 1. doInBackground 실행전에 설정부분 : 초기화 설정
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }*/

    // 반드시 선언해야 할것들 : 무조건 해야함 복,붙
    HttpClient httpClient;       // 클라이언트 객체
    HttpPost httpPost;           // 클라이언트에 붙일 본문
    HttpResponse httpResponse;   // 서버에서의 응답을 받는 부분
    HttpEntity httpEntity;       // 응답내용

    // 2. 실질적으로 작업을 하는곳
    @Override                   // 첫번째 파라메터
    protected String doInBackground(Void... voids) {

        try {
            // 무조건 해야함 : 복,붙
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            // 여기가 우리가 수정해야 하는 부분 : 서버로 보내는 데이터
            // builder에 문자열 및 파일 첨부하는곳
            builder.addTextBody("name", name, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("email", email, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("phone", phonenumber, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("id", id, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("profile", profileimage, ContentType.create("Multipart/related", "UTF-8"));

            Log.d(TAG, "하이염: "+ name);

//            if(profileimage.equals("NoImage")){
//                builder.addTextBody("imgpath", profileimage, ContentType.create("Multipart/related", "UTF-8"));
//            }else{
//                // 파일을 전송하기 : 추가로 해주기 : 진짜파일
//                builder.addPart("imgpath", new FileBody(new File(profileimage)));
//            }

            // 전송
            // 전송 Url : 우리가 수정해야 하는 부분
            String postURL = ipConfig + "/laundry/kangJo";

            Log.d(TAG, "ㅠㅠㅠㅠㅠㅠ: " + phonenumber);

            // 그대로 복,붙
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost); // 보내고 응답받는 부분
            httpEntity = httpResponse.getEntity();  // 응답내용을 저장
            inputStream = httpEntity.getContent();  // 응답내용을 inputStream에 넣음

            /*
            Log.d(TAG, "ㅠㅠ: ");
            // 응답처리 : 문자열(String) 형태
            BufferedReader bufferedReader = new
                    BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }
            state = stringBuilder.toString();

            inputStream.close();
            */

            //loginDTO = fromUsertoMember(readMessage(inputStream));


            HashMap<String,String> map = new Gson().fromJson( new InputStreamReader(inputStream), new TypeToken<HashMap<String,String>>(){}.getType() );
            UserDTO user = new Gson().fromJson( map.get("user"),   UserDTO.class );
            loginDTO = fromUsertoMember( user );
            HashMap<String,String> bookmark = new Gson().fromJson( map.get("bookmark"),   new TypeToken<HashMap<String,String>>(){}.getType() );

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

        return state;
    }

    private MemberDTO fromUsertoMember( UserDTO user ){
        return new MemberDTO(user.getUserid(), user.getName(), user.getEmail(), user.getPhone(), user.getProfile(), user.getPoint());
    }


    private UserDTO readMessage(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader
                (new InputStreamReader(inputStream, "utf-8"));

        String id="", name="", phone="", email="", profile="", point="";
        reader.beginObject();
        while (reader.hasNext()){
            String readStr = reader.nextName();
            if(readStr.equals("id")){
                id = reader.nextString();
            }else if(readStr.equals("name")){
                name = reader.nextString();
            }else if(readStr.equals("phone")){
                phone = reader.nextString();
            }else if(readStr.equals("email")){
                email = reader.nextString();
            }else if(readStr.equals("profile")){
                profile = /*ipConfig + "/app/resources/" +*/ reader.nextString();
                Log.d(TAG, "profile: " + profile);
            }else if(readStr.equals("point")){
                point = reader.nextString();
            }else {
                reader.skipValue();
            }
        } // while
        reader.endObject();
        Log.d(TAG, "readMessage: " + id + ", " +name);

        return new UserDTO(id, name, email, phone, profile, point);
    }



    /*// 2-1. 작업중에 데이터를 받는곳
    @Override                       // 두번째 파라메터
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }*/

    // 3. doInBackground 실행(작업)후에 오는부분
    @Override                   // 세번째 파라메터
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Log.d(TAG, "result: " + result);
    }
}
