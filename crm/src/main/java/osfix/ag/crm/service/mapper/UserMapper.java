package osfix.ag.crm.service.mapper;

import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.user.User;
import osfix.ag.crm.service.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper implements EntityMapper<User, UserDTO> {
    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setRoles(dto.getRoles());
        return user;
    }

    @Override
    public UserDTO fromEntity(User entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setRoles(entity.getRoles());
        return dto;
    }

    @Override
    public List<User> fromDtoList(List<UserDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> toDtoList(List<User> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
