package com.example.adam.timemanagerultimate.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


import com.example.adam.timemanagerultimate.R;
import com.example.adam.timemanagerultimate.controller.TimeController;
import com.example.adam.timemanagerultimate.daoWorkTimeRecord.DbHelper;
import com.example.adam.timemanagerultimate.daoWorkTimeRecord.IWorkTimeRecordRepo;
import com.example.adam.timemanagerultimate.daoWorkTimeRecord.WorkTimeRecordRepo;
import com.example.adam.timemanagerultimate.daoWorkTimeRecord.WorkTimeRecordtDaoProvider;

import org.joda.time.DateTime;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class UpdaterService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_FOO = "com.example.aluptak.androidrobo.services.action.FOO";
    public static final String ACTION_BAZ = "com.example.aluptak.androidrobo.services.action.BAZ";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "com.example.aluptak.androidrobo.services.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.aluptak.androidrobo.services.extra.PARAM2";
    private static final String TAG = "UpdaterService";

    private boolean rob = false;
    private Handler mHandler;


    private IWorkTimeRecordRepo _workTimeRecordRepo;

    public UpdaterService() {
        super("UpdaterService");

        Log.e(TAG, "vytvoreny service");

    }

    @Override
    public void onCreate() {
        mHandler = new Handler();
        super.onCreate();
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                _workTimeRecordRepo = new WorkTimeRecordRepo(UpdaterService.this, new WorkTimeRecordtDaoProvider(new DbHelper(UpdaterService.this)));
                TimeController timeController = new TimeController(_workTimeRecordRepo);
                Log.e(TAG, "idem zo servicu mal by som ist kazdych 15 sekund " + rob);
                String goHomeOverTime = null;
                String goHomeTime = null;

                try {
                    List workTimeRecords = _workTimeRecordRepo.getWorkTimeRecords();
                    for (Object workTimeRecord : workTimeRecords) {
                        Log.e("sdf", workTimeRecord.toString());
                    }

                    goHomeOverTime = timeController.getGoHomeOV();
                    goHomeTime = timeController.getGoHomeTime();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    timeController.getOverTime();
                    timeController.getGoHomeOV();
                    timeController.getGoHomeTime();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                DateTime timeNow =  DateTime.now();
                long timeGoHomeOV = timeController.getGoHomeOv();
                    if(timeNow.getMillis() >= timeGoHomeOV && timeGoHomeOV != 0) {
                        NotificationCompat.Builder notification =
                                new NotificationCompat.Builder(UpdaterService.this)
                                        .setSmallIcon(R.drawable.icon)
                                        .setContentTitle("Go home")
                                        .setContentText("Go home time");

                        NotificationManager notificationManager =
                                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                        notificationManager.notify(0, notification.build());
                    }

            }
        });
    }


}
