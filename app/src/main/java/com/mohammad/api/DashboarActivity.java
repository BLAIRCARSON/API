package com.mohammad.api;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboarActivity extends AppCompatActivity implements View.OnClickListener {


    Button btnRegisterEmployee,btnSearchEmployee,btnCUDEmployee,btnGetAllEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboar);

        btnRegisterEmployee=findViewById(R.id.btnRegisterEmployee);
        btnSearchEmployee=findViewById(R.id.btnSearchEmployee);
        btnGetAllEmployee=findViewById(R.id.btnGetAllEmployee);

        this.btnGetAllEmployee.setOnClickListener(this);
        this.btnRegisterEmployee.setOnClickListener(this);
        this.btnSearchEmployee.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGetAllEmployee:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.btnRegisterEmployee:
                startActivity(new Intent(this,RegisterEmployeeActivity.class));
                break;
            case R.id.btnSearchEmployee:
                startActivity(new Intent(this,SearchEmployeeActivity.class));
                break;
        }
    }
}
