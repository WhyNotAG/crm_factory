package osfix.ag.crm.service.impl.feedback;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import osfix.ag.crm.domain.feedback.Discussion;
import osfix.ag.crm.repo.feedback.DiscussionRepo;
import osfix.ag.crm.service.DiscussionService;

import java.util.List;

@Service
public class DiscussionServiceImpl implements DiscussionService {
    DiscussionRepo discussionRepo;

    public DiscussionServiceImpl(DiscussionRepo discussionRepo) {
        this.discussionRepo = discussionRepo;
    }

    @Override
    public List<Discussion> findAll() {
        return discussionRepo.findAll();
    }

    @Override
    public Discussion findById(Long id) {
        return discussionRepo.findById(id).orElse(null);
    }

    @Override
    public Discussion update(Long id, Discussion discussion) {
        Discussion discussionFromDb = discussionRepo.findById(id).orElse(null);
        BeanUtils.copyProperties(discussion, discussionFromDb, "id");
        return discussionRepo.save(discussionFromDb);
    }

    @Override
    public Discussion save(Discussion discussion) {
        return discussionRepo.save(discussion);
    }

    @Override
    public void delete(Long id) {
        discussionRepo.deleteById(id);
    }
}
