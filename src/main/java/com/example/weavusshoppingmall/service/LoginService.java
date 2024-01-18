package com.example.weavusshoppingmall.service;

import com.example.weavusshoppingmall.dto.UserDto;
import com.example.weavusshoppingmall.entity.User;
import com.example.weavusshoppingmall.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserMapper userMapper;



    public boolean signup(UserDto userDto){
        User user = new User(
                userDto.getId(),
                userDto.getPassword(),
                userDto.getUserEmail(),
                userDto.getCardNumber(),
                userDto.getCardCvc(),
                userDto.getUserName(),
                userDto.getUserEnName(),
                null,
                userDto.getIsActive()
        );

        try{
//            user.setId(userDto.getId());
//            userDto.setPassword(userDto.getPassword());
//            userDto.setUserEmail(userDto.getUserEmail());
//            userDto.setUserName(userDto.getUserName());
//            userDto.setUserEnName(userDto.getUserEnName());
//            userDto.setIsActive(userDto.getIsActive());
            userMapper.saveUser(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
