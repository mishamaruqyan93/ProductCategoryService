package am.itspace.productcategoryservice.mapper;

import am.itspace.productcategoryservice.dto.UserDto;
import am.itspace.productcategoryservice.dto.UserRequestDto;
import am.itspace.productcategoryservice.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User map(UserRequestDto userResponseDto);

    UserDto map(User user);
}
