package com.civilink.tender_management.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "tender")
public class Tender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String companyId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date openDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date closingDate;

    private String tenderName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date jobStartDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date estimaedCompletedDate;

    private String estimatedValue;

    @OneToMany(mappedBy = "tender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TenderCondition> tenderConditions = new ArrayList<>();

}
