package org.springframework.samples.petclinic.feeding;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FeedingController {
    private static final String createOrUpdateFeedingForm = "feedings/createOrUpdateFeedingForm";

    private final FeedingService feedingService;
    private final PetService petService;

    @Autowired
    public FeedingController(FeedingService feedingService, PetService petService) {
        this.feedingService = feedingService;
        this.petService = petService;
    }

    @ModelAttribute("pets")
    public Collection<Pet> populatePetTypes() {
        return petService.getAllPets();
    }

    @ModelAttribute("feedingTypes")
    public Collection<FeedingType> populateFeedingTypes() {
        return feedingService.getAllFeedingTypes();
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
