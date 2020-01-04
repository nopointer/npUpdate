package demo.nopointer.npUpdate;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import demo.nopointer.npUpdate.update.AppUpdate;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 100);
        }

        findViewById(R.id.textBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUpdate.getInstance().checkUpdate();
            }
        });

//        startActivity(new Intent(MainActivity.this,BleActivity.class));
//        BleScanner.getInstance().registerScanListener(this);
//        BleScanner.getInstance().startScan();

//        mac = "20:17:98:F7:7F:E5";
//        OTAHelper.getInstance().startOTA(this, file2Path, mac, null, FirmType.HTX, new OTACallback() {
//            @Override
//            public void onFailure(int code, String message) {
//                npLog.e("onFailure==>" + code + "///" + message);
//            }
//
//            @Override
//            public void onSuccess() {
//                npLog.e("onSuccess==>");
//            }
//
//            @Override
//            public void onProgress(int progress) {
//                npLog.e("onProgress==>" + progress);
//            }
//        });
//        startService(new Intent(this, BgService.class));


//        BleDevice bleDevice = new BleDevice("W28", macForXinCore);
////
//        OTAHelper.getInstance().startOTA(this, pathForXinCore, bleDevice, FirmType.XC, new OTACallback() {
//
//
//            @Override
//            public void onFailure(int code, String message) {
//                npLog.e("onFailure===ota失败" + message);
//            }
//
//            @Override
//            public void onSuccess() {
//                npLog.e("onSuccess===ota成功");
//            }
//
//            @Override
//            public void onProgress(int progress) {
//                npLog.e("progress===>ota进度" + progress);
//            }
//        });

//        BlePhoneSysUtil.releaseAllScanClient();
//        BlePhoneSysUtil.refreshBleAppFromSystem(this,"lib.ycble.demo");
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


//        NpBleManager.getBleManager().connBleDevice(macForXinCore);

//        startService(new Intent(this, MainBackLiveService.class));
//        BleScanner.getBleScaner().startScan();

//        NpBleManager.getBleManager().connDevice(mac);


//        npLog.e("MTK mode==>" + WearableManager.getInstance().getWorkingMode());
//
//
//        Set<BluetoothDevice> tmpList = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
//
//
//        BluetoothDevice bluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice("40:3D:A0:01:62:61");
//        npLog.e("name===>" + bluetoothDevice.getName());
//
//        WearableManager.getInstance().setRemoteDevice(bluetoothDevice);
//        npLog.e("[wearable][onCreate], BTNoticationApplication WearableManager connect!///" + WearableManager.getInstance().getWorkingMode());
//        WearableManager.getInstance().connect();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        BleScanner.getInstance().stopScan();
//        PushAiderHelper.getAiderHelper().stop(this);
    }


//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        PushAiderHelper.getAiderHelper().registerCallAndSmsReceiver(this);
////        PushAiderHelper.getAiderHelper().startListeningForNotifications(this);
//        if (PushAiderHelper.getAiderHelper().isNotifyEnable(this)) {
//            PushAiderHelper.getAiderHelper().startListeningForNotifications(this);
//            textBtn.setText("已开启");
//
//        } else {
//            textBtn.setText("未开启");
//        }
//    }

    //    adb shell dumpsys activity | grep -i run
//    plugin.voip.ui.VideoActivity
//    plugin.voip.ui.VideoActivity

}
