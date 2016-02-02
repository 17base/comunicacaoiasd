package br.ufc.quixada.dsdm.comunicacaoiasd.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import br.ufc.quixada.dsdm.comunicacaoiasd.R;
import br.ufc.quixada.dsdm.comunicacaoiasd.model.SearchResult;
import br.ufc.quixada.dsdm.comunicacaoiasd.utils.ConvertJsonObject;

/**
 * Created by jonas_000 on 18/12/2015.
 */
public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private RequestQueue requestQueue;
    private String URL = "https://www.googleapis.com/youtube/v3/search?key=AIzaSyBE8-6Mm2nXjDfNSJMF88EgN_vacB4thag&channelId=UC_OaSsAydgSIjUtjYn9qLog&part=snippet,id&order=date&maxResults=50";
    private SearchResult searchResult;
    private ConvertJsonObject convertJsonObject;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(SplashActivity.this);

        try {
            convertJsonObject = new ConvertJsonObject();
            retriveData();
        } catch (Exception e){
            Log.e(TAG, e.toString());
        }
    }

    public SearchResult retriveData() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Aguarde...");
        pDialog.setCancelable(false);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                hidePDialog();
                Log.d(TAG, response.toString());

                searchResult = convertJsonObject.convert(response);
                Log.d(TAG, searchResult.toString());

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra("RESULT", searchResult);
                startActivity(intent);
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
                hidePDialog();
            }
        });

        int timeout = 100000;
        RetryPolicy policy = new DefaultRetryPolicy(timeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);
        jsObjRequest.setTag(TAG);
        requestQueue.add(jsObjRequest);

        return searchResult;
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
