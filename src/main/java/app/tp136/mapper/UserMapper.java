package app.tp136.mapper;

import app.tp136.config.MapperConfig;
import app.tp136.dto.UserDto;
import app.tp136.dto.request.UserRegisterRequestDto;
import app.tp136.dto.request.UserUpdateProfileRequestDto;
import app.tp136.dto.responce.UserUpdateResponseDto;
import app.tp136.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    User toUser(UserRegisterRequestDto dto);

    UserDto toUserDto(User user);

    UserUpdateResponseDto toUpdateDto(User user);

    void updateUser(UserUpdateProfileRequestDto dto, @MappingTarget User user);
}
