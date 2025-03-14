package com.civilink.tender_management.repositories;

import com.civilink.tender_management.entities.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TenderRepository extends JpaRepository<Tender,Integer> {

    @Query(nativeQuery = true, value = "SELECT *FROM tender WHERE company_id = ?1")
    List<Tender> findAllByCompanyId(String userCompanyId);

    Optional<Tender> findByIdAndCompanyId(int tenderId, String userCompanyId);

    void deleteByIdAndCompanyId(int id, String companyId);
}
