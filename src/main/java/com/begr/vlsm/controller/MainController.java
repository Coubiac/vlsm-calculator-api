package com.begr.vlsm.controller;

import com.begr.vlsm.exceptions.FieldValidationException;
import com.begr.vlsm.model.request.NetworkDetailRequestModel;
import com.begr.vlsm.model.response.SubnetResponseModel;
import com.begr.vlsm.service.VlsmCalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@EnableWebMvc
public class MainController {

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @PostMapping(value = "/")
    public ResponseEntity<List<SubnetResponseModel>> calculate(@Valid @RequestBody NetworkDetailRequestModel networkDetailRequestModel, BindingResult bindingResult){
        logger.info("REQUETE: {}", networkDetailRequestModel);
        validateRequest(bindingResult);

        List<SubnetResponseModel> theSubnets = VlsmCalculatorService.calculate(networkDetailRequestModel);
        return ResponseEntity.ok(theSubnets);
    }

    protected void validateRequest(BindingResult result) {

        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
        
            throw new FieldValidationException(fieldErrors);
          }
        }
}
