package demo.nopointer.npUpdate.update;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;

import demo.nopointer.npUpdate.MainApplication;
import demo.nopointer.npUpdate.R;
import npUpdate.nopointer.constacne.UiType;
import npUpdate.nopointer.listener.Md5CheckResultListener;
import npUpdate.nopointer.listener.UpdateDownloadListener;
import npUpdate.nopointer.model.UiConfig;
import npUpdate.nopointer.model.UpdateConfig;
import npUpdate.nopointer.update.UpdateAppUtils;


public class AppUpdate {

    //换成具体的项目的apk安装路径（本项目里面的路径不一定能保证成功）
    private String apkUrl = "https://github.com/nopointer/npUpdate/raw/master/app/demo/npUpdate.apk";
    private String updateTitle = "发现新版本V2.0.0";
    private String updateContent = "1、Kotlin重构版\n2、支持自定义UI\n3、增加md5校验\n4、更多功能等你探索";

    private static Context context = null;

    private AppUpdate(Context context) {
        // 启动应用后删除安装包
        UpdateAppUtils.getInstance().deleteInstalledApk();
        updateTitle = "1.0.1";
        updateContent = MainApplication.getMainApplication().getResources().getString(R.string.update_content);
        AppUpdate.context = context;
    }

    private static AppUpdate instance = null;

    public static AppUpdate getInstance(Context context) {
        synchronized (AppUpdate.class) {
            if (instance == null) {
                synchronized (AppUpdate.class) {
                    instance = new AppUpdate(context);
                }
            }
        }
        return instance;

    }

    public void checkUpdate() {
        showNewVersion();
    }


    @TargetApi(Build.VERSION_CODES.FROYO)
    private void showNewVersion() {
        UpdateConfig updateConfig = new UpdateConfig();
        updateConfig.setCheckWifi(true);
        updateConfig.setNeedCheckMd5(false);

        updateConfig.setApkSavePath(context.getExternalFilesDir(null) + "/npUpdate/update");
        updateConfig.setNotifyImgRes(R.mipmap.ic_launcher);

        UiConfig uiConfig = new UiConfig();
        uiConfig.setUiType(UiType.PLENTIFUL);

        UpdateAppUtils
                .getInstance()
                .apkUrl(apkUrl)
                .updateTitle(updateTitle)
                .updateContent(updateContent)
                .uiConfig(uiConfig)
                .updateConfig(updateConfig)
                .setMd5CheckResultListener(new Md5CheckResultListener() {
                    @Override
                    public void onResult(boolean result) {

                    }
                })
                .setUpdateDownloadListener(new UpdateDownloadListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onDownload(int progress) {

                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                })
                .update();
    }

    public interface CheckCallback {
        void onCheckFinish();
    }


}
