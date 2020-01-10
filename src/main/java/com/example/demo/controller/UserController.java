package com.example.demo.controller;

import com.example.demo.dto.NotificationDto;
import com.example.demo.dto.TokenResponse;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.request.CreateUserRequest;
import com.example.demo.request.LoginUserRequest;
import com.example.demo.response.CreateUserResponse;
import com.example.demo.response.LoginUserResponse;
import com.example.demo.response.RegisterUserResponse;
import com.example.demo.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@Api(value = "Users APIs")
public class UserController {
    @Autowired
    private IUserService userService;

    @ApiOperation(value = "Get list of users", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 404, message="Not found"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @GetMapping()
    public ResponseEntity<?> getAllUsers(){
        List<UserDto> userDtoList = userService.getAllUsers();
        return ResponseEntity.ok(userDtoList);
    }
    @ApiOperation(value="Get a user by phone", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 404, message="Not found"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })

    @GetMapping("/phone/{phone}")
    public ResponseEntity<?> getUserByPhone(@PathVariable String phone){
        UserDto userDto = userService.getUserByPhone(phone);
        return ResponseEntity.ok(userDto);
    }
    @ApiOperation(value="Create a user", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 404, message="Not found"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest){
        UserDto userDTO = new UserDto();
        BeanUtils.copyProperties(createUserRequest,userDTO);
        CreateUserResponse createUserResponse = new CreateUserResponse();
        BeanUtils.copyProperties(userService.createUser(userDTO),createUserResponse);
        return ResponseEntity.ok(createUserResponse);
    }
    @ApiOperation(value="Delete a user", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 404, message="Not found"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Delete completely");
    }
    @ApiOperation(value="Update info of a user", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 404, message="Not found"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })

    @PutMapping("/{fullname}")
    public ResponseEntity<?> updateUser(@RequestBody CreateUserRequest createUserRequest, @PathVariable String fullname) {
        UserDto userDto = userService.updateUser(createUserRequest, fullname);
        return ResponseEntity.ok(userDto);
    }
    @ApiOperation(value="Login by user", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 404, message="Not found"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginUserRequest loginUserRequest) {
        LoginUserResponse loginUserResponse = new LoginUserResponse();

        TokenResponse result = userService.login(loginUserRequest);
        loginUserResponse.setTokenResponse(result);
        if (!result.getStatusCode().equals(HttpStatus.OK)) {

            return ResponseEntity.status(result.getStatusCode()).body(loginUserResponse);

        }
        else {
            UserDto userDto = userService.getUserByPhone(loginUserRequest.getPhone());
            System.out.println("values of userDto is: "+userDto);
            loginUserResponse.setAddress(userDto.getAddress());
            loginUserResponse.setEmail(userDto.getEmail());
            loginUserResponse.setPhone(userDto.getPhone());
            loginUserResponse.setFullname(userDto.getFullname());
            return ResponseEntity.ok(loginUserResponse);
        }

    }
    @ApiOperation(value="Register by user", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 404, message="Not found"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody CreateUserRequest createUserRequest){
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        if(userService.checkUser(createUserRequest)){
            //CreateUserResponse createUserResponse = new CreateUserResponse();

            userService.registerUser(createUserRequest);
            registerUserResponse.setMessageRegister("Register Succeed");
            registerUserResponse.setStatusRegister(true);
            //BeanUtils.copyProperties(userService.registerUser(createUserRequest),createUserResponse);
            return ResponseEntity.ok(registerUserResponse);
        }
        else {
//            throw new RuntimeException("User is existed");
            registerUserResponse.setMessageRegister("User is existed");
            registerUserResponse.setStatusRegister(false);
            return ResponseEntity.ok(registerUserResponse);

        }
    }
}
