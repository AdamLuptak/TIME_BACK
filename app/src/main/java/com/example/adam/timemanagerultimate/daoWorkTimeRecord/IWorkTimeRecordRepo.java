package com.example.adam.timemanagerultimate.daoWorkTimeRecord;


import com.example.adam.timemanagerultimate.domain.WorkTimeRecord;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by aluptak on 02/02/2016.
 */
public interface IWorkTimeRecordRepo {

    boolean isNullSomOfLeaveDateForYesterday() throws SQLException;

    public List getWorkTimeRecords() throws SQLException;

    public WorkTimeRecord getWorkTimeRecord(String id) throws SQLException;

    public void deleteWorkTimeRecord(WorkTimeRecord deleteProduct) throws SQLException;

    public void saveWorkTimeRecord(WorkTimeRecord saveProduct) throws SQLException;

    public void updateWorkTimeRecord(WorkTimeRecord updateProduct) throws SQLException;

    public List<WorkTimeRecord> getAlldaysForThisWeek() throws  SQLException;

    public WorkTimeRecord getFirstWorkTimeForThisDay() throws  SQLException;

    public WorkTimeRecord getLastWorkTimeRecord()throws  SQLException;

    public List<WorkTimeRecord> getAllWorkTimeRecordsForThisDay(int DayNumber)throws  SQLException;

    public boolean isLeaveTimeForLastDayNull() throws SQLException;

}
