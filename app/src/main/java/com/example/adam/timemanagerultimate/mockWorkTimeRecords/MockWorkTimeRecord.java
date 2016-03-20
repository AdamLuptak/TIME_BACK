package com.example.adam.timemanagerultimate.mockWorkTimeRecords;

import com.example.adam.timemanagerultimate.daoWorkTimeRecord.IWorkTimeRecordRepo;
import com.example.adam.timemanagerultimate.domain.WorkTimeRecord;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by adam on 19.3.2016.
 */
public class MockWorkTimeRecord {

    public void fillDatabaseWithValues(){
        
    }
    private IWorkTimeRecordRepo _workTimeRecordRepo;

    public MockWorkTimeRecord(IWorkTimeRecordRepo iWorkTimeRecordRepo) {
        this._workTimeRecordRepo = iWorkTimeRecordRepo;
    }

    public void generateData() throws SQLException {

        WorkTimeRecord wk = new WorkTimeRecord();

        //monday
        String arrivalTime = "Mon feb 15 2016  8:00:0";
        long arrivalTimeL = 1455519600000l;
        Date date = new Date(arrivalTimeL);
        wk.setArrivalTimeDate(date);

        arrivalTime = "Mon feb 15 2016  10:00:0";
        arrivalTimeL = 1455526800000l;
        wk.setLeaveTimeDate(new Date(arrivalTimeL));

        _workTimeRecordRepo.saveWorkTimeRecord(wk);

        arrivalTime = "Mon feb 15 2016  10:00:2";
        arrivalTimeL = 1455526802000l;
        wk.setArrivalTimeDate(new Date(arrivalTimeL));


        arrivalTime = "Mon feb 15 2016  15:00:00";
        arrivalTimeL = 1455544800000l;
        wk.setLeaveTimeDate(new Date(arrivalTimeL));

        _workTimeRecordRepo.saveWorkTimeRecord(wk);

        //tuesday
        arrivalTime = "Thu feb 16 2016  07:00:00";
        arrivalTimeL = 1455602400000l;
        wk.setArrivalTimeDate(new Date(arrivalTimeL));

        arrivalTime = "Thu feb 16 2016  15:0:0";
        arrivalTimeL = 1455631200000l;
        wk.setLeaveTimeDate(new Date(arrivalTimeL));

        _workTimeRecordRepo.saveWorkTimeRecord(wk);

        //wednesay
        arrivalTime = "wed feb 17 2016  9:0:0";
        arrivalTimeL = 1455696000000l;
        wk.setArrivalTimeDate(new Date(arrivalTimeL));

        arrivalTime = "wed feb 17 2016  19:0:0";
        arrivalTimeL = 1455732000000l;
        wk.setLeaveTimeDate(new Date(arrivalTimeL));

        _workTimeRecordRepo.saveWorkTimeRecord(wk);


        //thursday
        arrivalTime = "thur  feb 18 2016  8:0:0";
        arrivalTimeL = 1455778800000l;
        wk.setArrivalTimeDate(new Date(arrivalTime));
        wk.setLeaveTimeDate(null);
//        arrivalTime = "wed feb 16 2016  0:19:0";
//        arrivalTimeL = 1455578340000l;
//        wk.setLeaveTimeDate(new Date());

        _workTimeRecordRepo.saveWorkTimeRecord(wk);

    }

}
