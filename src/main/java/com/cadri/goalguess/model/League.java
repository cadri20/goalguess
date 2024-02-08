package com.cadri.goalguess.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Data
@Entity
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    //Pattern of a image
    @Pattern(regexp = "^(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)$", message = "Invalid URL")
    private String logoUrl;

    @OneToMany
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "league_id")
    private List<Team> teams;

    @OneToMany
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "league_id")
    private List<Matchday> matchdays;
}
