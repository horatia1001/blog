package com.ht.blog.dao;

import com.ht.blog.pojo.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeMapper {

    /**
     * 添加分类
     * @param type
     * @return
     */
    int addType(Type type);

    /**
     * 删除一个分类
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
