package com.mohammad.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;


public class DetailActivity extends AppCompatActivity {

    TextView tvName,tvAge,tvSalary,empId;
    ImageButton btnDelete, btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.tvName = findViewById(R.id.tvName);
        this.tvAge = findViewById(R.id.tvAge);
        this.tvSalary = findViewById(R.id.tvSalary);
        this.btnDelete = findViewById(R.id.btnDelete);
        this.btnEdit = findViewById(R.id.btnEdit);

        this.empId = findViewById(R.id.empId);
    }
}
