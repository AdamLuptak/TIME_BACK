package com.example.adam.timemanagerultimate.controller;

import com.example.adam.timemanagerultimate.daoWorkTimeRecord.IWorkTimeRecordRepo;
import com.example.adam.timemanagerultimate.domain.WorkTimeRecord;
import com.google.inject.Inject;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by adam on 19.3.2016.
 */
public class TimeController implements ITimeController {

    private IWorkTimeRecordRepo workTimeRecordRepo;

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
        return null;
    }

    @Override
    public String getOverTime() {
        return null;
    }

    @Override
    public String getGoHome() {
        return null;
    }

    @Override
    public void saveRecord(Date dateForSave) throws SQLException {

        long arrivalTimeL = 1455519600000l;
        Date date = new Date(arrivalTimeL);
        WorkTimeRecord workTimeRecord1 = new WorkTimeRecord(date);
        workTimeRecordRepo.saveWorkTimeRecord(workTimeRecord1);
        WorkTimeRecord lastWorkTimeRecord = workTimeRecordRepo.getLastWorkTimeRecord();
        if (lastWorkTimeRecord != null && lastWorkTimeRecord.getArrivalTimeDate() != null && lastWorkTimeRecord.getLeaveTimeDate() == null) {
            lastWorkTimeRecord.setLeaveTimeDate(dateForSave);
            workTimeRecordRepo.updateWorkTimeRecord(lastWorkTimeRecord);
        } else {
            WorkTimeRecord workTimeRecord = new WorkTimeRecord(dateForSave);
            workTimeRecordRepo.saveWorkTimeRecord(workTimeRecord);
        }
    }
}
