package com.example.a1test.ui.notifications;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.a1test.R;
import com.example.a1test.databinding.FragmentNotificationsBinding;

import org.w3c.dom.Text;

public class ZodiacFragment extends Fragment {


    private FragmentNotificationsBinding binding;

    TextView tv_name,tv_date,tv_gender ,tv_zodiac;

    int getMonth,getDay;
    String sign;
    ImageView zodiac_img,good1,good2,good3,bad1,bad2,bad3;
    TextView tv_inf,tv_p;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";;
    public static final String Gender = "genderKey";
    public static final String Date = "dateKey";
    public static final String Month = "monthKey";
    public static final String Day = "dayKey";




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tv_name = root.findViewById(R.id.tv_name);
        tv_date = root.findViewById(R.id.tv_date);
        tv_gender = root.findViewById(R.id.tv_gender);
        tv_zodiac= root.findViewById(R.id.tv_zodiac);
        zodiac_img=root.findViewById(R.id.zodiac_img);
        tv_inf=root.findViewById(R.id.tv_inf);
        tv_p=root.findViewById(R.id.tv_p);
        good1=root.findViewById(R.id.good1);
        good2=root.findViewById(R.id.good2);
        good3=root.findViewById(R.id.good3);
        bad1=root.findViewById(R.id.bad1);
        bad2=root.findViewById(R.id.bad2);
        bad3=root.findViewById(R.id.bad3);

