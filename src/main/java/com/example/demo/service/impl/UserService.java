package com.example.demo.service.impl;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers(){
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserDto> userDtoList = new CopyOnWriteArrayList<>();
        for(UserEntity userEntity : userEntityList){
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity,userDto);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
    @Override
    public UserDto getUserByPhone(String phone){
        UserEntity userEntity = userRepository.findByPhone(phone);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity,userDto);
        return userDto;
    }
    @Override
    public UserDto createUser(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto,userEntity);
        UserDto m_userDto = new UserDto();
        BeanUtils.copyProperties(userRepository.save(userEntity),m_userDto);
        return m_userDto;
    }
}
