package com.example.adam.timemanagerultimate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
    private IWorkTimeRecordRepo _workTimeRecordRepo;

    @InjectView(R.id.editTextWorkTimeRecordId)
    private EditText _editTextWorkTimeRecord;

    @InjectView(R.id.editTextArrivalTime)
    private EditText _editTextArrivaltime;

    @InjectView(R.id.editTextLeaveTime)
    private EditText _editTextLeaveTime;





    @InjectView(R.id.buttonWorkTimeRecordSave)
    private Button _buttonWorkTimeRecordSave;

    @InjectView(R.id.buttonWorkTimeRecordDelete)
    private Button _buttonWorkTimeRecordDelete;



    private WorkTimeRecord _selectedWorkTimeRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Calendar today = Calendar.getInstance();
//        datePicker.init(
//                today.get(Calendar.YEAR),
//                today.get(Calendar.MONTH),
//                today.get(Calendar.DAY_OF_MONTH),
//                new DatePicker.OnDateChangedListener() {
//
//                    @Override
//                    public void onDateChanged(DatePicker view,
//                                              int year, int monthOfYear, int dayOfMonth) {
//                        Calendar cal = Calendar.getInstance();
//                        cal.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
//                        cal.set(Calendar.MONTH, datePicker.getMonth());
//                        cal.set(Calendar.YEAR, datePicker.getYear());
//                        Date d = new Date(cal.getTimeInMillis());
//                        d.setHours(tiPicker.getCurrentHour());
//                        d.setMinutes(tiPicker.getCurrentMinute());
//
//                        _editTextLeaveTime.setText(d.toString());
//                    }
//                });

        _selectedWorkTimeRecord = (WorkTimeRecord) (getIntent()
                .getSerializableExtra("WorkTimeRecord"));
        if (_selectedWorkTimeRecord != null) {
            loadSelectedWorkTimeRecord(_selectedWorkTimeRecord);
        }
        _buttonWorkTimeRecordSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {

                    if (_selectedWorkTimeRecord == null) {

                        WorkTimeRecord workTimeRecord;
                        try {
                            workTimeRecord = new WorkTimeRecord();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy HH.mm.ss");
                            // Date date = sdf.parse(_editTextArrivaltime.getText().toString());
                            Date date = new Date();
                            workTimeRecord.setArrivalTimeDate(date);
                            // date = sdf.parse(_editTextArrivaltime.getText().toString());
                            workTimeRecord.setLeaveTimeDate(date);
                            _workTimeRecordRepo.saveWorkTimeRecord(workTimeRecord);
                        } catch (Exception e) {
                            alertBox();
                            throw new WrongFormatTime("Wrong formated arrival or leave time");
                        }
                    } else {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy HH.mm.ss");
                            //Date date = sdf.parse(_editTextArrivaltime.getText().toString());
                            Date date = new Date();
                            _selectedWorkTimeRecord.setArrivalTimeDate(date);
                            // date = sdf.parse(_editTextArrivaltime.getText().toString());
                            _selectedWorkTimeRecord.setLeaveTimeDate(date);

                            _workTimeRecordRepo.updateWorkTimeRecord(_selectedWorkTimeRecord);
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

        _buttonWorkTimeRecordDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (_selectedWorkTimeRecord != null) {
                    try {
                        _workTimeRecordRepo.deleteWorkTimeRecord(_selectedWorkTimeRecord);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void loadSelectedWorkTimeRecord(WorkTimeRecord workTimeRecord) {
        if (workTimeRecord != null) {
            _editTextWorkTimeRecord.setText(String.valueOf(workTimeRecord.getId()));
            _editTextArrivaltime.setText("sdsd");
            if (workTimeRecord.getLeaveTimeDate() != null) {
                _editTextLeaveTime.setText(workTimeRecord.getLeaveTimeDate().toString());
            } else {
                _editTextLeaveTime.setText("null");
            }
        }
    }
}

