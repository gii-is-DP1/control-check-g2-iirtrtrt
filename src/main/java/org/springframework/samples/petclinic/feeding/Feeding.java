package org.springframework.samples.petclinic.feeding;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "feedings")
public class Feeding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "start_date")
    @NotNull
    LocalDate startDate;

    @Range(min = 1)
    @NotNull
    @Column(name = "weeks_duration")
    double weeksDuration;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "pet_id")
    Pet pet;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "feedingtype_id")
    FeedingType feedingType;
}
