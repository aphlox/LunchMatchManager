package june.second.lunchmatchmanager;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class ManagerService extends Service {

    private BroadcastReceiver joinApprovalReceiver;
    public static final String JOIN_APPROVAL = "june.second.lunchmatchmaker.action.ACTION_JOIN_APPROVAL";


    public ManagerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter();
        filter.addAction(JOIN_APPROVAL);
        joinApprovalReceiver = new JoinApprovalReceiver();
        registerReceiver(joinApprovalReceiver, filter);
//        Toast.makeText(getApplicationContext(), "ManagerService : onCreate", Toast.LENGTH_SHORT).show();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if( intent == null)
        {
            IntentFilter filter = new IntentFilter();
            filter.addAction(JOIN_APPROVAL);
            joinApprovalReceiver = new JoinApprovalReceiver();
            registerReceiver(joinApprovalReceiver, filter);
            Toast.makeText(getApplicationContext(), "ManagerService : onStartCommand - intent null", Toast.LENGTH_SHORT).show();

        }
//        Toast.makeText(getApplicationContext(), "ManagerService : onStartCommand", Toast.LENGTH_SHORT).show();

        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(joinApprovalReceiver);
    }



    @Override
    public IBinder onBind(Intent intent) {
        return  null;
    }
}
