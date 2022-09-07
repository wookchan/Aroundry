package com.example.my_project.AsyncT;

import static com.example.my_project.DATA.CommonMethod.ipConfig;

import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.my_project.Adapter.HW_SetakAdapter;
import com.example.my_project.Adapter.Setak.HW_Listinformation;
import com.example.my_project.DATA.HW_SetakDTO;
import com.example.my_project.HW_Home;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class ListTask extends AsyncTask<Void,Void,Void> {
    ArrayList<HW_SetakDTO> dtos;
    HW_SetakAdapter adapter;
    String userid;
    private static final String TAG = "ListTask에서";

    public ListTask(ArrayList<HW_SetakDTO> dtos, HW_SetakAdapter adapter,String userid) {
        this.dtos = dtos;
        this.adapter= adapter;
        this.userid = userid;
    }

    // 반드시 선언해야 할것들 : 무조건 해야함 복,붙
    HttpClient httpClient;       // 클라이언트 객체
    HttpPost httpPost;           // 클라이언트에 붙일 본문
    HttpResponse httpResponse;   // 서버에서의 응답을 받는 부분
    HttpEntity httpEntity;       // 응답내용


   /* 실행 순서 상 호출받고 바로 실행됨(화면 설정등)
   @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }*/


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode((HttpMultipartMode.BROWSER_COMPATIBLE) );
            builder.setCharset(Charset.forName("utf-8"));
            
            //언젠간 지워야한다,로그인페이지에서 아이디를 받아서 쏴야하기 떄문임
            String id = userid;
            builder.addTextBody("id",id, ContentType.create("Multipart/related","UTF-8"));
           
            // ipconfig은 커먼 메소드에 + "스프링 주소 연결" <-
            // 현 ipconfig의 포트는 8989이다
            String postURL = ipConfig+"/laundry/Hwlist";

            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost); // 보내고 응답받는 부분
            httpEntity = httpResponse.getEntity();  // 응답내용을 저장
            inputStream = httpEntity.getContent();  // 응답내용을 inputStream에 넣음
            readJsonSteam(inputStream); // 데이터베이스로부터 올떈 json형태로 온다 그러니 이것을 읽어들일 메소드가 필요함
        }
        catch (Exception e) {
            e.getMessage();
        }
        finally {
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


    //세번쨰 Void
    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        adapter.notifyDataSetChanged();


    }
    // 직접 만든 변수 명 readJsonStream,들어올떄 반드시 utf-8로 들어오게해야함
    public void readJsonSteam(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream,"utf-8"));
        try {
            reader.beginArray();
            while(reader.hasNext()) {
                dtos.add(readMessage(reader)); //readmessage라는 메소드 안에 dto 형태로 캐스팅(파싱)해서 받아야함
            }
            reader.endArray();
        } //try
        catch (Exception e) {
            e.getMessage(); //에러
        }
        finally {
            reader.close(); // 닫기
        }
    } //readJsonSteam END

    private HW_SetakDTO readMessage(JsonReader reader) throws IOException {
        String titlelocation="" ,location="",imgpath="",conven="";
        int machine=0,storeid=0,f_cctv=0,f_game=0,f_toilet=0,f_concent=0,f_wifi=0,f_coin=0;
        reader.beginObject();
        // 값이 여러개라면
        while(reader.hasNext()) {
            String readStr = reader.nextName();

            if(readStr.equals("titlelocation")) {
                titlelocation = reader.nextString();
            }
            else if(readStr.equals("location")) {
                location = reader.nextString();
            }
            else if(readStr.equals("imgpath")) {
                imgpath= reader.nextString();
            }
//            else if(readStr.equals("conven")) {
//                conven = reader.nextString();
//            } 문자열로 받는 방식 이엇음
            else if(readStr.equals("machine")) {
                machine = reader.nextInt();
            }

            else if(readStr.equals("storeid")) {
                storeid = reader.nextInt();
            }
            else if(readStr.equals("f_cctv")) {
                f_cctv = reader.nextInt();
            }
            else if(readStr.equals("f_game")) {
                f_game = reader.nextInt();
            }
            else if(readStr.equals("f_toilet")) {
                f_toilet = reader.nextInt();
            }
            else if(readStr.equals("f_concent")) {
                f_concent = reader.nextInt();
            }
            else if(readStr.equals("f_wifi")) {
                f_wifi = reader.nextInt();
            }
            else if(readStr.equals("f_coin")) {
                f_coin = reader.nextInt();
            }

            else {
                reader.skipValue();
            }
        }
        reader.endObject();

        //dto의 양식대로 순서를 바꿔줘야한다
        Log.d(TAG, "HW: SETAKDTO " + titlelocation );
        return new HW_SetakDTO(titlelocation,location,imgpath,machine,storeid,f_cctv,f_game,f_toilet,f_concent,f_wifi,f_coin);
    }


     /*// UI가 업데이트 doInbackground ~~ onPost 사이
    @Override                       // 두번째 파라메터
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }*/
}


