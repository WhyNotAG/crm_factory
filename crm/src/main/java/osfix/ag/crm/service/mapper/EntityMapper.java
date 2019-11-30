package osfix.ag.crm.service.mapper;

import java.util.List;

public interface EntityMapper<O, D> {
    O toEntity(D dto);
    D fromEntity(O entity);
    List<O> fromDtoList(List<D> list);
    List<D> toDtoList(List<O> list);
}
