package com.ht.blog.service.impl;

import com.ht.blog.dao.TypeMapper;
import com.ht.blog.pojo.Type;
import com.ht.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Transactional
    @Override
    public int addType(Type type) {
        return typeMapper.addType(type);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeMapper.deleteType(id);
    }

    @Transactional
    @Override
    public int updateType(Type type) {
        return typeMapper.updateType(type);
    }

    @Override
    public List<Type> listType() {
        return typeMapper.listType();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Override
    public Type getTypeById(Long id) {
        return typeMapper.getTypeById(id);
    }

    //----------------------------------------------------------------

}
