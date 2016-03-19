package com.example.adam.timemanagerultimate.daoWorkTimeRecord;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;
import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import com.example.adam.timemanagerultimate.MainActivity;
import com.example.adam.timemanagerultimate.domain.WorkTimeRecord;
import com.j256.ormlite.dao.Dao;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Date;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * Created by adam on 17.3.2016.
 */
public class WorkTimeRecordRepoTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Object mainActivity;

    public WorkTimeRecordRepoTest() {
        super(MainActivity.class);
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