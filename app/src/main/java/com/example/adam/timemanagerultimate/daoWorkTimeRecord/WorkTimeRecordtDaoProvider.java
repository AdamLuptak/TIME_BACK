package com.example.adam.timemanagerultimate.daoWorkTimeRecord;



import com.example.adam.timemanagerultimate.domain.WorkTimeRecord;
import com.google.inject.Provider;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import javax.inject.Inject;

/**
 * Created by aluptak on 02/02/2016.
 */
public class WorkTimeRecordtDaoProvider implements Provider<Dao<WorkTimeRecord, String>> {

    private ConnectionSource connectionSource;

    @Inject
    public WorkTimeRecordtDaoProvider(DbHelper dbHelper) {
        connectionSource = dbHelper.getConnectionSource();
    }

    @Override
    public Dao<WorkTimeRecord, String> get() {
        try {
            return DaoManager.createDao(connectionSource, WorkTimeRecord.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
