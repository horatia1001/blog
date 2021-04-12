package com.ht.blog.dao;

import com.ht.blog.pojo.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper {

    int addTag(Tag tag);

    void deleteTag(Long id);

    int updateTag(Tag tag);

    List<Tag> listTag();

    Tag getTagByName(String name);

    Tag getTagById(Long id);
}
