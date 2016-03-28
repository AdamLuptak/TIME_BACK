package com.example.adam.timemanagerultimate.daoWorkTimeRecord;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import com.example.adam.timemanagerultimate.MainActivity;
import com.example.adam.timemanagerultimate.controller.TimeController;
import com.example.adam.timemanagerultimate.domain.WorkTimeRecord;
import com.google.inject.Inject;

import org.joda.time.DateTime;
import org.junit.Before;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by adam on 17.3.2016.
 */
public class WorkTimeRecordRepoTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Object mainActivity;

    public WorkTimeRecordRepoTest() {
        super(MainActivity.class);
    }


    @Before
    public void setupDB() {

    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.mainActivity = getActivity();

    }

    @SmallTest
    public void testGetLastWorkTimeRecord() throws Exception {
    }
}