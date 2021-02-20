package com.ith.myblog.mapper;

import com.ith.myblog.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author muyun
 * @date 2020/4/20 - 20:04
 */

@Mapper
@Repository
public interface UserDao {

    @Select("select * from t_user where username=#{username} and password=#{password}")
    User findByNameAndPwd(String username, String password);

    @Select("select * from t_user where id=#{id}")
    User getUser(Long id);

}
