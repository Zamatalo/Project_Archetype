package ${package}.services;

import ${package}.dto.UserDTO;
import ${package}.entity.UserEntity;
import ${package}.repo.UserRepository;
import ${package}.utility.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper = new UserMapper();
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Transactional(readOnly = true)
    public UserDTO getUserByName(String name) {
        Optional<UserEntity> userEntity = userRepository.findByName(name);
        if (userEntity.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        return userMapper.convertUserToUserDTO(userEntity.get());
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        if (!users.isEmpty()) {
            for (UserEntity user : users) {
                userDTOs.add(userMapper.convertUserToUserDTO(user));
            }
        } else {
            return new ArrayList<>();
        }
        return userDTOs;
    }
}
