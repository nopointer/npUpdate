package demo.nopointer.npUpdate.update;

import demo.nopointer.npUpdate.MainApplication;
import demo.nopointer.npUpdate.R;
import npUpdate.nopointer.constacne.UiType;
import npUpdate.nopointer.listener.Md5CheckResultListener;
import npUpdate.nopointer.listener.UpdateDownloadListener;
import npUpdate.nopointer.model.UiConfig;
import npUpdate.nopointer.model.UpdateConfig;
import npUpdate.nopointer.update.UpdateAppUtils;


public class AppUpdate {

    private String apkUrl = "http://118.24.148.250:8080/yk/update_signed.apk";
    private String updateTitle = "发现新版本V2.0.0";
    private String updateContent = "1、Kotlin重构版\n2、支持自定义UI\n3、增加md5校验\n4、更多功能等你探索";


    private AppUpdate() {
        // 启动应用后删除安装包
        UpdateAppUtils.getInstance().deleteInstalledApk();
        updateTitle = "";
        updateContent = MainApplication.getMainApplication().getResources().getString(R.string.update_content);
    }

    private static AppUpdate instance = new AppUpdate();

    public static AppUpdate getInstance() {
        return instance;

    }

    public void checkUpdate() {
        showNewVersion( );
    }


    private void showNewVersion() {
        UpdateConfig updateConfig = new UpdateConfig();
        updateConfig.setCheckWifi(true);
        updateConfig.setNeedCheckMd5(true);
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
                    public void onError( Throwable e) {

                    }
                })
                .update();
    }

    public interface CheckCallback {
        void onCheckFinish();
    }


}
