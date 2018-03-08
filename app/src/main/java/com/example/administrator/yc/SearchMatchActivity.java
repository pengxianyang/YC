package com.example.administrator.yc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by victory on 2018/3/7.
 */

public class SearchMatchActivity extends AppCompatActivity {
    private  EditText Sport;//运动种类
    private EditText Distance;
    private  EditText Fromtime;//时间区间的起始
    private  EditText Totime;//时间区间的结束
    private  EditText Expense;//花费
    private  EditText Members;//人数
    private ArrayList<String> choice_sport;
    private ArrayList<String> choice_Expense;
    private ArrayList<String> choice_Members;
    private ArrayList<String> choice_distance;
    private TimePickerView timePickerView1;
    private TimePickerView timePickerView2;
    private OptionsPickerView<String>sportpicker;
    private OptionsPickerView<String>expensepicker;
    private OptionsPickerView<String>memberspicker;
    private OptionsPickerView<String>distancespicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select_match);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init_data();
        Init();
    }

    private void Init() {

        Sport= (EditText)findViewById(R.id.sport);
        Fromtime = (EditText)findViewById(R.id.time1);
        Totime = (EditText)findViewById(R.id.time2);
         Expense = (EditText)findViewById(R.id.expense);
         Members=(EditText)findViewById(R.id.members);
         Distance  = (EditText)findViewById(R.id.city);
        //时间选择器
         timePickerView1 = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
             @Override
             public void onTimeSelect(Date date, View v) {
                 Fromtime.setText(date.toString());
             }
         }).setType(new boolean[]{true, true, true, true, false, false}).build();

        timePickerView2 = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Totime.setText(date.toString());
            }
        }).setType(new boolean[]{true, true, true, true, false, false}).build();

        //运动选项
        sportpicker= new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                Sport.setText(choice_sport.get(options1).toString());
            }
        }).setSelectOptions(0).build();
        sportpicker.setPicker(choice_sport);

        //费用选项
        expensepicker= new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                Expense.setText(choice_Expense.get(options1));
            }
        }).setSelectOptions(0).build();
        expensepicker.setPicker(choice_Expense);

        //人数选项
        memberspicker= new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                Members.setText(choice_Members.get(options1));
            }
        }).setSelectOptions(0).build();
        memberspicker.setPicker(choice_Members);

        //距离选项
       distancespicker = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                Distance.setText(choice_distance.get(options1));
            }
        }).setSelectOptions(0).build();
        distancespicker.setPicker(choice_distance);

        Sport.setOnClickListener(getClickEvent());
        Distance.setOnClickListener(getClickEvent());
        Fromtime.setOnClickListener(getClickEvent());
        Expense.setOnClickListener(getClickEvent());
        Members.setOnClickListener(getClickEvent());
        Totime.setOnClickListener(getClickEvent());

    }

    private void init_data(){
        choice_sport = new ArrayList<>();
        choice_sport.add("<无>");
        choice_sport.add("足球");
        choice_sport.add("篮球");
        choice_sport.add("跑步");

        choice_Expense = new ArrayList<>();
        choice_Expense.add("<无>");
        choice_Expense.add("0~50元");
        choice_Expense.add("50~100元");
        choice_Expense.add("100~200元");
        choice_Expense.add("200~300元");
        choice_Expense.add("300元以上");

        choice_Members = new ArrayList<>();
        choice_Members.add("<无>");
        choice_Members.add("0~5人");
        choice_Members.add("6~10人");
        choice_Members.add("11~15人");
        choice_Members.add("15人以上");

        choice_distance = new ArrayList<>();
        choice_distance.add("<无>");
        choice_distance.add("1KM内");
        choice_distance.add("5KM内");
        choice_distance.add("10KM内");
        choice_distance.add("20KM内");
        choice_distance.add("50KM内");
        choice_distance.add("100KM内");
        choice_distance.add("200KM内");
    }

    private View.OnClickListener getClickEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==Sport){
                    sportpicker.show();
                }else if(v==Expense){
                    expensepicker.show();
                }else if(v==Members){
                    memberspicker.show();
                }else if (v==Fromtime){
                    timePickerView1.show();
                }else if (v==Totime){
                    timePickerView2.show();
                }else if (v==Distance){
                    distancespicker.show();
                }

            }
        };
    }
}
