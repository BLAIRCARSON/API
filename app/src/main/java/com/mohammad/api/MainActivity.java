package com.mohammad.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mohammad.api.adapter.EmployeeAdapter;
import com.mohammad.api.api.EmployeeAPI;
import com.mohammad.api.model.Employee;
import com.mohammad.api.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        EmployeeAPI employeeAPI=retrofit.create(EmployeeAPI.class);
        Call<List<Employee>> empListCall=employeeAPI.getAllEmployees();

        empListCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Error"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Employee> empList=response.body();

                EmployeeAdapter employeeAdapter=new EmployeeAdapter(MainActivity.this,empList);
                recyclerView.setAdapter(employeeAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("errorMsg","onFailure"+t.getLocalizedMessage());
                Toast.makeText(MainActivity.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
