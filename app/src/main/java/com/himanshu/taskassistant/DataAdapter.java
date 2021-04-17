package com.himanshu.taskassistant;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.admin.DelegatedAdminReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.transition.Hold;

import java.util.ArrayList;
import java.util.Calendar;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.viewHolder> {
    Context context;
    Activity activity;
    ArrayList<Data> arrayList;
    DatabaseHelper database_helper;

    public DataAdapter(Context context, Activity activity, ArrayList<Data> arrayList) {
        this.context = context;
        this.activity  = activity ;
        this.arrayList = arrayList;
    }

    @Override
    public DataAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapter.viewHolder holder, final int position) {
        database_helper = new DatabaseHelper(context);
        holder.title.setText(arrayList.get(position).title);
        holder.description.setText(arrayList.get(position).description);
        holder.datetime.setText(arrayList.get(position).dateTime);
        holder.DELETE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database_helper.delete(arrayList.get(position).title);
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.CHECK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database_helper.delete(arrayList.get(position).title);
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title, description, datetime;
        ImageView DELETE, CHECK;
        public viewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleText);
            description = itemView.findViewById(R.id.descriptionText);
            datetime = itemView.findViewById(R.id.timeStamp);
            DELETE = itemView.findViewById(R.id.btnDelete);
            CHECK = itemView.findViewById(R.id.btnCheck);
        }
    }

    String tempstr1, tempstr, DT;
    public void showDialog()
    {
        Dialog taskCreator = new Dialog(activity);
        taskCreator.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        taskCreator.setTitle("Add Task");
        taskCreator.setContentView(R.layout.add_task_layout);
        params.copyFrom(taskCreator.getWindow().getAttributes());
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        taskCreator.getWindow().setAttributes(params);
        taskCreator.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        taskCreator.show();
        EditText titleText = taskCreator.findViewById(R.id.titleText);
        EditText descText = taskCreator.findViewById(R.id.descriptionText);

        ((Button)taskCreator.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int myear = calendar.get(Calendar.YEAR);
                int Mmonth = calendar.get(Calendar.MONTH);
                int mday = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
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
                        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
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

                        }, mhour, mminute, DateFormat.is24HourFormat(context));
                        timePickerDialog.show();

                    }
                }, myear, Mmonth, mday);
                datePickerDialog.show();
                String title = titleText.getText().toString();
                String desc = descText.getText().toString();
                database_helper.addNotes(title, desc, DT, false);
                taskCreator.cancel();
                notifyDataSetChanged();
            }
        });
    }
}

