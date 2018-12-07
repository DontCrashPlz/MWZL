package com.pokong.bluetooth;

/**
 * Created on 2018/12/7 17:17
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 打印相关的行为
 */
public interface Printable {
    /**
     * 换行
     * \br
     */
    void nextLine();

    /**
     * 打印普通分割线
     * ------------
     */
    void printNormalDivideLine();

    /**
     * 打印带有文字的普通分割线
     * ----text----
     * @param text
     */
    void printNormalDevideLineWithText(String text);

    /**
     * 打印结束行
     * ＊＊＊＊#0001完＊＊＊＊
     * @param serialNum
     */
    void printEndDivideLine(String serialNum);

    /**
     * 打印正常字符(左对齐)
     * @param text
     */
    void printNormalTextLift(String text);

    /**
     *打印正常字符(居中)
     * @param text
     */
    void printNormalTextCenter(String text);

    /**
     *打印正常字符(右对齐)
     * @param text
     */
    void printNormalTextRight(String text);

    /**
     * 打印挑高字符(左对齐)
     * @param text
     */
    void printRiseTextLift(String text);

    /**
     *打印挑高字符(居中)
     * @param text
     */
    void printRiseTextCenter(String text);

    /**
     *打印挑高字符(右对齐)
     * @param text
     */
    void printRiseTextRight(String text);

    /**
     * 打印加大字符(左对齐)
     * @param text
     */
    void printLargeTextLift(String text);

    /**
     * 打印加大字符(居中)
     * @param text
     */
    void printLargeTextCenter(String text);

    /**
     * 打印加大字符(右对齐)
     * @param text
     */
    void printLargeTextRight(String text);
}
