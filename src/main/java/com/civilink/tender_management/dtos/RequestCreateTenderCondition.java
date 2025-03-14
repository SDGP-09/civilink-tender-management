package com.civilink.tender_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestCreateTenderCondition {
    private String title;
    private String description;
    private File document;
}
