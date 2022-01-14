package org.springframework.samples.petclinic.feeding;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FeedingController {
    private static final String createOrUpdateFeedingForm = "feedings/createOrUpdateFeedingForm";

    private final FeedingService feedingService;

    @Autowired
    public FeedingController(FeedingService feedingService) {
        this.feedingService = feedingService;
    }

    @GetMapping(value = "/feeding/create")
    public String getFeedingCreate(ModelMap model) {
        Feeding feeding = new Feeding();
        model.put("feeding", feeding);
        return createOrUpdateFeedingForm;
    }

    @PostMapping(value = "/feeding/create")
    public String postFeedingCreate(@Valid Feeding feeding, BindingResult result,
            ModelMap model) {
        if (result.hasErrors()) {
            model.put("feeding", feeding);
            return createOrUpdateFeedingForm;
        } else {
            try {
                feedingService.save(feeding);
            } catch (Exception ex) {
                return createOrUpdateFeedingForm;
            }
            return "redirect:/welcome";
        }
    }
}
