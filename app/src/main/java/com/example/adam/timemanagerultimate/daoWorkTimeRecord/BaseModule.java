package com.example.adam.timemanagerultimate.daoWorkTimeRecord;

import com.google.inject.AbstractModule;

/**
 * Created by aluptak on 02/02/2016.
 */
public class BaseModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IWorkTimeRecordRepo.class).to(WorkTimeRecordRepo.class);
    }
}
