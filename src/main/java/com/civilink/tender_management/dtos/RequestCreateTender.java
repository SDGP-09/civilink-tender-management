package com.civilink.tender_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestCreateTender {
    private String companyId;
    private Date createdDate;
    private Date openDate;
    private Date closingDate;
    private String tenderName;
    private Date jobStartDate;
    private Date estimaedCompletedDate;
    private String estimatedValue;

}
