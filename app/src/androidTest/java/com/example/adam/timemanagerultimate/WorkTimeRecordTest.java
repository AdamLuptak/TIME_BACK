package com.example.adam.timemanagerultimate;

import com.example.adam.timemanagerultimate.domain.WorkTimeRecord;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by adam on 17.3.2016.
 */
public class WorkTimeRecordTest extends TestCase {

    WorkTimeRecord workTimeRecord;

    @Before
    public void setUp() {
        this.workTimeRecord = new WorkTimeRecord();
    }


    @Test
    public void testGetId() throws Exception {
        this.workTimeRecord.setId(10);
        assertEquals(10, this.workTimeRecord.getId());
    }

    @Test
    public void testGetArrivalTimeDate() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1988);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date arrivalTisme = cal.getTime();
        this.workTimeRecord.setArrivalTimeDate(arrivalTisme);
        assertEquals(cal.getTime(), this.workTimeRecord.getArrivalTimeDate());
    }

    @Test
    public void testSetArrivalTimeDate() throws Exception {
        testGetArrivalTimeDate();
    }

    @Test
    public void testGetLeaveTimeDate() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1988);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date arrivalTisme = cal.getTime();
        this.workTimeRecord.setLeaveTimeDate(arrivalTisme);
        assertEquals(cal.getTime(), this.workTimeRecord.getLeaveTimeDate());
    }

    @Test
    public void testSetLeaveTimeDate() throws Exception {
        testGetLeaveTimeDate();
    }

    @Test
    public void testToString() throws Exception {
        testGetArrivalTimeDate();
        testGetLeaveTimeDate();

        assertEquals("WorkTimeRecord{ " +
                "\narrivalDate: " + workTimeRecord.getArrivalTimeDate() +
                "\nleaveDate: " + workTimeRecord.getLeaveTimeDate(), workTimeRecord.toString());
    }

    @After
    private void clean() {
        this.workTimeRecord = null;
        System.gc();
    }
}