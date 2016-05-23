package com.example.adam.timemanagerultimate;


import com.example.adam.timemanagerultimate.daoWorkTimeRecord.WorkTimeRecordRepo;
import com.example.adam.timemanagerultimate.domain.WorkTimeRecord;
import com.example.adam.timemanagerultimate.test.BuildConfig;
import com.google.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;


import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created by adam on 17.3.2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class WorkTimeRecordRepoTest{

    @Inject
    WorkTimeRecordRepo workTimeRecordRepo;


    @Test
    public void testInitialDB(){
        try {
            Date arrivalDate = new Date();
            WorkTimeRecord workTimeRecord = new WorkTimeRecord(arrivalDate);
            workTimeRecordRepo.saveWorkTimeRecord(workTimeRecord);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}