package npUpdate.nopointer.update

import npUpdate.nopointer.extension.log
import npUpdate.nopointer.extension.no
import npUpdate.nopointer.extension.yes
import npUpdate.nopointer.listener.Md5CheckResultListener
import npUpdate.nopointer.listener.OnBtnClickListener
import npUpdate.nopointer.listener.OnInitUiListener
import npUpdate.nopointer.listener.UpdateDownloadListener
import npUpdate.nopointer.model.UiConfig
import npUpdate.nopointer.model.UpdateConfig
import npUpdate.nopointer.model.UpdateInfo
import npUpdate.nopointer.ui.UpdateAppActivity
import npUpdate.nopointer.util.GlobalContextProvider
import npUpdate.nopointer.util.SPUtil
import npUpdate.nopointer.util.Utils


/**
 * Created by Teprinciple on 2016/11/15.
 */
object UpdateAppUtils {

    init {
        GlobalContextProvider.getGlobalContext()
    }

    // 更新信息对象
    internal val updateInfo = UpdateInfo()

    // 下载监听
    internal var downloadListener: UpdateDownloadListener? = null

    // md5校验结果回调
    internal var md5CheckResultListener: Md5CheckResultListener? = null

    // 初始化更新弹窗UI回调
    internal var onInitUiListener: OnInitUiListener? = null

    // "暂不更新"按钮点击事件
    internal var onCancelBtnClickListener: OnBtnClickListener? = null

    // "立即更新"按钮点击事件
    internal var onUpdateBtnClickListener: OnBtnClickListener? = null

    /**
     * 设置apk下载地址
     */
    fun apkUrl(apkUrl: String): UpdateAppUtils {
        updateInfo.apkUrl = apkUrl
        return this
    }

    /**
     * 设置更新标题
     */
    fun updateTitle(title: CharSequence): UpdateAppUtils {
        updateInfo.updateTitle = title
        return this
    }

    /**
     * 设置更新内容
     */
    fun updateContent(content: CharSequence): UpdateAppUtils {
        updateInfo.updateContent = content
        return this
    }

    /**
     * 设置更新配置
     */
    fun updateConfig(config: UpdateConfig): UpdateAppUtils {
        updateInfo.config = config
        return this
    }

    /**
     * 设置UI配置
     */
    fun uiConfig(uiConfig: UiConfig): UpdateAppUtils {
        updateInfo.uiConfig = uiConfig
        return this
    }

    /**
     * 设置下载监听
     */
    fun setUpdateDownloadListener(listener: UpdateDownloadListener?): UpdateAppUtils {
        downloadListener = listener
        return this
    }

    /**
     * 设置md5校验结果监听
     */
    fun setMd5CheckResultListener(listener: Md5CheckResultListener?): UpdateAppUtils {
        md5CheckResultListener = listener
        return this
    }

    /**
     * 设置初始化UI监听
     */
    fun setOnInitUiListener(listener: OnInitUiListener?): UpdateAppUtils {
        onInitUiListener = listener
        return this
    }

    /**
     * 设置 “暂不更新” 按钮点击事件
     */
    fun setCancelBtnClickListener(listener: OnBtnClickListener?): UpdateAppUtils {
        onCancelBtnClickListener = listener
        return this
    }

    /**
     * 设置 “立即更新” 按钮点击事件
     */
    fun setUpdateBtnClickListener(listener: OnBtnClickListener?): UpdateAppUtils {
        onUpdateBtnClickListener = listener
        return this
    }

    /**
     * 检查更新
     */
    fun update() {
        val keyName = GlobalContextProvider.getGlobalContext().packageName + updateInfo.config.serverVersionName
        // 设置每次显示，设置本次显示及强制更新 每次都显示弹窗
        (updateInfo.config.alwaysShow || updateInfo.config.thisTimeShow || updateInfo.config.force).yes {
            UpdateAppActivity.launch()
        }.no {
            val hasShow = SPUtil.getBoolean(keyName, false)
            (hasShow).no { UpdateAppActivity.launch() }
        }
        SPUtil.putBase(keyName, true)
    }

    /**
     * 删除已安装 apk
     */
    fun deleteInstalledApk() {
        val apkPath = SPUtil.getString(DownloadAppUtils.KEY_OF_SP_APK_PATH, "")
        val appVersionCode = Utils.getAPPVersionCode()
        val apkVersionCode = Utils.getApkVersionCode(apkPath)
        log("appVersionCode:$appVersionCode")
        log("apkVersionCode:$apkVersionCode")
        (apkPath.isNotEmpty() && appVersionCode == apkVersionCode && apkVersionCode > 0).yes {
            Utils.deleteFile(apkPath)
        }
    }

    /**
     * 获取单例对象
     */
    @JvmStatic
    fun getInstance() = this
}