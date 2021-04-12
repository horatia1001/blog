package com.ht.blog.service;

import com.ht.blog.pojo.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {

    int addTag(Tag tag);

    void deleteTag(Long id);

    int updateTag(Tag tag);

    List<Tag> listTag();

    Tag getTagByName(String name);

    Tag getTagById(Long id);
}
