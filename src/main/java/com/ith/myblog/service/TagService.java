package com.ith.myblog.service;

import com.ith.myblog.domain.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author muyun
 * @date 2020/4/25 - 15:38
 */
public interface TagService {

    List<Tag> ListTag(int page);

    List<Tag> ListTagLimit(int limit);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    void saveTag(Tag tag);

    void updateTag(Tag tag);

    void deleteTag(Long id);

    List<Tag> ListTagToAll();
}
