package com.pokong.mwzl.data.bean.personal;

import java.io.Serializable;

/**
 * Created on 2018/11/7 9:55
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 更新信息响应实体类
 */
public class UpdateResponseBean implements Serializable {

    private String create_time;
    private String description;
    private String download_url;
    private int id;
    private String version_no;

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion_no() {
        return version_no;
    }

    public void setVersion_no(String version_no) {
        this.version_no = version_no;
    }

    @Override
    public String toString() {
        return "UpdateResponseBean{" +
                "create_time='" + create_time + '\'' +
                ", description='" + description + '\'' +
                ", download_url='" + download_url + '\'' +
                ", id=" + id +
                ", version_no='" + version_no + '\'' +
                '}';
    }
}
//        "create_time":"2019-01-04 16:18:41",
//        "description":"第一个版本1.1",
//        "download_url":"http://mytestspace.51vip.biz/upload/temp/e3b412777b3f40d18e224f3a9506c8ec.docx",
//        "id":2,
//        "version_no":"1.1"
