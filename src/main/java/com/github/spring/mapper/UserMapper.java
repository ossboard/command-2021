package com.github.spring.mapper;

import org.apache.commons.collections4.map.ListOrderedMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    ListOrderedMap getUserByName(String username);
    List<ListOrderedMap> getUserList();
}