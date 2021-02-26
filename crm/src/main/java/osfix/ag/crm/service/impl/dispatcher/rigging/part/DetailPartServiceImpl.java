package osfix.ag.crm.service.impl.dispatcher.rigging.part;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.Log;
import osfix.ag.crm.domain.dispatcher.rigging.parts.DetailPart;
import osfix.ag.crm.repo.LogRepo;
import osfix.ag.crm.repo.rigging.part.DetailPartRepo;
import osfix.ag.crm.service.DetailPartService;

import java.util.List;

@Service
public class DetailPartServiceImpl implements DetailPartService {
    private DetailPartRepo detailPartRepo;
    private LogRepo logRepo;

    public DetailPartServiceImpl(DetailPartRepo detailPartRepo, LogRepo logRepo) {
        this.detailPartRepo = detailPartRepo;
        this.logRepo = logRepo;
    }

    @Override
    public List<DetailPart> findAll() { return detailPartRepo.findAll(); }

    @Override
    public DetailPart findById(Long id) { return detailPartRepo.findById(id).orElse(null); }

    @Override
    public DetailPart update(Long id, DetailPart detailPart) {
        DetailPart detailPartFromDb = detailPartRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(detailPart, detailPartFromDb, "id");
        loging("Изменение детали", "Изменение в оснастке №" + detailPartFromDb.getDetail().getId(), "riggingPart", detailPartFromDb.getId());
        return detailPartRepo.save(detailPartFromDb);
    }

    @Override
    public DetailPart save(DetailPart detailPart) {
        detailPartRepo.save(detailPart);
        loging("Добавление детали", "Добавление в оснастке №" + detailPart.getDetail().getId(), "riggingPart", detailPart.getId());
        return detailPart;
    }

    @Override
    public void delete(Long id) {
        detailPartRepo.deleteById(id);
        loging("Удаление детали", "Удаление детали", "riggingPart", id);
    }

    @Override
    public DetailPart changeColor(Long id, String color) {
        DetailPart detailPart = detailPartRepo.findById(id).orElse(null);
        detailPart.setColor(color);
        return detailPartRepo.save(detailPart);
    }
    public void loging(String actionShort, String action, String type, Long id) {
        Log log = new Log();
        log.setAction(actionShort);
        log.setDate(java.util.Calendar.getInstance().getTime());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.setAuthor(authentication.getName());
        log.setDescription( action + " детали №" + id + " в \"Запчасти\"" );
        log.setType(type);
        log.setElementId(id);
        logRepo.save(log);
    }
}
