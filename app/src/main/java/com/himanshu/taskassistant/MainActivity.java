package com.himanshu.taskassistant;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.LinearGradient;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.xml.sax.DTDHandler;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton addButton;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public String DT, tempstr, tempstr1="";
    DatabaseHelper databaseHelper;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList arrayList;
        addButton = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        databaseHelper = new DatabaseHelper(this);

        try {
            arrayList = new ArrayList<>(databaseHelper.getNotes());
        }
        catch (NullPointerException e)
        {
            arrayList = new ArrayList<>();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DataAdapter adapter = new DataAdapter(getApplicationContext(), this, arrayList);
        recyclerView.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*AlertDialog.Builder taskCreator = new AlertDialog.Builder(MainActivity.this);
                View view = getLayoutInflater().inflate(R.layout.add_task_layout,null);
                taskCreator.setTitle("Add Task");
                taskCreator.setMessage("What do you want to do?");
                taskCreator.setView(view);
                ((Button)view.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int myear = calendar.get(Calendar.YEAR);
                        int Mmonth = calendar.get(Calendar.MONTH);
                        int mday = calendar.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                //Log.d("DATE " , (Integer.toString(day)+"-"+Integer.toString(month)+"-"+Integer.toString(year)));
                                int mmonth= month+1;
                                String fm=""+mmonth;
                                String fd=""+dayOfMonth;
                                if(mmonth<10){
                                    fm ="0"+mmonth;
                                }
                                if (dayOfMonth<10){
                                    fd="0"+dayOfMonth;
                                }
                                String date= ""+fd+"-"+fm+"-"+year;

                                Calendar c = Calendar.getInstance();
                                int mhour = c.get(Calendar.HOUR);
                                int mminute = c.get(Calendar.MINUTE);
                                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        Log.d("DATE",""+date);
                                        Log.d("TIME ", ""+hourOfDay+":"+minute);
                                        tempstr = ""+hourOfDay+":"+minute;
                                        if(tempstr.length()==4)
                                        {
                                            if(tempstr.charAt(1)==':')
                                            {
                                                tempstr1 = "0"+tempstr;
                                            }
                                            else
                                            {
                                                char temp = DT.charAt(3);
                                                tempstr1 = tempstr.substring(0, 4) + "0" + temp;
                                            }
                                        }
                                        else if (tempstr.length()==5){}
                                        else
                                        {
                                            char temp1 = tempstr.charAt(0);
                                            char temp2 = tempstr.charAt(2);
                                            tempstr1 = "0" + temp1 + ":0" + temp2;
                                        }
                                        DT = ""+date+" " + tempstr1;
                                        Log.d("FINALLLLLLLL", DT);
                                    }

                                }, mhour, mminute, DateFormat.is24HourFormat(MainActivity.this));
                                timePickerDialog.show();

                            }
                        }, myear, Mmonth, mday);
                        datePickerDialog.show();

                    }
                });

                taskCreator.setCancelable(true);
                taskCreator.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(((EditText)view.findViewById(R.id.editTextTitle)).getText().toString().isEmpty())
                        {
                            Toast.makeText(taskCreator.getContext(), "EMPTY TITLE | Task Not Added", Toast.LENGTH_SHORT).show();
                            addButton.callOnClick();
                        }
                        else
                        {
                            //list.add(new Data("TITITITI", "DDDDDDDD", "1-1-2021 18:33", false));
                            String title, desc, datetime;
                            title = ((EditText)view.findViewById(R.id.editTextTitle)).getText().toString();
                            desc = ((EditText)view.findViewById(R.id.editTextDescription)).getText().toString();
                           // datetime = ((EditText)view.findViewById(R.id.editTextDate)).getText().toString() + " " + ((EditText)view.findViewById(R.id.editTextTime)).getText().toString();
                            Log.d("TITLE - ", title);
                            Log.d("DESCRIPTION - ", desc);
                            //Log.d("DATETIME - ", datetime);
                            databaseHelper.addNotes(title, desc, DT, false);

                        }
                    }
                });
                taskCreator.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = taskCreator.create();
                dialog.show();*/
                adapter.showDialog();
            }
        });

    }
}