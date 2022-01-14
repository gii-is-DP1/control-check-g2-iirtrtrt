package org.springframework.samples.petclinic.feeding;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
        return feedingRepository.save(p);
    }

}
