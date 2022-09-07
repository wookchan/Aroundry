package com.example.project.ATask;

import static com.example.project.Common.CommonMethod.ipConfig;
import static com.example.project.Common.CommonMethod.storeDTOS;
import static com.example.project.MainActivity.myLoc;

import android.location.Location;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.project.Adapter.PS_SearchAdapter;
import com.example.project.DTO.StoreDTO;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PS_SearchSelect3 extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "main:PS_SearchSelect";

    ArrayList<StoreDTO> dtos= new ArrayList<>();
    PS_SearchAdapter adapter;
    String userid;

    // 우리는 무조건 생성자를 만들어서 데이터를 넘겨받는다
    public PS_SearchSelect3(ArrayList<StoreDTO> dtos, PS_SearchAdapter adapter, String userid) {
        this.dtos = dtos;
        this.adapter = adapter;
        this.userid = userid;
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

            builder.addTextBody("userid", userid, ContentType.create("Multipart/related", "UTF-8"));
            // 여기가 우리가 수정해야 하는 부분 : 서버로 보내는 데이터
            // builder에 문자열 및 파일 첨부하는곳

            // 전송
            // 전송 Url : 우리가 수정해야 하는 부분
            String postURL = ipConfig + "/laundry/anSearch3";

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

            // 거리를 이용한 정렬
            Collections.sort(dtos, new StoreDTOComparator());
            for (StoreDTO dto: dtos) {
                Log.d(TAG, "dto distance : " + dto.getDistance());
            }


        }catch (Exception e){
            e.getMessage();
        }finally {
            storeDTOS = dtos;
            reader.close();
        }

    }

    // 하나의 DTO형태로 데이터를 받을때 파싱하는 부분
    private StoreDTO readMessage(JsonReader reader) throws IOException {
        String  storeid="", location="", imageurl="", address="", operating="", f_cctv="", f_game="",
                f_toilet="", f_concent="", f_wifi="", f_coin="", ownerid="", latitude="", longitude="", rest_cnt="";
        double distance = 0.f;
        reader.beginObject();
        while (reader.hasNext()){
            String readStr = reader.nextName();
            if(readStr.equals("storeid")){
                storeid = reader.nextString();
            }else if(readStr.equals("location")){
                location = reader.nextString();
            }else if(readStr.equals("imageurl")){
                imageurl = reader.nextString();
            }else if(readStr.equals("address")){
                address = reader.nextString();
            }else if(readStr.equals("operating")){
                operating = reader.nextString();
            }else if(readStr.equals("f_cctv")){
                f_cctv = reader.nextString();
            }else if(readStr.equals("f_game")){
                f_game = reader.nextString();
            }else if(readStr.equals("f_toilet")){
                f_toilet = reader.nextString();
            }else if(readStr.equals("f_concent")){
                f_concent = reader.nextString();
            }else if(readStr.equals("f_wifi")){
                f_wifi = reader.nextString();
            }else if(readStr.equals("f_coin")){
                f_coin = reader.nextString();
            }else if(readStr.equals("ownerid")){
                ownerid = reader.nextString();
            }else if(readStr.equals("latitude")){
                latitude = reader.nextString();
            }else if(readStr.equals("longitude")){
                longitude = reader.nextString();
            }else if(readStr.equals("rest_cnt")) {
                rest_cnt = reader.nextString();
            }else {
                reader.skipValue();
            }

        } // while

        distance = getDistance(latitude, longitude);

        reader.endObject();
        Log.d(TAG, "readMessage: " + address + ", " +location + ", " +imageurl);

        return new StoreDTO(storeid, location, imageurl, address, operating, f_cctv, f_game,
                f_toilet, f_concent, f_wifi, f_coin, ownerid, latitude, longitude, rest_cnt,
                Double.parseDouble(String.format("%.3f",distance)));
    }

    // 위치와 관련된 메소드
    public double getDistance(String latitude, String longitude){
        double distance = 0;
        Location storeLoc = new Location("");

        storeLoc.setLatitude(Double.parseDouble(latitude));
        storeLoc.setLongitude(Double.parseDouble(longitude));

        distance = myLoc.distanceTo(storeLoc);

        return distance/1000;    // 올림 : ex) 4.4032km => ~5km 로 표현
    }

    class StoreDTOComparator implements Comparator<StoreDTO> {
        @Override
        public int compare(StoreDTO a,StoreDTO b){
            if(a.getDistance()>b.getDistance()) return 1;
            if(a.getDistance()<b.getDistance()) return -1;
            return 0;
        }
    }

}
