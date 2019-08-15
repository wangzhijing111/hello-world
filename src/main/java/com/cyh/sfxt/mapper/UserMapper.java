package com.cyh.sfxt.mapper;

import com.cyh.sfxt.entirty.Users;
import com.cyh.sfxt.util.BaseMaper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMaper<Users> {
    /**
     * 根据用户名和密码登陆用户
     * @param username
     * @param password
     * @return
     */
    @Select("select * from users t where t.username=#{username} and t.password=#{password}")
    public Users login(@Param("username") String username, @Param("password") String password);

    /**
     * 删除用户
     * @param users
     * @return
     */
    @Update("update users set state = #{users.state} where id=#{users.id}")
    public int deleteUSerList(@Param("users")Users users);
}
