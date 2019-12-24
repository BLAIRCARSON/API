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

public class RegisterEmployeeActivity extends AppCompatActivity {

    Button btnRegister;
    EditText etName,etAge,etSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_employee);

        this.etName=findViewById(R.id.etName);
        this.etAge=findViewById(R.id.etAge);
        this.etSalary=findViewById(R.id.etSalary);
        this.btnRegister=findViewById(R.id.btnRegister);

        this.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
    }

    private void Register(){
            String name = etName.getText().toString();
            String age = etAge.getText().toString();
            String salary = etSalary.getText().toString();
            if(name.equals("")){
                etName.setError("Enter name");
            }else if(age.equals("")){
                etAge.setError("Enter age");
            }else if(salary.equals("")){
                etSalary.setError("Enter salary");
            }else {
                int empAge=Integer.parseInt(age);
                float empSalary=Float.parseFloat(salary);
                EmployeeCUD employeeCUD = new EmployeeCUD(name, empAge, empSalary);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL.base_url).addConverterFactory(GsonConverterFactory.create()).build();

                EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
                Call<Void> voidCall = employeeAPI.registerEmployee(employeeCUD);

                voidCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        etName.setText("");
                        etAge.setText("");
                        etSalary.setText("");
                        Toast.makeText(RegisterEmployeeActivity.this, "Employee Register Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(RegisterEmployeeActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

    }
}
