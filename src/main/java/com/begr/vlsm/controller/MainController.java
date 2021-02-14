package com.begr.vlsm.controller;

import com.begr.vlsm.model.request.NetworkDetailRequestModel;
import com.begr.vlsm.model.response.SubnetResponseModel;
import com.begr.vlsm.service.VlsmCalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.*;
import java.util.List;
import java.util.Set;

@RestController
@Validated
public class MainController {

    @PostMapping(value = "/")
    public ResponseEntity<List<SubnetResponseModel>> calculate(@Valid @RequestBody NetworkDetailRequestModel networkDetailRequestModel){
        List<SubnetResponseModel> theSubnets = VlsmCalculatorService.calculate(networkDetailRequestModel);
        return ResponseEntity.ok(theSubnets);
    }
}
