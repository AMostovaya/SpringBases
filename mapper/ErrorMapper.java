package ru.geekbrains.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.dto.ErrorDto;
import ru.geekbrains.rest.UserNotFoundException;

@Mapper
public interface ErrorMapper {

    ErrorMapper MAPPER = Mappers.getMapper(ErrorMapper.class);

    ErrorDto toDTO(UserNotFoundException notFoundException);

}
