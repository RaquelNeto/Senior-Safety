package pt.ipp.estg.seniorsafety.notificacaoDiaria;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Calendar;

import androidx.core.app.NotificationCompat;
import pt.ipp.estg.seniorsafety.R;
import pt.ipp.estg.seniorsafety.notificacaoDiaria.Task.AlarmTask;

public class NotificationService extends Service {

    private PendingIntent pendingIntent;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    /**
     * Class for clients to access
     */
    public class ServiceBinder extends Binder {
        NotificationService getService() {
            return NotificationService.this;
        }
    }

    // Unique id to identify the notification.
    private static final int NOTIFICATION = 123;
    // Name of an intent extra we can use to identify if this service was started to create a notification
    public static final String INTENT_NOTIFY = "estgf.ipp.pt.cmu.Service.INTENT_NOTIFY";
    // The system notification manager
    private NotificationManager notificationManager;

    @Override
    public void onCreate() {
        Log.i("NotifyService", "onCreate()");
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        // If this service was started by out AlarmTask intent then we want to show our notification
        if (intent.getBooleanExtra(INTENT_NOTIFY, false))
            showNotification();

        // We don't care if this service is stopped as we have already delivered our notification
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // This is the object that receives interactions from clients
    private final IBinder mBinder = new NotificationService.ServiceBinder();

    /**
     * Creates a notification and shows it in the OS drag-down status bar
     */
    private void showNotification() {
        // This is the 'title' of the notification
        CharSequence title = "Jogo Matemática";
        // This is the icon to use on the notification
        int icon = R.drawable.ic_sms_black_24dp;
        // This is the scrolling text of the notification
        CharSequence text = "Novos cálculos! Jogue já!";
        // What time to show on the notification
        long time = System.currentTimeMillis();

//		Notification notification = new Notification(icon, text, time);

        // The PendingIntent to launch our activity if the user selects this notification
        //PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, StartUpBootReceiver.class), 0);

        // Set the info for the views that show in the notification panel.
//		notification.setLatestEventInfo(this, title, text, contentIntent);

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(getApplicationContext());
        Notification notification = nBuilder
                //.setContentIntent(contentIntent)
                .setSmallIcon(icon)
                .setWhen(time)
                .setContentTitle(title)
                .setContentText(text).build();
        // Clear the notification when it is pressed
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        // Send the notification to the system.
        notificationManager.notify(NOTIFICATION, notification);

        // Stop the service when we are finished
        stopSelf();
    }

    /**
     * Show an alarm for a certain date when the alarm is called it will pop up a notification
     */
    public void setAlarm(Calendar c) {
        // This starts a new thread to set the alarm
        // You want to push off your tasks onto a new thread to free up the UI to carry on responding
        new AlarmTask(this, c).run();
    }

}
