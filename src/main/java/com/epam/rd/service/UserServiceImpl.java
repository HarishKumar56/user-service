package com.epam.rd.service;

import com.epam.rd.dto.UserDto;
import com.epam.rd.entity.User;
import com.epam.rd.exception.DuplicateUserException;
import com.epam.rd.exception.UserNotFoundException;
import com.epam.rd.repository.UserDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<UserDto> getAllUsers() {

        List<User> allUsers = userDao.findAll();
        List<UserDto> allUserDto = new ArrayList<>();
        allUsers.forEach(user -> allUserDto.add(modelMapper.map(user , UserDto.class)));
        return allUserDto;
    }

    @Override
    public UserDto getUserByUserName(String userName) throws UserNotFoundException {
        Optional<User> user = userDao.findById(userName);
        if(user.isEmpty()){
            throw new UserNotFoundException("user with this username does not exist");
        }
        return modelMapper.map(user.get() , UserDto.class);
    }

    @Override
    public void saveUser(UserDto userDto) throws DuplicateUserException {
        User user = modelMapper.map(userDto ,User.class );
        if(userDao.existsById(user.getUserName())){
            throw new DuplicateUserException("User Already Exist");
        }
        userDao.save(user);
    }

    @Override
    public void updateUser(String userName, UserDto userDto) throws UserNotFoundException {
        UserDto user1 = getUserByUserName(userName);
        user1.setName(userDto.getName());
        user1.setPassword(userDto.getPassword());
        User user = modelMapper.map(user1 , User.class);
        userDao.save(user);
    }

    @Override
    public void deleteUser(String userName) throws UserNotFoundException {
        UserDto userDto = getUserByUserName(userName);
        User user = modelMapper.map(userDto , User.class);
        userDao.delete(user);
    }
}
