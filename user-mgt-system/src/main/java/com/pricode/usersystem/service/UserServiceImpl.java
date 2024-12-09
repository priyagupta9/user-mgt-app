package com.pricode.usersystem.service;
import com.pricode.usersystem.entity.UserEntity;
import com.pricode.usersystem.model.User;
import com.pricode.usersystem.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user,userEntity);
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public List<User> getUsers(){
        List<UserEntity> userEntities = userRepository.findAll();

        List<User> users = userEntities
                .stream()
                .map(user -> new User(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmailId()
                )).collect(Collectors.toList());

        return users;
    }

    @Override
    public User getUserById(Long id){
        UserEntity userEntity = userRepository.findById(id).get();
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

    @Override
    public Boolean deleteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        userRepository.delete(userEntity);
        return true;
    }

    @Override
    public User updateUser(Long id, User user){
        UserEntity prevUserEntity = userRepository.findById(id).get();

        if(user.getFirstName() !=null && !user.getFirstName().isEmpty()){
            prevUserEntity.setFirstName(user.getFirstName());
        }
        if(user.getLastName() !=null && !user.getLastName().isEmpty()){
            prevUserEntity.setLastName(user.getLastName());
        }
        if(user.getEmailId() !=null && !user.getEmailId().isEmpty()){
            prevUserEntity.setEmailId(user.getEmailId());
        }

        userRepository.save(prevUserEntity);

        BeanUtils.copyProperties(prevUserEntity,user);

        return user;
    }
}
