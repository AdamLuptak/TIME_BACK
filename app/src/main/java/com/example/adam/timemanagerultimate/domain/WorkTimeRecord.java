package com.example.adam.timemanagerultimate.domain;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by adam on 15.3.2016.
 */
@DatabaseTable
public class WorkTimeRecord implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "arrivalDate", canBeNull = false)
    private Date arrivalDate;
    @DatabaseField(columnName = "leaveDate", canBeNull = true)
    private Date leaveDate;

    public WorkTimeRecord(Date arrivalTimeDate, Date leaveTimeDate) {
        super();
        this.arrivalDate = arrivalTimeDate;
        this.leaveDate = leaveTimeDate;
    }

    public WorkTimeRecord() {

    }

    public WorkTimeRecord(Date arrivalDate) {
        this.setArrivalTimeDate(arrivalDate);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getArrivalTimeDate() {
        return arrivalDate;
    }

    public void setArrivalTimeDate(Date arrivalTimeDate) {
        this.arrivalDate = arrivalTimeDate;
    }

    public Date getLeaveTimeDate() {
        return leaveDate;
    }

    public void setLeaveTimeDate(Date leaveTimeDate) {

        this.leaveDate = leaveTimeDate;
    }

    @Override
    public String toString() {
        return "WorkTimeRecord{ " +
                "\narrivalDate: " + this.arrivalDate +
                "\nleaveDate: " + this.leaveDate;
    }
}