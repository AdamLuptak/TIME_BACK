package com.example.adam.timemanagerultimate.controller;

import com.example.adam.timemanagerultimate.domain.WorkTimeRecord;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by adam on 19.3.2016.
 */
public interface ITimeController {
    public void setArrivalTime(Date arrivalTime) throws SQLException;
    public void setLeaveTime(Date leaveTime) throws SQLException;
    public List<WorkTimeRecord> getAllWorkTimeForThisWeek() throws SQLException;
    public String getGoHomeOV();
    public String getOverTime() throws SQLException;
    public String getGoHomeTime() throws SQLException;
    public void saveRecord(Date dateForSave) throws SQLException;
}
