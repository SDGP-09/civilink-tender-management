package com.civilink.tender_management.controllers;

import com.civilink.tender_management.dtos.RequestCreateTender;
import com.civilink.tender_management.dtos.RequestCreateTenderCondition;
import com.civilink.tender_management.services.TenderService;
import com.civilink.tender_management.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tender")
@CrossOrigin(origins = "*")
public class Tender {

    @Autowired
    private TenderService tenderService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_GENARAL_USER') or hasRole('ROLE_GROUP_M1')")
    public ResponseEntity<StandardResponse> createTender(
            @RequestBody RequestCreateTender requestCreateTender,
            Authentication authentication
    ){

        tenderService.createTender(requestCreateTender,authentication);

        return ResponseEntity.ok(new StandardResponse(200,"Tender created","Success"));
    }

    @GetMapping("/get-all")
    public ResponseEntity<StandardResponse> getAllTenders(Authentication authentication){

        return ResponseEntity.ok(new StandardResponse(200,"Success",tenderService.getAllTenders(authentication)));
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<StandardResponse> getTenderById(
            @RequestParam String tenderId,
            Authentication authentication
    ){

        return ResponseEntity.ok(new StandardResponse(200,"Success",tenderService.getTenderById(tenderId,authentication)));
    }

    @PatchMapping("/update")
    public ResponseEntity<StandardResponse> updateTender(
            @RequestBody RequestCreateTender requestCreateTender,
            @RequestParam String tenderId,
            Authentication authentication
    ){
        tenderService.updateTender(requestCreateTender,tenderId,authentication);

        return ResponseEntity.ok(new StandardResponse(200,"Success","Updated"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<StandardResponse> deleteTender(
            @RequestParam String tenderId,
            Authentication authentication
    ){
        tenderService.deleteTender(tenderId,authentication);

        return ResponseEntity.ok(new StandardResponse(200,"Deleted",""));
    }

    @PostMapping("/create-condition")
    public ResponseEntity<StandardResponse> addTenderCondition(
            @RequestBody RequestCreateTenderCondition requestCreateTenderCondition,
            @RequestParam String tenderId,
            Authentication authentication
    ){
        tenderService.addTenderCondition(tenderId,requestCreateTenderCondition,authentication);
        return ResponseEntity.ok(new StandardResponse(200,"Created",""));
    }
    @PatchMapping("/update-condition")
    public ResponseEntity<StandardResponse> updateTenderCondition(
            @RequestBody RequestCreateTenderCondition requestCreateTenderCondition,
            @RequestParam String tenderId,
            @RequestParam String conditionId,
            Authentication authentication
    ){
        tenderService.updateTenderCondition(tenderId,conditionId,requestCreateTenderCondition,authentication);
        return ResponseEntity.ok(new StandardResponse(200,"Success",""));
    }

    @DeleteMapping("/remove-condition")
    public ResponseEntity<StandardResponse> deleteTenderCondition(
            @RequestParam String tenderId,
            @RequestParam String conditionId,
            Authentication authentication
    ){
        tenderService.deleteTenderCondition(tenderId,conditionId,authentication);
        return ResponseEntity.ok(new StandardResponse(200,"Success",""));
    }

    @GetMapping("/get-condition-by-id")
    public ResponseEntity<StandardResponse> getTenderConditionById(
            @RequestParam String tenderId,
            @RequestParam String conditionId,
            Authentication authentication
    ){
        tenderService.deleteTenderCondition(tenderId,conditionId,authentication);
        return ResponseEntity.ok(new StandardResponse(200,"Success",""));
    }

    @GetMapping("/get-all-conditions")
    public ResponseEntity<StandardResponse> getAllTenderConditions(
            @RequestParam String tenderId,
            Authentication authentication
    ){
        return ResponseEntity.ok(new StandardResponse(200,"Success",tenderService.getAllTenderConditions(tenderId,authentication)));
    }

}
