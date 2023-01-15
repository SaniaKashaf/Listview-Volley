package com.example.listviewvolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;



    String URL="https://reqres.in/api/users";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycler);

        ArrayList<UserModel> arrayList=new ArrayList();

        StringRequest request=new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject valueObject=jsonArray.getJSONObject(i);

                        int id=valueObject.getInt("id");
                        String firstname=valueObject.getString("first_name");
                        String lastname=valueObject.getString("lastname");
                        String email=valueObject.getString("email");
                        String image=valueObject.getString("avatar");


                        UserModel userModel=new UserModel(id,email,firstname,lastname,image);
                        arrayList.add(userModel);

                    }
                    UserAdapter adapter =new UserAdapter(arrayList,MainActivity.this);

                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);

                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(linearLayoutManager);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}