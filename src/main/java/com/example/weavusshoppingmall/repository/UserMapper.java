package com.example.weavusshoppingmall.repository;

import com.example.weavusshoppingmall.dto.UserDto;
import com.example.weavusshoppingmall.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findById(String id);

    void saveUser(User user);

    User findByIdAndPassword(String id, String password);
}
