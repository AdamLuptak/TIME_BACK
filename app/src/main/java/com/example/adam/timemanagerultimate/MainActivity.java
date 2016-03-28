package com.example.adam.timemanagerultimate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adam.timemanagerultimate.controller.TimeController;
import com.example.adam.timemanagerultimate.daoWorkTimeRecord.IWorkTimeRecordRepo;
import com.example.adam.timemanagerultimate.domain.WorkTimeRecord;
import com.example.adam.timemanagerultimate.mockWorkTimeRecords.MockWorkTimeRecord;

import org.joda.time.DateTime;
import org.roboguice.shaded.goole.common.base.Predicates;

import java.sql.SQLException;
import java.util.Date;

import javax.inject.Inject;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {

    private Boolean inWork = false;

    @Inject
    private IWorkTimeRecordRepo workTimeRecordRepo;

    @Inject
    private TimeController timeController;

    @InjectView(R.id.doRecordButton)
    private Button doRecordButton;

    @InjectView(R.id.showStatisticsButton)
    private Button showStatisticsButton;

    @InjectView(R.id.startServiceButton)
    private Button startServiceButton;

    @InjectView(R.id.stopServiceButton)
    private Button stopServiceButton;

    @InjectView(R.id.goHomeOvNumber)
    private TextView goHomeOv;

    @InjectView(R.id.goHomeNumber)
    private TextView goHome;

    @InjectView(R.id.overtimeNumber)
    private TextView overTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        try {
//            workTimeRecordRepo.saveWorkTimeRecord(new WorkTimeRecord(new DateTime(1458543600000l).toDate(), new Date(1458568800000l)));
//            workTimeRecordRepo.saveWorkTimeRecord(new WorkTimeRecord(new Date(1458626400000l), new Date(1458655200000l)));
//            workTimeRecordRepo.saveWorkTimeRecord(new WorkTimeRecord(new Date(1458720000000l), new Date(1458756000000l)));
//            workTimeRecordRepo.saveWorkTimeRecord(new WorkTimeRecord(new Date(1458802800000l), new Date(1458831600000l)));
//            workTimeRecordRepo.saveWorkTimeRecord(new WorkTimeRecord(new Date(1458885600000l)));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        Intent intentEdit = this.getIntent();
        String inWorkBefore = intentEdit.getStringExtra("inWork");
        if (inWorkBefore != null) {
            setupDoRecordButtonText(Boolean.valueOf(inWorkBefore));
        }
        if (!(timeController.isMonday())) {
            if (timeController.isNullSomOfLeaveDateForYesterday()) {
                showStatisticsButton.callOnClick();
                return;
            }
        }

        if (timeController.findFirstDateForThisDay()) {
            timeController.calculateTimes();

            if (timeController.isLeaveTimeForLastDayNull()) {
                //set AddLeaveTime
                this.inWork = true;
                setupDoRecordButtonText(this.inWork);
            } else {
                this.inWork = false;
                setupDoRecordButtonText(this.inWork);
            }
        } else {

        }

        try {
            overTime.setText(timeController.getOverTime());
            goHome.setText(timeController.getGoHomeTime());
            goHomeOv.setText(timeController.getGoHomeOV());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void FillDB() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean("firstRun", true)) {

            prefs.edit().putBoolean("firstRun", false).commit();
        }
    }

    private void updateTimesFromDB() {
        try {
            overTime.setText(timeController.getOverTime());
            goHome.setText(timeController.getGoHomeTime());
            goHomeOv.setText(timeController.getGoHomeOV());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doRecord(View view) throws SQLException {
        timeController.saveRecord(new Date());
        this.inWork = !this.inWork;
        setupDoRecordButtonText(this.inWork);
        updateTimesFromDB();
        Log.e("sdfs", timeController.getAllWorkTimeForThisWeek().toString());
    }

    private void setupDoRecordButton() {
        if (!this.inWork) {
            doRecordButton.setText(R.string.leaveTimeString);
        } else {
            doRecordButton.setText(R.string.arrivalTimeString);
            updateTimesFromDB();
        }
        this.inWork = !this.inWork;
    }

    private void setupDoRecordButtonText(boolean doRecord) {
        if (this.inWork) {
            doRecordButton.setText(R.string.leaveTimeString);
        } else {
            doRecordButton.setText(R.string.arrivalTimeString);
        }
    }


    public void showStatistics(View view) {
        Intent intent = new Intent(this, WorkTimeRecordListActivity.class);
        intent.putExtra("inWork", String.valueOf(this.inWork));
        startActivity(intent);
    }

    public void startUpdateService(View view) {

    }

    public void stopUpdateService(View view) {

    }


}
