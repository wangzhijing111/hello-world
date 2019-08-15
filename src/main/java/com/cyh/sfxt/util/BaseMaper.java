package com.cyh.sfxt.util;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface BaseMaper<T> extends Mapper<T>, MySqlMapper<T> {
}
