package ${package}.utility;


import ${package}.dto.UserDTO;
import ${package}.entity.UserEntity;

public class UserMapper {

    public UserDTO convertUserToUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(userEntity.getName());
        userDTO.setPassword(userEntity.getPassword());
        return userDTO;
    }

    public UserEntity convertUserDTOToUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.getName());
        userEntity.setPassword(userDTO.getPassword());
        return userEntity;
    }
}
