package com.example.adam.timemanagerultimate.daoWorkTimeRecord;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.adam.timemanagerultimate.domain.WorkTimeRecord;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;


import org.joda.time.DateTime;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;


/**
 * Created by aluptak on 02/02/2016.
 */
public class WorkTimeRecordRepo implements IWorkTimeRecordRepo {

    private Dao<WorkTimeRecord, String> productDao;

    @Inject
    public WorkTimeRecordRepo(Context context, WorkTimeRecordtDaoProvider workTimeRecordtDaoProvider) {
        productDao = workTimeRecordtDaoProvider.get();
    }

    @Override
    public WorkTimeRecord getLastWorkTimeRecord() throws SQLException {
        Calendar c = getCalendar(Calendar.MONDAY);
        return productDao.queryBuilder().orderBy("arrivalDate", false).where().ge("arrivalDate", c.getTime()).queryForFirst();
    }

    @Override
    public List<WorkTimeRecord> getAllWorkTimeRecordsForThisDay(int dayNumber) throws SQLException {

//        Calendar cGe = Calendar.getInstance();
//        Date date = new Date();
//        cGe.setTime(date);
//        cGe.set(Calendar.DAY_OF_WEEK, dayNumber);
//        cGe.set(Calendar.HOUR_OF_DAY, 0);
//        cGe.set(Calendar.MINUTE, 0);
//        cGe.set(Calendar.SECOND, 0);
//        Date ArrivalTime = new Date(cGe.getTimeInMillis());
//
//        Calendar cLt = getCalendarDay(dayNumber + 1);
//        cGe.setTime(date);
//        cGe.set(Calendar.DAY_OF_WEEK, dayNumber + 1);
//        cGe.set(Calendar.HOUR_OF_DAY, 0);
//        cGe.set(Calendar.MINUTE, 0);
//        cGe.set(Calendar.SECOND, 0);
//        Date NexDayTime = new Date(cGe.getTimeInMillis());

        Date arrivalTime  = DateTime.now().withDayOfWeek(dayNumber).toDate();
        arrivalTime.setHours(0);
        Date nexDayTime = DateTime.now().withDayOfWeek(dayNumber + 1).toDate();
        nexDayTime.setHours(0);

        List<WorkTimeRecord> wkReturn = null;
        wkReturn = productDao.queryBuilder().where().ge("arrivalDate", arrivalTime).and().lt("arrivalDate", nexDayTime).query();
        return wkReturn;
    }

    @Override
    public boolean isLeaveTimeForLastDayNull() throws SQLException {
        Date todayDate = DateTime.now().toDate();
        todayDate.setHours(0);
        WorkTimeRecord workTimeRecord = productDao.queryBuilder().orderBy("arrivalDate", false).where().ge("arrivalDate", todayDate).queryForFirst();
        if(workTimeRecord == null ){
            return false;
        }
        return (workTimeRecord.getLeaveTimeDate() != null) ? false : true;
    }

    @Override
    public boolean isNullSomOfLeaveDateForYesterday() throws SQLException {
        Date todayDate = DateTime.now().minusDays(1).toDate();
        todayDate.setHours(0);
        WorkTimeRecord workTimeRecord = productDao.queryBuilder().orderBy("leaveDate", false).where().ge("leaveDate", todayDate).queryForFirst();
        return (workTimeRecord == null) ? true : false;
    }

    @Override
    public List getWorkTimeRecords() throws SQLException {
        return productDao.queryBuilder().orderBy("arrivalDate", false).query();
    }

    @Override
    public WorkTimeRecord getWorkTimeRecord(String id) throws SQLException {
        return productDao.queryForId(id);
    }

    @Override
    public void deleteWorkTimeRecord(WorkTimeRecord deleteProduct) throws SQLException {
        productDao.delete(deleteProduct);
    }

    @Override
    public void saveWorkTimeRecord(WorkTimeRecord deleteProduct) throws SQLException {
        productDao.create(deleteProduct);
    }

    @Override
    public void updateWorkTimeRecord(WorkTimeRecord updateProduct) throws SQLException {
        productDao.update(updateProduct);
    }

    @Override
    public List<WorkTimeRecord> getAlldaysForThisWeek() throws SQLException {
        Calendar c = getCalendar(Calendar.MONDAY);
        List<WorkTimeRecord> wkReturn = productDao.queryBuilder().where().ge("arrivalDate", c.getTime()).query();
        return wkReturn;
    }

    @NonNull
    private Calendar getCalendar(int dayOfWeek) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, dayOfWeek);
        return c;
    }


    @NonNull
    private Calendar getCalendarDay(int dayOfWeek) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        return c;
    }

    @Override
    public WorkTimeRecord getFirstWorkTimeForThisDay() throws SQLException {
        Date todayDate = DateTime.now().toDate();
        todayDate.setHours(0);
        WorkTimeRecord wkReturn = productDao.queryBuilder().orderBy("arrivalDate", true).where().ge("arrivalDate",todayDate).queryForFirst();
        return wkReturn;
    }
}