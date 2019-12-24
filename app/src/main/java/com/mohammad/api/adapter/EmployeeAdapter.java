package com.mohammad.api.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mohammad.api.R;
import com.mohammad.api.Update_Employee_Activity;
import com.mohammad.api.api.EmployeeAPI;
import com.mohammad.api.model.Employee;
import com.mohammad.api.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeVewHolder>{

    private Context context;
    private List<Employee> employeeList;

    public EmployeeAdapter(Context context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeVewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_detail,parent,false);
        return new EmployeeVewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeVewHolder holder, final int position) {

        final Employee employee=employeeList.get(position);
        holder.empId.setText(String.valueOf(employee.getId()));
        holder.empId.setAlpha(0.0f);
        holder.tvName.setText(employee.getEmployee_name());
        holder.tvAge.setText(String.valueOf(employee.getEmployee_age()));
        holder.tvSalary.setText(String.valueOf(employee.getEmployee_salary()));
        holder.imgProfile.setImageResource(R.drawable.image1);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(URL.base_url).addConverterFactory(GsonConverterFactory.create()).build();
                EmployeeAPI employeeAPI=retrofit.create(EmployeeAPI.class);
                Call<Void> voidCall=employeeAPI.deleteEmployee(employee.getId());
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,employeeList.size());
                voidCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(context, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Update_Employee_Activity.class);
                intent.putExtra("id",employee.getId());
                intent.putExtra("name",employee.getEmployee_name());
                intent.putExtra("age",employee.getEmployee_age());
                intent.putExtra("salary",employee.getEmployee_salary());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class EmployeeVewHolder extends RecyclerView.ViewHolder {

        ImageView imgProfile;
        TextView empId,tvName,tvAge,tvSalary;
        ImageButton btnDelete,btnEdit;

        public EmployeeVewHolder(@NonNull View itemView) {
            super(itemView);
            this.empId=itemView.findViewById(R.id.empId);
            this.imgProfile=itemView.findViewById(R.id.imgProfile);
            this.tvName=itemView.findViewById(R.id.tvName);
            this.tvAge=itemView.findViewById(R.id.tvAge);
            this.tvSalary=itemView.findViewById(R.id.tvSalary);
            this.btnDelete=itemView.findViewById(R.id.btnDelete);
            this.btnEdit=itemView.findViewById(R.id.btnEdit);
        }
    }

}
