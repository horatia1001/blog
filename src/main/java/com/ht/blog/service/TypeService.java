package com.ht.blog.service;

import com.ht.blog.pojo.Type;

import java.util.List;

public interface TypeService {

    int addType(Type type);

    void deleteType(Long id);

    int updateType(Type type);

    List<Type> listType();

    Type getTypeByName(String name);

    Type getTypeById(Long id);

    //------------------------------

}
