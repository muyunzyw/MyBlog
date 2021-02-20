package com.ith.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.ith.myblog.domain.Tag;
import com.ith.myblog.mapper.TagDao;
import com.ith.myblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author muyun
 * @date 2020/4/25 - 15:39
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public List<Tag> ListTag(int page) {
        PageHelper.startPage(page, 5);
        return tagDao.ListTag();
    }

    @Override
    public List<Tag> ListTagLimit(int limit) {
        return tagDao.ListTagLimit(limit);
    }

    @Override
    public Tag getTag(Long id) {
        return tagDao.getTag(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    @Override
    public void saveTag(Tag tag) {
        tagDao.saveTag(tag);
    }

    @Override
    public void updateTag(Tag tag) {
        tagDao.updateTag(tag);
    }

    @Override
    public void deleteTag(Long id) {
        tagDao.deleteTag(id);
    }

    @Override
    public List<Tag> ListTagToAll() {
        return tagDao.ListTag();
    }
}
