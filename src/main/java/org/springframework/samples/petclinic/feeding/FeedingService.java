package org.springframework.samples.petclinic.feeding;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedingService {
    private FeedingRepository feedingRepository;

    @Autowired
    public FeedingService(FeedingRepository feedingRepository) {
        this.feedingRepository = feedingRepository;
    }

    public List<Feeding> getAll() {
        return feedingRepository.findAll();
    }

    public List<FeedingType> getAllFeedingTypes() {
        return null;
    }

    public FeedingType getFeedingType(String typeName) {
        return feedingRepository.findFeedingType(typeName);
    }

    @Transactional(rollbackFor = UnfeasibleFeedingException.class)
    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
        if (p.getPet().getId() != p.getFeedingType().getPetType().getId()) {
            throw new UnfeasibleFeedingException();
        } else
            return feedingRepository.save(p);
    }
}
