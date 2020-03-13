package osfix.ag.crm.service.mapper.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osfix.ag.crm.domain.manager.Client;
import osfix.ag.crm.repo.manager.CategoryRepo;
import osfix.ag.crm.service.dto.manager.ClientDTO;
import osfix.ag.crm.service.mapper.EntityMapper;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper implements EntityMapper<Client, ClientDTO> {
    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public Client toEntity(ClientDTO dto) {
        Client client = new Client();
        client.setCategory(categoryRepo.findById(dto.getCategoryId()).orElse(null));
        client.setCheck(dto.getCheck());
        client.setClientType(dto.getClientType());
        client.setComment(dto.getComment());
        client.setDiscount(dto.getDiscount());
        client.setId(dto.getId());
        client.setManager(dto.getManager());
        client.setName(dto.getName());
        client.setNextDateContact(new Date(dto.getNextDateContact()*1000));
        client.setPrice(dto.getPrice());
        client.setSite(dto.getSite());
        client.setStorageAddress(dto.getStorageAddress());
        client.setWorkCondition(dto.getWorkCondition());
        return client;
    }

    @Override
    public ClientDTO fromEntity(Client entity) {
        ClientDTO dto = new ClientDTO();
        dto.setCategoryId(entity.getCategory().getId());
        dto.setCheck(entity.getCheck());
        dto.setClientType(entity.getClientType());
        dto.setComment(entity.getComment());
        dto.setDiscount(entity.getDiscount());
        dto.setId(entity.getId());
        dto.setManager(entity.getManager());
        dto.setName(entity.getName());
        dto.setCategoryName(entity.getCategory().getName());
        dto.setNextDateContact(entity.getNextDateContact().getTime());
        dto.setPrice(entity.getPrice());
        dto.setSite(entity.getSite());
        dto.setStorageAddress(entity.getStorageAddress());
        dto.setWorkCondition(entity.getWorkCondition());
        return dto;
    }

    @Override
    public List<Client> fromDtoList(List<ClientDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientDTO> toDtoList(List<Client> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
