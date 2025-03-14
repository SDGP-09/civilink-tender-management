package com.civilink.tender_management.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.File;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TenderCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String companyId;
    private String title;
    private String description;
    private String document;

    @ManyToOne
    @JoinColumn(name = "tender_id", nullable = false)
    private Tender tender;

}
