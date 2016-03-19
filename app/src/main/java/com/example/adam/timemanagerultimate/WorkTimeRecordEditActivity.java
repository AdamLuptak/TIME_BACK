package com.example.adam.timemanagerultimate;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.adam.timemanagerultimate.daoWorkTimeRecord.IWorkTimeRecordRepo;
import com.example.adam.timemanagerultimate.domain.WorkTimeRecord;
import com.example.adam.timemanagerultimate.exception.WrongFormatTime;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_work_time_record_edit)
public class WorkTimeRecordEditActivity extends RoboActivity {

    @Inject
    private IWorkTimeRecordRepo workTimeRecordRepo;

    @InjectView(R.id.editTextArrivalTime)
    private EditText editTextArrivaltime;

    @InjectView(R.id.editTextLeaveTime)
    private EditText editTextLeaveTime;

    @InjectView(R.id.buttonWorkTimeRecordSave)
    private Button buttonWorkTimeRecordSave;

    @InjectView(R.id.buttonWorkTimeRecordDelete)
    private Button buttonWorkTimeRecordDelete;

    DateFormat sdf1 = new SimpleDateFormat("EEE-MM-dd-yyyy hh-mm-ss");

    private WorkTimeRecord selectedWorkTimeRecord;

    private Calendar cal;
    private int day;
    private int month;
    private int year;

    private TimePickerDialog tmDialog;
    private DatePickerDialog dtDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedWorkTimeRecord = (WorkTimeRecord) (getIntent()
                .getSerializableExtra("WorkTimeRecord"));
        if (selectedWorkTimeRecord != null) {
            loadSelectedWorkTimeRecord(selectedWorkTimeRecord);
        }
        buttonWorkTimeRecordSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {

                    if (selectedWorkTimeRecord == null) {

                        WorkTimeRecord workTimeRecord;
                        try {
                            workTimeRecord = new WorkTimeRecord();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy HH.mm.ss");
                            // Date date = sdf.parse(_editTextArrivaltime.getText().toString());
                            Date date = new Date();
                            workTimeRecord.setArrivalTimeDate(date);
                            // date = sdf.parse(_editTextArrivaltime.getText().toString());
                            workTimeRecord.setLeaveTimeDate(date);
                            workTimeRecordRepo.saveWorkTimeRecord(workTimeRecord);
                        } catch (Exception e) {
                            alertBox();
                            throw new WrongFormatTime("Wrong formated arrival or leave time");
                        }
                    } else {
                        try {
                            workTimeRecordRepo.updateWorkTimeRecord(selectedWorkTimeRecord);
                        } catch (Exception e) {
                            alertBox();
                            throw new WrongFormatTime("Wrong formated arrival or leave time");
                        }
                    }
                    Intent intent = new Intent(WorkTimeRecordEditActivity.this,
                            WorkTimeRecordListActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivityForResult(intent, 0);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        buttonWorkTimeRecordDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (selectedWorkTimeRecord != null) {
                    try {
                        workTimeRecordRepo.deleteWorkTimeRecord(selectedWorkTimeRecord);
                        Intent intent = new Intent(WorkTimeRecordEditActivity.this,
                                WorkTimeRecordListActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivityForResult(intent, 0);
                        finish();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        editTextArrivaltime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        TimePickerDialog.OnTimeSetListener listenerTimePicker = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                selectedWorkTimeRecord.getArrivalTimeDate().setHours(hourOfDay);
                selectedWorkTimeRecord.getArrivalTimeDate().setMinutes(minute);
                editTextArrivaltime.setText(sdf1.format(selectedWorkTimeRecord.getArrivalTimeDate()).toString());
            }
        };

        DatePickerDialog.OnDateSetListener listenerDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectedWorkTimeRecord.getArrivalTimeDate().setYear(year);
                selectedWorkTimeRecord.getArrivalTimeDate().setMonth(monthOfYear);
                selectedWorkTimeRecord.getArrivalTimeDate().setDate(dayOfMonth);
                tmDialog.show();
            }
        };

        dtDialog = new DatePickerDialog(this, 2,listenerDatePicker ,  cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
        tmDialog = new TimePickerDialog(this, 2, listenerTimePicker,  cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false);

        editTextArrivaltime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog();
            }
        });
    }

    public void DateDialog() {
        dtDialog.show();
    }

    public void alertBox() {
        new AlertDialog.Builder(this)
                .setTitle("Wrong format input ")
                .setMessage("Wrong format for Arrival or Leave time")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void loadSelectedWorkTimeRecord(WorkTimeRecord workTimeRecord) {
        if (workTimeRecord != null) {
            editTextArrivaltime.setText(sdf1.format(workTimeRecord.getArrivalTimeDate()).toString());
                editTextLeaveTime.setText(sdf1.format(workTimeRecord.getLeaveTimeDate()).toString());
            } else {
                editTextLeaveTime.setText("null");
            }
            cal = Calendar.getInstance();
            cal.setTime(selectedWorkTimeRecord.getArrivalTimeDate());
    }
}

