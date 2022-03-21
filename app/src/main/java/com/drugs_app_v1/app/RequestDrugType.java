package com.drugs_app_v1.app;
import okhttp3.*;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class RequestDrugType{

    private final OkHttpClient httpClient = new OkHttpClient();
    public boolean waiting = false;
    public String sendPOST(String age,String gender,String bp,String cholesterol,String na,String k) throws IOException {
        // form parameters
        RequestBody formBody = new FormBody.Builder()
                .add("age", age)
                .add("gender", gender)
                .add("bp", bp)
                .add("cholesterol", cholesterol)
                .add("na", na)
                .add("k", k)
                .build();
        Request request = new Request.Builder()
                .url("https://aayarismail.pythonanywhere.com/api/v1/get-drug-rx-decision-tree/")
                .addHeader("User-Agent", "OkHttp Bot")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            // Get response body
            return response.body().string();

        }

    }

}




