package com.ith.myblog.mapper;

import com.ith.myblog.domain.Tag;
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
public interface TagDao {

    @Select("select * from t_tag")
    List<Tag> ListTag();

    List<Tag> ListTagLimit(int limit);

    @Select("select * from t_tag where id=#{id}")
    Tag getTag(Long id);

    @Select("select * from t_tag where name=#{name}")
    Tag getTagByName(String name);

    @Insert("insert into t_tag (id,name) values(#{id},#{name})")
    void saveTag(Tag tag);

    @Update("update t_tag set name=#{name} where id=#{id}")
    void updateTag(Tag tag);

    @Delete("delete from t_tag where id=#{id}")
    void deleteTag(Long id);
}
