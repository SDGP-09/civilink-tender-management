package com.civilink.tender_management.services;

import com.civilink.tender_management.dtos.RequestCreateTender;
import com.civilink.tender_management.dtos.RequestCreateTenderCondition;
import com.civilink.tender_management.entities.Tender;
import com.civilink.tender_management.entities.TenderCondition;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface TenderService {
    void createTender(RequestCreateTender requestCreateTender, Authentication authentication);
    List<Tender> getAllTenders(Authentication authentication);
    Optional<Tender> getTenderById(String id,Authentication authentication);
    void updateTender(RequestCreateTender requestCreateTender, String tenderId,Authentication authentication);
    void deleteTender(String id,Authentication authentication);
    void addTenderCondition(String tenderId, RequestCreateTenderCondition requestCreateTenderCondition,Authentication authentication);
    void updateTenderCondition(String tenderId, String conditionId, RequestCreateTenderCondition requestCreateTenderCondition,Authentication authentication);
    void deleteTenderCondition(String tenderId, String conditionId,Authentication authentication);
    TenderCondition getTenderConditionById(String tenderId, String conditionId,Authentication authentication);
    List<TenderCondition> getAllTenderConditions(String tenderId,Authentication authentication);
}
