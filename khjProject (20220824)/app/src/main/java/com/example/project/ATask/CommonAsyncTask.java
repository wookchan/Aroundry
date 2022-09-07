package com.example.project.ATask;

import static com.example.project.Common.CommonMethod.ipConfig;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CommonAsyncTask extends AsyncTask<String, String, String> {
    private MultipartEntityBuilder entityBuilder;
    private HttpClient client;
    private HttpPost post;
    private String app = ipConfig + "/laundry/";
    private String url;
    private InputStreamReader response;
    private HashMap<String, Object> request;
//    Params : AsyncTask 실행에 필요한 파라미터.
//    Progress : 현재 작업 진행 정보를 나타내는 상태 값.
//    Result : 작업의 실행이 완료된 후의 최종 결과.
    /*
    실행(execute) : 비동기(Asynchronous) 작업 준비 및 시작.
    백그라운드 작업(doInBackground) : 백그라운드 스레드에서 비동기(Asynchronous) 작업 실행.
    진행 상황 업데이트(onProgressUpdate) : 백그라운드 스레드 진행 상황을 메인스레드로 전달.
    비동기 실행 완료 후 처리(onPostExecute) : 백그라운드 스레드 완료 후 메인스레드에 완료 상태 전달.
    execute -> doInBackground -> onProgressUpdate -> onPostExecute
    안드로이드 버전 3.0 이상부터는 인터넷 연결은 스레드나 핸들러에서 처리하도록 정책이 바뀌었습니다.
    그래서 UI 쓰레드에서 인터넷 연결을 시도하면(HttpURLConnection과 같은 것으로) 실행 타임에서 에러가 발생합니다.
    에러 발생 원인은 Network 관련 operation 을 main thread 에서 했기 때문, 추가로 strict mode 가 설정되었기 때문입니다.
    */
    public CommonAsyncTask(String url){
        this.url = url;
    }

    public CommonAsyncTask(String url, HashMap<String, Object> request){
        this.url = url;
        this.request = request;
    }

    public InputStreamReader getResponse() {
        return response;
    }

    public void setResponse(InputStreamReader response) {
        this.response = response;
    }

    @Override
    protected String doInBackground(String... strings) {
        client = AndroidHttpClient.newInstance("Android");

        entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
try {
    post = new HttpPost(app + url);
}catch(Exception e){
    Log.d("post> ",e.getMessage());
}
        if (request != null) {
            for (Map.Entry<String, Object> entry : request.entrySet()) {
                entityBuilder.addTextBody(entry.getKey(), new Gson().toJson(entry.getValue())
                        , ContentType.create("Multipart/related", "utf-8"));
            }
        }

        post.setEntity(entityBuilder.build());
        try {
            InputStream in = client.execute(post).getEntity().getContent();
            if (in != null) {
                response = new InputStreamReader(in);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }

}