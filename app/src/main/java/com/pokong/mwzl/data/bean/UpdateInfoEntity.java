package com.pokong.mwzl.data.bean;

import java.io.Serializable;

/**
 * 更新信息实体
 * Created by Zheng on 2016/8/26.
 */
public class UpdateInfoEntity implements Serializable {

    private String version = "";//新的版本号
    private String url = "";//新的安装包下载地址
    private String description = "";//新版本描述

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UpdateInfoEntity{" +
                "version='" + version + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

//<android-app>
//<version>1.0</version>
//<description>测试版本</description>
//<url>
//http://192.168.1.62:8080/default/portal/appdownload/android-app/yicr-soa.apk
//</url>
//</android-app>