package com.cyh.sfxt.util;

/**
 * @Description //TODO 格式化文件类
 * @Param
 * @return
 * @Author wangzhijing
 * @Date  2019-09-25
**/
public class FormatSize {
    /**
     * @Description //TODO 格式化文件大小并返回文件大小和单位
     * @Param [size]
     * @return java.lang.String[]
     * @Author wangzhijing
     * @Date  2019-09-25
    **/
    public static String[] getFormatSize(long size){
        double kiloByte=size/1024;
        String [] strings=new String[2];
        if(kiloByte < 1){
            strings[0]=String.valueOf(kiloByte);
            strings[1]="Byte(s)";
            return strings;
        }
        double megaByte = kiloByte/1024;
        if(megaByte < 1){
            strings[0]=String.valueOf(megaByte);
            strings[1]="KB";
            return strings;
        }
        double gigaByte = megaByte/1024;
        if(gigaByte < 1){
            strings[0]=String.valueOf(gigaByte);
            strings[1]="MB";
            return strings;
        }
        double teraBytes = gigaByte/1024;
        if(teraBytes < 1){
            strings[0]=String.valueOf(teraBytes);
            strings[1]="GB";
            return strings;
        }
        return strings;
    }
}
