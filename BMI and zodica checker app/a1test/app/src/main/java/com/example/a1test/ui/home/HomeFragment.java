package com.example.a1test.ui.home;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.a1test.R;
import com.example.a1test.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment implements View.OnClickListener{

    Button btn_save, btn_retrieve, btn_clear;
    EditText et_name, et_height, et_weight, et_gender;
    FloatingActionButton add_date_btn;
    TextView tv_date;
    int zodiacDay,zodiacMonth;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Weight = "weightKey";
    public static final String Height = "heightKey";
    public static final String Gender = "genderKey";
    public static final String Date = "dateKey";
    public static final String Month = "monthKey";
    public static final String Day = "dayKey";




    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btn_save = root.findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        btn_clear = root.findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(this);
        btn_retrieve = root.findViewById(R.id.btn_retrieve);
        btn_retrieve.setOnClickListener(this);

        et_name = root.findViewById(R.id.et_name);
        et_height = root.findViewById(R.id.et_height);
        et_weight = root.findViewById(R.id.et_weight);
        et_gender = root.findViewById(R.id.et_gender);

        add_date_btn = root.findViewById(R.id.add_date_btn);
        add_date_btn.setOnClickListener(this);
        tv_date = root.findViewById(R.id.tv_date);


        sharedpreferences = getContext().getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        // File creation mode: the default mode, where the created file
        // can only be accessed by the calling application
        // (or all applications sharing the same user ID).

        return root;
    }

    @Override
    public void onClick(View v) {
        // now assign the system
        // service to InputMethodManager
        InputMethodManager manager
                = (InputMethodManager)
                getActivity().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        manager
                .hideSoftInputFromWindow(
                        v.getWindowToken(), 0);
        switch (v.getId()) {
            case R.id.btn_clear:
                et_name.setText("");
                et_weight.setText("");
                et_height.setText("");
                et_gender.setText("");
                tv_date.setText("");
                break;
            case R.id.btn_save:
                // use of editor to write key value pair into xml file
                SharedPreferences.Editor editor = sharedpreferences.edit();
                // save values to the preferences
                editor.putString(Name, et_name.getText().toString());
                editor.putString(Weight, et_weight.getText().toString());
                editor.putString(Height, et_height.getText().toString());
                editor.putString(Gender, et_gender.getText().toString());
                editor.putString(Date, tv_date.getText().toString());
                editor.putInt(Month, zodiacMonth);
                editor.putInt(Day, zodiacDay);

                // commit the changes made to the file
                editor.commit();
                // inform user about SAVE completed
                Toast.makeText(getContext().getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_retrieve:
                // check if preferences contain a preference
                // if found, retrieve values from preferences
                if (sharedpreferences.contains(Name)) {
                    et_name.setText(sharedpreferences.getString(Name, ""));
                }
                if (sharedpreferences.contains(Weight)) {
                    et_weight.setText(sharedpreferences.getString(Weight, ""));
                }
                if (sharedpreferences.contains(Height)) {
                    et_height.setText(sharedpreferences.getString(Height, ""));
                }
                if (sharedpreferences.contains(Gender)) {
                    et_gender.setText(sharedpreferences.getString(Gender, ""));
                }
                if (sharedpreferences.contains(Date)) {
                    tv_date.setText(sharedpreferences.getString(Date, ""));
                }

                // inform user about RETRIEVE completed
                Toast.makeText(getContext().getApplicationContext(),"Retrieved",Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_date_btn:
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dateDialog = new DatePickerDialog(v.getContext(), datePickerListener, mYear, mMonth, mDay);
                dateDialog.getDatePicker().setMaxDate(new Date().getTime());
                dateDialog.show();

                break;
        }


    }


    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);
            String format = new SimpleDateFormat("dd MMM YYYY").format(c.getTime());
            tv_date.setText(format);
            zodiacDay= day;
            zodiacMonth= month;
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}