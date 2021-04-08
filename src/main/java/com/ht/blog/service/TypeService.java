package com.ht.blog.service;



import com.ht.blog.pojo.Type;

import java.util.List;

public interface TypeService {

    /**
     * 增加分类
     * @param type
     * @return
     */
    int addType(Type type);

    /**
     * 删除分类
     * @param id
     */
    void deleteType(Long id);

    /**
     * 编辑分类
     * @param type
     * @return
     */
    int updateType(Type type);

    /**
     * 查询所有分类
     * @return
     */
    List<Type> listType();
}
