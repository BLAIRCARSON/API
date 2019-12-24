package com.mohammad.api.api;

import com.mohammad.api.model.Employee;
import com.mohammad.api.model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeAPI {

    @GET("employees")
    Call<List<Employee>> getAllEmployees();

    @GET("employee/{empId}")
    Call<Employee> getEmployeeById(@Path("empId") int id);

    @POST("create")
    Call<Void> registerEmployee(@Body EmployeeCUD emp);

    @PUT("update/{empId}")
    Call<Void> updateEmployee(@Path("empId")int empId,@Body EmployeeCUD emp);

    @DELETE("delete/{empId}")
    Call<Void> deleteEmployee(@Path("empId") int empId);

}
