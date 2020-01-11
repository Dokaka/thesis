package com.example.demo.mapper;

import com.example.demo.entity.UserEntity;
import com.example.demo.request.CreateUserRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserMapper {
    public static UserEntity toUser(CreateUserRequest request){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(request.getEmail());
        userEntity.setPhone(request.getPhone());
        userEntity.setFullname(request.getFullname());
        userEntity.setAddress(request.getAddress());
        String hash = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt(12));
        userEntity.setPassword(hash);
        return userEntity;
    }
}
