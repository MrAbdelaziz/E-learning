package com.cteaching.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cteaching.auth.AuthGroup;
import com.cteaching.dto.UserDto;
import com.cteaching.model.User;
import com.cteaching.repositories.AuthGroupRepository;
import com.cteaching.repositories.UserRepository;

import java.time.LocalDate;

@Service
public class UserServiceImpl {

    private UserRepository userRepository;
    private AuthGroupRepository authGroupRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthGroupRepository authGroupRepository) {
        this.userRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
    }

    public void createUser(UserDto userDto) throws Exception {
        if (null != userRepository.findByUsername(userDto.getUsername())) {
            throw new Exception("Ya existe un usuario con el nombre " + userDto.getUsername());
        } else if (null != userRepository.findByEmail(userDto.getEmail())) {
            throw new Exception("Ya existe un usuario con el email " + userDto.getEmail());
        }
        String username = userDto.getUsername();
        String password = new BCryptPasswordEncoder(11).encode(userDto.getPassword());
        String nombre = userDto.getNom();
        String apellido = userDto.getPrenom();
        String email = userDto.getEmail();
        String imgurl = userDto.getImgurl();
        LocalDate fecha = LocalDate.now();
        User user = new User(username, password, nombre, apellido, email, imgurl, fecha);
        AuthGroup group = new AuthGroup();

        group.setUsername(userDto.getUsername());
        group.setAuthgroup("USER");

        userRepository.save(user);
        authGroupRepository.save(group);
    }

    public void update(User user) {
        User current = userRepository.findByUsername(user.getUsername());

        current.setNom(user.getNom());
        current.setPrenom(user.getPrenom());
        current.setEmail(user.getEmail());
        current.setImgurl(user.getImgurl());

        userRepository.save(current);
    }

    public void patch(User user) {
        User current = userRepository.findByUsername(user.getUsername());

        current.setDetail(user.getDetail());

        userRepository.save(current);
    }
}
