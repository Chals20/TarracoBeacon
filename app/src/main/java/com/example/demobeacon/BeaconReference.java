package com.example.demobeacon;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;

import java.util.Collection;


public class BeaconReference extends Application implements BootstrapNotifier {
    private static final String TAG = "BeaconReferenceApp";
    private MonitoringActivity monitoringActivity = null;
    BeaconManager beaconManager;
    String beacon, title;
    Region regionJesus, regionJudith, regionCarlos;

    public void onCreate() {
        super.onCreate();
        beaconManager = BeaconManager.getInstanceForApplication(this);

        // By default the AndroidBeaconLibrary will only find AltBeacons.  If you wish to make it
        // find a different type of beacon, you must specify the byte layout for that beacon's
        // advertisement with a line like below.  The example shows how to find a beacon with the
        // same byte layout as AltBeacon but with a beaconTypeCode of 0xaabb.  To find the proper
        // layout expression for other beacon types, do a web search for "setBeaconLayout"
        // including the quotes.
        //
        beaconManager.getBeaconParsers().clear();
        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));

        BeaconManager.setDebug(true);


        // Uncomment the code below to use a foreground service to scan for beacons. This unlocks
        // the ability to continually scan for long periods of time in the background on Andorid 8+
        // in exchange for showing an icon at the top of the screen and a always-on notification to
        // communicate to users that your app is using resources in the background.
        //


        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle("Scanning for Beacons");
        Intent intent = new Intent(this, MonitoringActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
        );
        builder.setContentIntent(pendingIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notification Channel ID",
                    "My Notification Name", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("My Notification Channel Description");
            NotificationManager notificationManager = (NotificationManager) getSystemService(
                    Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channel.getId());
        }
        beaconManager.enableForegroundServiceScanning(builder.build(), 456);
        // For the above foreground scanning service to be useful, you need to disable
        // JobScheduler-based scans (used on Android 8+) and set a fast background scan
        // cycle that would otherwise be disallowed by the operating system.
        //
        beaconManager.setEnableScheduledScanJobs(false);
        beaconManager.setBackgroundBetweenScanPeriod(0);
        beaconManager.setBackgroundScanPeriod(1100);


        Log.d(TAG, "setting up background monitoring for beacons and power saving");
        // wake up the app when a beacon is seen
        regionJesus = new Region("beaconBackground1",
                Identifier.parse("b9407f30-f5f8-466e-aff9-25556b57fe6d"), Identifier.parse("10527"), Identifier.parse("32676"));
        regionJudith = new Region("beaconBackground2",
                Identifier.parse("b9407f30-f5f8-466e-aff9-25556b57fe6d"), Identifier.parse("6314"), Identifier.parse("20675"));
        regionCarlos = new Region("beaconBackground3",
                Identifier.parse("b9407f30-f5f8-466e-aff9-25556b57fe6d"), Identifier.parse("52176"), Identifier.parse("49395"));
        RegionBootstrap regionBootstrap1 = new RegionBootstrap(this, regionJesus);
        RegionBootstrap regionBootstrap2 = new RegionBootstrap(this, regionJudith);
        RegionBootstrap regionBootstrap3 = new RegionBootstrap(this, regionCarlos);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void didEnterRegion(Region region) {
        Log.d(TAG, "did enter region.");
        // Send a notification to the user whenever a Beacon
        sendNotification(region);

        if (monitoringActivity != null) {
            // If the Monitoring Activity is visible, we log info about the beacons we have
            // seen on its display
        }
    }

    @Override
    public void didExitRegion(Region region) {
    }

    @Override
    public void didDetermineStateForRegion(int state, Region region) {
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void sendNotification(Region region) {

        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Beacon Reference Notifications",
                    "Beacon Reference Notifications", NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(channel);
            builder = new Notification.Builder(this, channel.getId());
        }
        else {
            builder = new Notification.Builder(this);
            builder.setPriority(Notification.PRIORITY_HIGH);
        }

        Intent notification;
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        notification = new Intent(this, NotificationActivity.class);
        beacon = region.getId1().toString() + ", " + region.getId2().toString() + ", " + region.getId3().toString();
        switch (region.getUniqueId()) {
            case ("beaconBackground1"):
                title = "Balcó del Mediterrani";
                break;
            case ("beaconBackground2"):
                title = "Plaça Imperial Tarraco";
                break;
            case ("beaconBackground3"):
                title = "Circ Romà";
                break;
        }
        notification.putExtra("notificacio", beacon);
        stackBuilder.addNextIntent(notification);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle(title);
        builder.setContentText("Pulsa para ver los detalles");
        builder.setContentIntent(resultPendingIntent);
        notificationManager.notify(1, builder.build());
    }

    public void setMonitoringActivity(MonitoringActivity activity) {
        this.monitoringActivity = activity;
    }

}
