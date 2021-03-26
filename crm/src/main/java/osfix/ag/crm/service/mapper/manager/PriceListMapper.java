package osfix.ag.crm.service.mapper.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.manager.Client;
import osfix.ag.crm.domain.manager.Prices;
import osfix.ag.crm.repo.manager.ClientRepo;
import osfix.ag.crm.service.dto.manager.PriceListDTO;
import osfix.ag.crm.service.mapper.EntityMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceListMapper implements EntityMapper<Prices, PriceListDTO> {

    @Autowired
    ClientRepo clientRepo;

    @Override
    public Prices toEntity(PriceListDTO dto) {
        Prices prices = new Prices();
        prices.setId(dto.getId());
        prices.setUri(dto.getUri());
        List<Client> clients = new ArrayList<>();
        for (Long clientId : dto.getClientId()) {
            clients.add(clientRepo.findById(clientId).orElse(null));
        }
        prices.setClients(clients);
        return prices;
    }

    @Override
    public PriceListDTO fromEntity(Prices entity) {
        PriceListDTO dto = new PriceListDTO();
        dto.setId(entity.getId());
        List<Long> clientsId = new ArrayList<>();
        for(Client client : entity.getClients()) {
           clientsId.add(client.getId());
        }
        dto.setClientId(clientsId);
        return dto;
    }

    @Override
    public List<Prices> fromDtoList(List<PriceListDTO> list) {
        return list.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PriceListDTO> toDtoList(List<Prices> list) {
        return list.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
