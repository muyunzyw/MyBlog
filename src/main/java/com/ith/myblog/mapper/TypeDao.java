package com.ith.myblog.mapper;

import com.ith.myblog.domain.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author muyun
 * @date 2020/4/21 - 9:44
 */
@Mapper
@Repository
public interface TypeDao {

    @Select("select * from t_type")
    List<Type> ListType();

    List<Type> ListTypeLimit(int limit);

    @Select("select * from t_type where id=#{id}")
    Type getType(Long id);

    @Select("select * from t_type where name=#{name}")
    Type getTypeByName(String name);

    @Insert("insert into t_type (id,name) values(#{id},#{name})")
    void saveType(Type type);

    @Update("update t_type set name=#{name} where id=#{id}")
    void updateType(Type type);

    @Delete("delete from t_type where id=#{id}")
    void deleteType(Long id);
}
