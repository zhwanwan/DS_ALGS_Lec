package com.lec.names;

/**
 * @author zhwanwan
 * @create 2019-08-15 16:07
 */
public class MetaLibItem {

    private int bh;//笔画
    private String findStr;
    private int wxIndex;//五行

    /**
     * @param bh      笔画
     * @param wxIndex 五行顺序
     * @param findStr //汉字列
     */
    public MetaLibItem(int bh, int wxIndex, String findStr) {
        this.bh = bh;
        this.wxIndex = wxIndex;
        this.findStr = findStr;
    }

    /**
     * @return 获得笔画
     */
    public int getBh() {
        return this.bh;
    }

    /**
     * 获得汉字列
     *
     * @return
     */
    public String getFindStr() {
        return this.findStr;
    }

    /**
     * 获得汉字是否存在列表中
     *
     * @return
     */
    public Boolean exists(String findStr) {
        if (this.findStr.indexOf(findStr) == -1) {
            return false;
        }
        return true;
    }

    /**
     * 获得汉字是否存在列表中
     *
     * @return
     */
    public Boolean exists(char findStr) {
        if (this.findStr.indexOf(findStr) == -1) {
            return false;
        }
        return true;
    }

    /**
     * 获得五行顺序
     *
     * @return
     */
    public int getWxIndex() {
        return this.wxIndex;
    }
}
