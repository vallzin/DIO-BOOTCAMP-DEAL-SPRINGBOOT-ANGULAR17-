package br.com.dio;

import br.com.dio.dto.UserDTO;
import br.com.dio.mapper.UserMapper;
import br.com.dio.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

public class Main {

    private static final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    public static void main(String[] args) {

        var model = new UserModel();
        model.setUsername("Mario");
        model.setCode(1);
        model.setBirthday(LocalDate.now().minusYears(42));
        System.out.println(mapper.toDTO(model));

        var dto = new UserDTO();
        dto.setName("Mario");
        dto.setId(2);
        dto.setBirthday(LocalDate.now().minusYears(42));
        System.out.println(mapper.toModel(dto));
        

    }
}
