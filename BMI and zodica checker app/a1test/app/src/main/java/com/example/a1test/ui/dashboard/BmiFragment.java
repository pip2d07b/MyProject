package com.example.a1test.ui.dashboard;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.a1test.R;
import com.example.a1test.databinding.FragmentDashboardBinding;

public class BmiFragment extends Fragment {
    double height,weight,cal_bmi;
    TextView tv_status;
    ImageView bmi_table;



    private FragmentDashboardBinding binding;



    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Weight = "weightKey";
    public static final String Height = "heightKey";
    public static final String Gender = "genderKey";
    public static final String Date = "dateKey";

    TextView tv_name,tv_height,tv_weight,tv_date,tv_gender,tv_result;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tv_name = root.findViewById(R.id.tv_name);
        tv_height = root.findViewById(R.id.tv_height);
        tv_weight = root.findViewById(R.id.tv_weight);
        tv_date = root.findViewById(R.id.tv_date);
        tv_gender = root.findViewById(R.id.tv_gender);
        tv_result = root.findViewById(R.id.tv_result);
        tv_status = root.findViewById(R.id.tv_status);
        bmi_table= root.findViewById(R.id.bmi_table);


        sharedpreferences = getContext().getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Name)) {
            tv_name.setText(sharedpreferences.getString(Name, ""));
        }

        if (sharedpreferences.contains(Height)) {
            tv_height.setText(sharedpreferences.getString(Height, ""));
        }
        if (sharedpreferences.contains(Weight)) {
            tv_weight.setText(sharedpreferences.getString(Weight, ""));
        }
        if (sharedpreferences.contains(Date)) {
            tv_date.setText(sharedpreferences.getString(Date, ""));
        }
        if (sharedpreferences.contains(Gender)) {
            tv_gender.setText(sharedpreferences.getString(Gender, ""));
        }

        height=Double.parseDouble(tv_height.getText().toString());
        weight=Double.parseDouble(tv_weight.getText().toString());
        cal_bmi = calculateBMI(height,weight);


        tv_result.setText(String.valueOf(cal_bmi) + "("+ status_inf(cal_bmi)+")");

        status_advice(status_inf(cal_bmi));



        Button show_hide_btn = root.findViewById(R.id.show_hide_btn);
        show_hide_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bmi_table.getVisibility()== view.VISIBLE){
                    bmi_table.setVisibility(View.GONE);
                    show_hide_btn.setText("SHOW BMI TABLE");
                }
                else{
                    bmi_table.setVisibility(View.VISIBLE);
                    show_hide_btn.setText("HIDE BMI TABLE");
                }
            }
        });



        return root;
    }



    public double calculateBMI(double h,double w){
        double BMI;
        BMI=w/((h/100)*(h/100));
        return BMI;
    }

    public String status_inf(double BMI){
        String status;
        if(BMI<18.5){
            status="Underweight";
        }
        else if(BMI<25){
            status="Normal range";
        }
        else if(BMI<30){
            status="Overweight";
        }
        else if(BMI<35){
            status="Obese class 1";
        }
        else if(BMI<40){
            status="Obese class 2";
        }
        else {
            status="Obese class 3";
        }

        return status;
    }


    public void status_advice(String i){

        if(i=="Underweight"){

            tv_status.setText( Html.fromHtml(getResources().getString(R.string.status_underweight), FROM_HTML_MODE_LEGACY)  );
        }

        else if(i=="Normal range"){

            tv_status.setText( Html.fromHtml(getResources().getString(R.string.status_normalRange), FROM_HTML_MODE_LEGACY)  );
        }

        else if(i=="Overweight"){

            tv_status.setText( Html.fromHtml(getResources().getString(R.string.status_overweight), FROM_HTML_MODE_LEGACY)  );
        }

        else if(i=="Obese class 1"){

            tv_status.setText( Html.fromHtml(getResources().getString(R.string.status_o1), FROM_HTML_MODE_LEGACY)  );
        }

        else if(i=="Obese class 2"){

            tv_status.setText( Html.fromHtml(getResources().getString(R.string.status_o2), FROM_HTML_MODE_LEGACY)  );
        }

        else if(i=="Obese class 3"){

            tv_status.setText( Html.fromHtml(getResources().getString(R.string.status_o3), FROM_HTML_MODE_LEGACY)  );
        }


    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}