package com.mohammad.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mohammad.api.api.EmployeeAPI;
import com.mohammad.api.model.Employee;
import com.mohammad.api.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchEmployeeActivity extends AppCompatActivity {

    EditText etEmpId;
    Button btnGetDetail;
    TextView tvEmpDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employee);

        this.etEmpId=findViewById(R.id.etEmpId);
        this.btnGetDetail=findViewById(R.id.btnGetDetail);
        this.tvEmpDetails=findViewById(R.id.tvEmpDetails);


        this.btnGetDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Validate()){
                    loadData();
                }
            }
        });

    }

    private void loadData(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI=retrofit.create(EmployeeAPI.class);
        Call <Employee> empDetails=employeeAPI.getEmployeeById(Integer.parseInt(etEmpId.getText().toString()));

        empDetails.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(SearchEmployeeActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                String empDetails="";
                empDetails+="Id : "+response.body().getId()+"\n";
                empDetails+="Name : "+response.body().getEmployee_name()+"\n";
                empDetails+="Age : "+response.body().getEmployee_age();
                empDetails+="Salary : "+response.body().getEmployee_salary()+"\n";
                tvEmpDetails.setText(empDetails);

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(SearchEmployeeActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean Validate(){
        String empId= etEmpId.getText().toString();
        if(!empId.equals("")){
            return true;
        }
        etEmpId.setError("Enter employee id");
        return false;
    }
}
