package com.mohammad.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mohammad.api.api.EmployeeAPI;
import com.mohammad.api.model.EmployeeCUD;
import com.mohammad.api.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Update_Employee_Activity extends AppCompatActivity {

    EditText etEmployeeName,etEmployeeAge,etEmployeeSalary;
    Button btnUpdate;
    int id;
    String name;
    int age;
    float salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__employee_);
        this.etEmployeeName=findViewById(R.id.etEmployeeName);
        this.etEmployeeAge=findViewById(R.id.etEmployeeAge);
        this.etEmployeeSalary=findViewById(R.id.etEmployeeSalary);
        this.btnUpdate=findViewById(R.id.btnUpdate);

        Bundle bundle=getIntent().getExtras();

        if(bundle!=null){
            id=bundle.getInt("id");
            name=bundle.getString("name");
            age=bundle.getInt("age");
            salary=bundle.getFloat("salary");

            this.etEmployeeName.setText(name);
            this.etEmployeeAge.setText(String.valueOf(age));
            this.etEmployeeSalary.setText(String.valueOf(salary));
        }
        this.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    EmployeeCUD employeeCUD = new EmployeeCUD(name, age, salary);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(URL.base_url).addConverterFactory(GsonConverterFactory.create()).build();

                    EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
                    Call<Void> voidCall = employeeAPI.updateEmployee(id, employeeCUD);
                    voidCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(Update_Employee_Activity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(Update_Employee_Activity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        });

    }
}