        sharedpreferences = getContext().getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Name)) {
            tv_name.setText(sharedpreferences.getString(Name, ""));
        }

        if (sharedpreferences.contains(Date)) {
            tv_date.setText(sharedpreferences.getString(Date, ""));
        }
        if (sharedpreferences.contains(Gender)) {
            tv_gender.setText(sharedpreferences.getString(Gender, ""));
        }



        getMonth=sharedpreferences.getInt(Month, 0);
        getDay=sharedpreferences.getInt(Day, 0);

        set_zodiac();
        tv_zodiac.setText(sign);


        getimage(sign);

        return root;
    }


    public void set_zodiac(){
        if (getMonth == 0) {
            if (getDay < 20)
                sign = "Capricorn";
            else
                sign = "Aquarius";
        }
        else if (getMonth == 1) {
            if (getDay < 19)
                sign = "Aquarius";
            else
                sign = "Pisces";
        }
        else if(getMonth == 2) {
            if (getDay < 21)
                sign = "Pisces";
            else
                sign = "Aries";
        }
        else if (getMonth == 3) {
            if (getDay < 20)
                sign = "Aries";
            else
                sign = "Taurus";
        }
        else if (getMonth == 4) {
            if (getDay < 21)
                sign = "Taurus";
            else
                sign = "Gemini";
        }
        else if( getMonth == 5) {
            if (getDay < 21)
                sign = "Gemini";
            else
                sign = "Cancer";
        }
        else if (getMonth == 6) {
            if (getDay < 23)
                sign = "Cancer";
            else
                sign = "Leo";
        }
        else if( getMonth == 7) {
            if (getDay < 23)
                sign = "Leo";
            else
                sign = "Virgo";
        }
        else if (getMonth == 8) {
            if (getDay < 23)
                sign = "Virgo";
            else
                sign = "Libra";
        }
        else if (getMonth == 9) {
            if (getDay < 23)
                sign = "Libra";
            else
                sign = "Scorpio";
        }
        else if (getMonth == 10) {
            if (getDay < 22)
                sign = "Scorpio";
            else
                sign = "Sagittarius";
        }
        else if (getMonth == 11) {
            if (getDay < 22)
                sign = "Sagittarius";
            else
                sign ="Capricorn";
        }

    }

    public void getimage (String zodiac){


        if(zodiac=="Aries"){
            zodiac_img.setImageResource(R.drawable.aries);
            tv_inf.setText( getResources().getString(R.string.aries_inf));
            tv_p.setText( getResources().getString(R.string.aries_p));
            zodiac_img.setImageResource(R.drawable.saggittarius);

            good1.setImageResource(R.drawable.saggittarius);
            good2.setImageResource(R.drawable.libra);
            good3.setImageResource(R.drawable.gemini);

            bad1.setImageResource(R.drawable.scorpio);
            bad2.setImageResource(R.drawable.cancer);
            bad3.setImageResource(R.drawable.virgo);
        }
        else if(zodiac=="Taurus"){
            zodiac_img.setImageResource(R.drawable.taurus);
            tv_inf.setText( getResources().getString(R.string.taurus_inf));
            tv_p.setText( getResources().getString(R.string.taurus_p));

            good1.setImageResource(R.drawable.virgo);
            good2.setImageResource(R.drawable.cancer);
            good3.setImageResource(R.drawable.capricorn);

            bad1.setImageResource(R.drawable.leo);
            bad2.setImageResource(R.drawable.aquarius);
            bad3.setImageResource(R.drawable.saggittarius);
        }
        else if(zodiac=="Gemini"){
            zodiac_img.setImageResource(R.drawable.gemini);
            tv_inf.setText( getResources().getString(R.string.gemini_inf));
            tv_p.setText( getResources().getString(R.string.gemini_p));

            good1.setImageResource(R.drawable.saggittarius);
            good2.setImageResource(R.drawable.aquarius);
            good3.setImageResource(R.drawable.libra);

            bad1.setImageResource(R.drawable.capricorn);
            bad2.setImageResource(R.drawable.taurus);
            bad3.setImageResource(R.drawable.scorpio);
        }
        else if(zodiac=="Cancer"){
            zodiac_img.setImageResource(R.drawable.cancer);
            tv_inf.setText( getResources().getString(R.string.cancer_inf));
            tv_p.setText( getResources().getString(R.string.cancer_p));

            good1.setImageResource(R.drawable.scorpio);
            good2.setImageResource(R.drawable.pisces);
            good3.setImageResource(R.drawable.virgo);

            bad1.setImageResource(R.drawable.gemini);
            bad2.setImageResource(R.drawable.saggittarius);
            bad3.setImageResource(R.drawable.leo);
        }
        else if(zodiac=="Leo"){
            zodiac_img.setImageResource(R.drawable.leo);
            tv_inf.setText( getResources().getString(R.string.leo_inf));
            tv_p.setText( getResources().getString(R.string.leo_p));

            good1.setImageResource(R.drawable.saggittarius);
            good2.setImageResource(R.drawable.gemini);
            good3.setImageResource(R.drawable.aquarius);

            bad1.setImageResource(R.drawable.capricorn);
            bad2.setImageResource(R.drawable.taurus);
            bad3.setImageResource(R.drawable.virgo);
        }
        else if(zodiac=="Virgo"){
            zodiac_img.setImageResource(R.drawable.virgo);
            tv_inf.setText( getResources().getString(R.string.virgo_inf));
            tv_p.setText( getResources().getString(R.string.virgo_p));

            good1.setImageResource(R.drawable.taurus);
            good2.setImageResource(R.drawable.scorpio);
            good3.setImageResource(R.drawable.capricorn);

            bad1.setImageResource(R.drawable.saggittarius);
            bad2.setImageResource(R.drawable.gemini);
            bad3.setImageResource(R.drawable.aquarius);
        }
        else if(zodiac=="Libra"){
            zodiac_img.setImageResource(R.drawable.libra);
            tv_inf.setText( getResources().getString(R.string.libra_inf));
            tv_p.setText( getResources().getString(R.string.libra_p));

            good1.setImageResource(R.drawable.aries);
            good2.setImageResource(R.drawable.aquarius);
            good3.setImageResource(R.drawable.libra);

            bad1.setImageResource(R.drawable.virgo);
            bad2.setImageResource(R.drawable.capricorn);
            bad3.setImageResource(R.drawable.pisces);
        }
        else if(zodiac=="Scorpio"){
            zodiac_img.setImageResource(R.drawable.scorpio);
            tv_inf.setText( getResources().getString(R.string.scorpio_p));
            tv_p.setText( getResources().getString(R.string.scorpio_p));

            good1.setImageResource(R.drawable.cancer);
            good2.setImageResource(R.drawable.virgo);
            good3.setImageResource(R.drawable.pisces);

            bad1.setImageResource(R.drawable.aquarius);
            bad2.setImageResource(R.drawable.leo);
            bad3.setImageResource(R.drawable.aries);
        }
        else if(zodiac=="Sagittarius"){
            zodiac_img.setImageResource(R.drawable.saggittarius);
            tv_inf.setText( getResources().getString(R.string.sagittarius_inf));
            tv_p.setText( getResources().getString(R.string.sagittarius_p));

            good1.setImageResource(R.drawable.gemini);
            good2.setImageResource(R.drawable.aries);
            good3.setImageResource(R.drawable.saggittarius);

            bad1.setImageResource(R.drawable.capricorn);
            bad2.setImageResource(R.drawable.taurus);
            bad3.setImageResource(R.drawable.pisces);
        }
        else if(zodiac=="Capricorn"){
            zodiac_img.setImageResource(R.drawable.capricorn);
            tv_inf.setText( getResources().getString(R.string.capricornus_inf));
            tv_p.setText( getResources().getString(R.string.capricornus_p));

            good1.setImageResource(R.drawable.virgo);
            good2.setImageResource(R.drawable.capricorn);
            good3.setImageResource(R.drawable.white_background);

            bad1.setImageResource(R.drawable.gemini);
            bad2.setImageResource(R.drawable.saggittarius);
            bad3.setImageResource(R.drawable.leo);
        }
        else if(zodiac=="Aquarius"){
            zodiac_img.setImageResource(R.drawable.aquarius);
            tv_inf.setText( getResources().getString(R.string.aquarius_inf));
            tv_p.setText( getResources().getString(R.string.aquarius_p));

            good1.setImageResource(R.drawable.gemini);
            good2.setImageResource(R.drawable.aquarius);
            good3.setImageResource(R.drawable.libra);

            bad1.setImageResource(R.drawable.scorpio);
            bad2.setImageResource(R.drawable.cancer);
            bad3.setImageResource(R.drawable.taurus);
        }
        else if(zodiac=="Pisces"){
            zodiac_img.setImageResource(R.drawable.pisces);
            tv_inf.setText( getResources().getString(R.string.pisces_inf));
            tv_p.setText( getResources().getString(R.string.pisces_p));

            good1.setImageResource(R.drawable.cancer);
            good2.setImageResource(R.drawable.capricorn);
            good3.setImageResource(R.drawable.scorpio);

            bad1.setImageResource(R.drawable.gemini);
            bad2.setImageResource(R.drawable.saggittarius);
            bad3.setImageResource(R.drawable.leo);
        }


    }






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}