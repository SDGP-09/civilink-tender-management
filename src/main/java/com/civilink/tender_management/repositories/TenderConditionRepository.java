package com.civilink.tender_management.repositories;

import com.civilink.tender_management.entities.TenderCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TenderConditionRepository extends JpaRepository<TenderCondition,Integer> {

    Optional<TenderCondition> findByCompanyIdAndIdAndTenderId(String userCompanyId, int i, int i1);


    void deleteByCompanyIdAndTenderIdAndId(String userCompanyId, int i, int i1);


    List<TenderCondition> findAllByCompanyIdAndTenderId(String userCompanyId, int i);
}
