package com.ith.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.ith.myblog.domain.Type;
import com.ith.myblog.mapper.TypeDao;
import com.ith.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author muyun
 * @date 2020/4/21 - 9:57
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Override
    public List<Type> ListType(int page) {
        PageHelper.startPage(page, 5);
        return typeDao.ListType();
    }

    @Override
    public List<Type> ListTypeLimit(int limit) {
        return typeDao.ListTypeLimit(limit);
    }

    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    public void saveType(Type type) {
        typeDao.saveType(type);
    }

    @Override
    public void updateType(Type type) {
        typeDao.updateType(type);
    }

    @Override
    public void deleteType(Long id) {
        typeDao.deleteType(id);
    }

    @Override
    public List<Type> ListTypeAll() {
        return typeDao.ListType();
    }
}
