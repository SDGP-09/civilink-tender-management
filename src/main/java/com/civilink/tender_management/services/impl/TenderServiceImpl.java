package com.civilink.tender_management.services.impl;

import com.civilink.tender_management.dtos.RequestCreateTender;
import com.civilink.tender_management.dtos.RequestCreateTenderCondition;
import com.civilink.tender_management.entities.Tender;
import com.civilink.tender_management.entities.TenderCondition;
import com.civilink.tender_management.repositories.TenderConditionRepository;
import com.civilink.tender_management.repositories.TenderRepository;
import com.civilink.tender_management.services.FileService;
import com.civilink.tender_management.services.TenderService;
import com.civilink.tender_management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TenderServiceImpl implements TenderService {
    @Autowired
    UserService userService;

    @Autowired
    TenderRepository tenderRepository;

    @Autowired
    FileService fileService;

    @Autowired
    TenderConditionRepository tenderConditionRepository;

    @Override
    public void createTender(RequestCreateTender requestCreateTender, Authentication authentication) {
        String userCompanyId = userService.getUserCompany(authentication);

        Tender tender = Tender.builder()
                .companyId(userCompanyId)
                .createdDate(new Date())
                .openDate(requestCreateTender.getOpenDate())
                .closingDate(requestCreateTender.getClosingDate())
                .tenderName(requestCreateTender.getTenderName())
                .estimaedCompletedDate(requestCreateTender.getEstimaedCompletedDate())
                .estimaedCompletedDate(requestCreateTender.getEstimaedCompletedDate())
                .jobStartDate(requestCreateTender.getJobStartDate())
                .estimatedValue(requestCreateTender.getEstimatedValue())
                .build();

        tenderRepository.save(tender);
    }

    @Override
    public List<Tender> getAllTenders(Authentication authentication) {
        String userCompanyId = userService.getUserCompany(authentication);
        return tenderRepository.findAllByCompanyId(userCompanyId);

    }

    @Override
    public Optional<Tender> getTenderById(String tenderId,Authentication authentication) {
        String userCompanyId = userService.getUserCompany(authentication);

        return tenderRepository.findByIdAndCompanyId(Integer.parseInt(tenderId),userCompanyId);


    }

    @Override
    public void updateTender(RequestCreateTender requestCreateTender,String tenderId,Authentication authentication) {
        String userCompanyId = userService.getUserCompany(authentication);

        Optional<Tender> optionalTender = tenderRepository.findByIdAndCompanyId(Integer.parseInt(tenderId),userCompanyId);

        if (optionalTender.isPresent()){
            Tender tender = optionalTender.get();
            tender.setTenderName(requestCreateTender.getTenderName());
            tender.setEstimaedCompletedDate(requestCreateTender.getEstimaedCompletedDate());
            tender.setClosingDate(requestCreateTender.getClosingDate());
            tender.setEstimatedValue(requestCreateTender.getEstimatedValue());
            tender.setJobStartDate(requestCreateTender.getJobStartDate());
            tenderRepository.save(tender);
        }
    }

    @Override
    public void deleteTender(String id,Authentication authentication) {
        String userCompanyId = userService.getUserCompany(authentication);

        tenderRepository.deleteByIdAndCompanyId(Integer.parseInt(id),userCompanyId);
    }

    @Override
    public void addTenderCondition(String tenderId, RequestCreateTenderCondition requestCreateTenderCondition,Authentication authentication) {

        String userCompanyId = userService.getUserCompany(authentication);

        Tender tender = getTenderById(tenderId,authentication).get();

        TenderCondition tenderCondition = TenderCondition.builder()
                .companyId(userCompanyId)
                .title(requestCreateTenderCondition.getTitle())
                .description(requestCreateTenderCondition.getDescription())
                .document(fileService.uploadFile(requestCreateTenderCondition.getDocument()))
                .tender(tender)
                .build();

        tender.getTenderConditions().add(tenderCondition);
        tenderConditionRepository.save(tenderCondition);

    }

    @Override
    public void updateTenderCondition(String tenderId, String conditionId, RequestCreateTenderCondition requestCreateTenderCondition,Authentication authentication) {
        String userCompanyId = userService.getUserCompany(authentication);

        TenderCondition tenderCondition = tenderConditionRepository.findByCompanyIdAndIdAndTenderId(userCompanyId,Integer.parseInt(conditionId),Integer.parseInt(tenderId)).get();

        tenderCondition.setTitle(requestCreateTenderCondition.getTitle());
        tenderCondition.setDescription(requestCreateTenderCondition.getDescription());

        if (requestCreateTenderCondition.getDocument() != null){
            tenderCondition.setDocument(fileService.uploadFile(requestCreateTenderCondition.getDocument()));
        }

        tenderConditionRepository.save(tenderCondition);
    }

    @Override
    public void deleteTenderCondition(String tenderId, String conditionId,Authentication authentication) {
        String userCompanyId = userService.getUserCompany(authentication);

        tenderConditionRepository.deleteByCompanyIdAndTenderIdAndId(userCompanyId,Integer.parseInt(tenderId),Integer.parseInt(conditionId));
    }

    @Override
    public TenderCondition getTenderConditionById(String tenderId, String conditionId,Authentication authentication) {
        String userCompanyId = userService.getUserCompany(authentication);

        return tenderConditionRepository.findByCompanyIdAndIdAndTenderId(userCompanyId,Integer.parseInt(tenderId),Integer.parseInt(conditionId)).get();

    }

    @Override
    public List<TenderCondition> getAllTenderConditions(String tenderId,Authentication authentication) {
        String userCompanyId = userService.getUserCompany(authentication);

        return tenderConditionRepository.findAllByCompanyIdAndTenderId(userCompanyId,Integer.parseInt(tenderId));

    }
}
