package com.example.demo.service.impl;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.InternalServerException;
import com.example.demo.repository.IUserRepository;
import com.example.demo.request.CreateUserRequest;
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
    @Override
    public UserDto login(String phone, String password){
        UserEntity userEntity = userRepository.findByPhone(phone);
        UserDto userDto = new UserDto();
        if(userEntity.getPhone().equals(phone) && userEntity.getPassword().equals(password)){
            BeanUtils.copyProperties(userEntity,userDto);
            return userDto;
        }
        else
        {
            throw new RuntimeException("Phone or Password is not exactly");
        }

    }
    @Override
    public boolean checkUser(CreateUserRequest createUserRequest){
        UserEntity userEntity = userRepository.findByFullname(createUserRequest.getFullname());
        if(userEntity == null)
        {
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
