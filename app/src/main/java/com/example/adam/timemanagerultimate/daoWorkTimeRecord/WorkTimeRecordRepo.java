package com.example.adam.timemanagerultimate.daoWorkTimeRecord;




import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.adam.timemanagerultimate.domain.WorkTimeRecord;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;


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
        Calendar cGe = getCalendar(dayNumber);
        Calendar cLt = getCalendar(dayNumber + 1);
        List<WorkTimeRecord> wkReturn = null;
        if(dayNumber == 7){
            wkReturn =  productDao.queryBuilder().where().ge("arrivalDate",cGe.getTime()).query();
        }else{
            wkReturn =  productDao.queryBuilder().where().ge("arrivalDate",cGe.getTime()).and().lt("arrivalDate",cLt.getTime()).query();
        }
        return wkReturn;
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
        List<WorkTimeRecord> wkReturn =  productDao.queryBuilder().where().ge("arrivalDate",c.getTime()).query();
        return wkReturn;
    }

    @NonNull
    private Calendar getCalendar(int dayOfWeek) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH,dayOfWeek);
        return c;
    }

    @Override
    public WorkTimeRecord getFirstWorkTimeForThisDay() throws SQLException {
        Calendar c = getCalendar(Calendar.DAY_OF_MONTH);
        WorkTimeRecord wkReturn =  productDao.queryBuilder().orderBy("arrivalDate",true).where().ge("arrivalDate", c.getTime()).queryForFirst();
        return wkReturn ;
    }
}