package com.example.demo.service.impl;

import com.example.demo.dto.TokenResponse;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.InternalServerException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.IUserRepository;
import com.example.demo.request.CreateUserRequest;
import com.example.demo.request.LoginUserRequest;
import com.example.demo.service.IUserService;
import com.example.demo.util.JwtUltis;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
        try{
            UserEntity userEntity = userRepository.findByPhone(phone);
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity,userDto);
            return userDto;
        }
        catch (Exception e){
            return null;
        }

    }

    @Override
    public UserDto createUser(CreateUserRequest createUserRequest){
        UserEntity userEntity = UserMapper.toUser(createUserRequest);
        UserDto m_userDto = new UserDto();
        BeanUtils.copyProperties(userRepository.save(userEntity),m_userDto);
        return m_userDto;
    }

    @Override
    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception ex) {
            throw new InternalServerException("Error when delete user information");
        }
    }

    @Override
    public UserDto updateUser(CreateUserRequest createUserRequest, String fullname){
        String updateFullname = createUserRequest.getFullname();
        String updatePhone = createUserRequest.getPhone();
        String updateEmail = createUserRequest.getEmail();
        String updateAddress = createUserRequest.getAddress();
        String updatePassword = createUserRequest.getPassword();
        UserEntity userEntity = userRepository.findByFullname(fullname);
        if (null == userEntity) throw new RuntimeException("User not found");
        if(!updateFullname.isEmpty() &&!updatePhone.isEmpty() &&!updateEmail.isEmpty()&&!updateAddress.isEmpty()&&!updatePassword.isEmpty()) {
            userEntity.setEmail(updateEmail);
            userEntity.setAddress(updateAddress);
            userEntity.setFullname(updateFullname);
            userEntity.setPhone(updatePhone);
            userEntity.setPassword(updatePassword);
        }
        else
        {
            System.out.println("can't update");
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRepository.save(userEntity),userDto);
        return userDto;
    }

public TokenResponse login(LoginUserRequest loginRequest) {
        // Get user information
        UserEntity userEntity = userRepository.findByPhone(loginRequest.getPhone());
        if (userEntity == null) { 
            return new TokenResponse("Phone does not exist in the system", "", HttpStatus.NOT_FOUND);
        }

        // Check password
        boolean result = BCrypt.checkpw(loginRequest.getPassword(),userEntity.getPassword());
        System.out.println("result is: "+result);
        if (!result) {
            return new TokenResponse("Password wrong", "", HttpStatus.BAD_REQUEST);
        }

        String token = JwtUltis.generateToken(userEntity);
        return new TokenResponse("Login success", token, HttpStatus.OK);
    }

    @Override
    public boolean checkUser(CreateUserRequest createUserRequest){
        UserEntity userEntity = userRepository.findByFullname(createUserRequest.getFullname());
        if(userEntity == null) {
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public UserDto registerUser(CreateUserRequest createUserRequest){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(createUserRequest,userEntity);
        UserDto m_userDto = new UserDto();
        BeanUtils.copyProperties(userRepository.save(userEntity),m_userDto);
        return m_userDto;
    }
}
