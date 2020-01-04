package npUpdate.nopointer.model

import npUpdate.nopointer.R
import npUpdate.nopointer.util.GlobalContextProvider

/**
 * desc: UpdateInfo
 * time: 2019/6/18
 * @author teprinciple
 */
internal data class UpdateInfo(
        // 更新标题
        var updateTitle: CharSequence = GlobalContextProvider.getGlobalContext().getString(R.string.update_title),
        // 更新内容
        var updateContent: CharSequence = GlobalContextProvider.getGlobalContext().getString(R.string.update_content),
        // apk 下载地址
        var apkUrl: String = "",
        // 更新配置
        var config: UpdateConfig = UpdateConfig(),
        // ui配置
        var uiConfig: UiConfig = UiConfig(),
        //是否是使用默认的标题
        var useDefaultTitle: Boolean = false,
        //是否是使用默认的内容
        var useDefaultContent: Boolean = true
)