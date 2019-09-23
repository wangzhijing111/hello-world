package com.cyh.sfxt.util;

import tk.mybatis.mapper.genid.GenId;

import java.util.UUID;

/**
 * @Description //TODO UUID实现类
 * @Param 
 * @return 
 * @Author wangzhijing
 * @Date  2019-09-23
**/
public class UUIdGenId implements GenId<String> {
    @Override
    public String genId(String s, String s1) {
        return UUID.randomUUID().toString().replace("-","");
    }
}
