package com.example.adam.timemanagerultimate.daoWorkTimeRecord;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.adam.timemanagerultimate.domain.WorkTimeRecord;
import com.google.inject.Singleton;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import javax.inject.Inject;

/**
 * Created by aluptak on 02/02/2016.
 * /data/data/com.example.adam.timemanagerultimate/databases
 */
@Singleton
public class DbHelper extends OrmLiteSqliteOpenHelper {

    private Dao<WorkTimeRecord, Integer> workTimeRecordDao = null;
    private RuntimeExceptionDao<WorkTimeRecord, Integer> workTimeRecordRuntimeDao = null;

    private final static String DATABASENAME = "TimeManager.db";
    private final static int DATABASEVERSION = 1;

    @Inject
    public DbHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
        resetDatabase(WorkTimeRecord.class);
    }

    /**
     * Clean database
     * @param clazz generic
     */
    public void resetDatabase(Class<?> clazz) {
        try {
            TableUtils.clearTable(getConnectionSource(), clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, WorkTimeRecord.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}


