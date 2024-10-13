package com.example.BookMyShowCaseStudy.Controllers;

import Dtos.ResponseStatus;
import Dtos.SignUpRequestDto;
import Dtos.SignUpResponseDto;
import com.example.BookMyShowCaseStudy.Models.User;
import com.example.BookMyShowCaseStudy.Services.UserService;

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {
        SignUpResponseDto responseDto = new SignUpResponseDto();
        User user;

        try {
            user = userService.signUp(requestDto.getEmail(), requestDto.getPassword());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setUserId(user.getId());
        } catch (Exception ex) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }
}
