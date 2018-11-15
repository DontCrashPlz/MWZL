package com.pokong.library.util;

/**
 * Created on 2018/11/7 9:42
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 数据加密工具类，用于对某些敏感数据进行加密
 */
public class EncryptUtils {

    /**
     * 对注册密码进行加密
     * @param regPass
     * @return
     */
    public static String enctyptRegisterPass(String regPass){
        //todo 按照某种规则对注册密码进行加密后上传服务器
        return regPass;
    }

    /**
     * 对登录密码进行加密
     * @param loginPass
     * @return
     */
    public static String enctyptLoginPass(String loginPass){
        //todo 按照某种规则对登录密码进行加密后上传服务器
        return loginPass;
    }

}
