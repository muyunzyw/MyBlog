package com.ith.myblog.service;

import com.ith.myblog.domain.Type;

import java.util.List;

/**
 * @author muyun
 * @date 2020/4/21 - 9:56
 */
public interface TypeService {

    List<Type> ListType(int page);

    List<Type> ListTypeLimit(int limit);

    Type getType(Long id);

    Type getTypeByName(String name);

    void saveType(Type type);

    void updateType(Type type);

    void deleteType(Long id);

    List<Type> ListTypeAll();

}
