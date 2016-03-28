package com.example.adam.timemanagerultimate.controller;

import com.example.adam.timemanagerultimate.daoWorkTimeRecord.IWorkTimeRecordRepo;
import com.example.adam.timemanagerultimate.domain.WorkTimeRecord;
import com.google.inject.Inject;

import org.joda.time.DateTime;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by adam on 19.3.2016.
 */
public class TimeController implements ITimeController {

    private IWorkTimeRecordRepo workTimeRecordRepo;
    final long WORK_TIME_HOURS = 30600000l;
    DateFormat sdf1 = new SimpleDateFormat(" hh:mm:ss");
    private long goHomeLong = 0l;
    private long goHomeOv = 0l;
    private long overTime = 0l;

    @Inject
    public TimeController(IWorkTimeRecordRepo workTimeRecordRepo) {
        this.workTimeRecordRepo = workTimeRecordRepo;
    }

    @Override
    public void setArrivalTime(Date arrivalTime) throws SQLException {
        WorkTimeRecord workTimeRecord = new WorkTimeRecord(arrivalTime);
        workTimeRecordRepo.saveWorkTimeRecord(workTimeRecord);
    }

    @Override
    public void setLeaveTime(Date leaveTime) throws SQLException {
        WorkTimeRecord lastWorkTimeRecord = workTimeRecordRepo.getLastWorkTimeRecord();
        lastWorkTimeRecord.setLeaveTimeDate(leaveTime);
        workTimeRecordRepo.saveWorkTimeRecord(lastWorkTimeRecord);
    }

    @Override
    public List<WorkTimeRecord> getAllWorkTimeForThisWeek() throws SQLException {
        return workTimeRecordRepo.getAlldaysForThisWeek();
    }

    @Override
    public String getGoHomeOV() {
        long goHomeLonGSum = goHomeLong - overTime;
        return (goHomeLonGSum > 0) ? sdf1.format(new Date(goHomeLonGSum)).toString() : "##:##:##";
    }

    @Override
    public String getOverTime() throws SQLException {
        //vrat mi vsetko pre pondelok
        //prvy den overtime(workTime(all records from that day) - workPerDayHours) + yesterdayOV
        long yesterdayOV = 0;
        for (int i = 1; i < 6; i++) {
            int today = DateTime.now().getDayOfWeek();
            if (i < today) {
                List<WorkTimeRecord> workTimeRecordsPerDay = workTimeRecordRepo.getAllWorkTimeRecordsForThisDay(i);
                long overTimeSum = 0;
                for (WorkTimeRecord workTimeRecord : workTimeRecordsPerDay) {
                    if (workTimeRecord.getLeaveTimeDate() != null) {
                        overTimeSum += workTimeRecord.getLeaveTimeDate().getTime() - workTimeRecord.getArrivalTimeDate().getTime();
                    }
                }
                if (overTimeSum != 0) {
                    overTimeSum -= (60 * 60 * 1000 * 8.5);
                    yesterdayOV = overTimeSum + yesterdayOV;
                }
            }
        }
        this.overTime = yesterdayOV;
        int seconds = (int) (yesterdayOV / 1000) % 60;
        int minutes = (int) ((yesterdayOV / (1000 * 60)) % 60);
        int hours = (int) ((yesterdayOV / (1000 * 60 * 60)) % 24);
        return (yesterdayOV != 0) ? String.valueOf(hours) + ":" + String.valueOf(minutes) + ":" + String.valueOf(seconds) : "##:##:##";
    }

    @Override
    public String getGoHomeTime() throws SQLException {
        WorkTimeRecord firstWorkTimeForThisDay = workTimeRecordRepo.getFirstWorkTimeForThisDay();
        if (firstWorkTimeForThisDay != null) {
            long l = firstWorkTimeForThisDay.getArrivalTimeDate().getTime() + 30600000l;
            this.goHomeLong = l;
            return sdf1.format(new Date(l)).toString();
        }
        return "##:##:##";
    }

    @Override
    public void saveRecord(Date dateForSave) throws SQLException {
        WorkTimeRecord lastWorkTimeRecord = workTimeRecordRepo.getLastWorkTimeRecord();
        if (lastWorkTimeRecord != null && lastWorkTimeRecord.getArrivalTimeDate() != null && lastWorkTimeRecord.getLeaveTimeDate() == null) {
            lastWorkTimeRecord.setLeaveTimeDate(dateForSave);
            workTimeRecordRepo.updateWorkTimeRecord(lastWorkTimeRecord);
        } else {
            WorkTimeRecord workTimeRecord = new WorkTimeRecord(dateForSave);
            workTimeRecordRepo.saveWorkTimeRecord(workTimeRecord);
        }
    }

    public boolean findFirstDateForThisDay() {
        try {
            return (workTimeRecordRepo.getFirstWorkTimeForThisDay() != null) ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void calculateTimes() {
        try {
            this.getOverTime();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isLeaveTimeForLastDayNull() {
        try {
            return workTimeRecordRepo.isLeaveTimeForLastDayNull();
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean isNullSomOfLeaveDateForYesterday(){
        try {
            return  workTimeRecordRepo.isNullSomOfLeaveDateForYesterday();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isMonday() {
        int dayOfWeek = DateTime.now().getDayOfWeek();
        return (dayOfWeek == 1) ? true : false;
    }
}
