package com.begr.vlsm.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.begr.vlsm.model.request.NetworkDetailRequestModel;
import com.begr.vlsm.model.request.SubnetRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import ch.qos.logback.core.net.ObjectWriter;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testValidRequest() throws Exception {

        String url = "/";
        NetworkDetailRequestModel requestBody = new NetworkDetailRequestModel();
        requestBody.setCidr("192.168.1.0/23");

        List<SubnetRequestModel> subnetRequestModelList = new ArrayList<>();

        SubnetRequestModel subnet01 = new SubnetRequestModel();
        subnet01.setName("RH");
        subnet01.setNeededSize(10);
        subnetRequestModelList.add(subnet01);

        SubnetRequestModel subnet02 = new SubnetRequestModel();
        subnet02.setName("COMPTABILITE");
        subnet02.setNeededSize(6);
        subnetRequestModelList.add(subnet02);

        SubnetRequestModel subnet03 = new SubnetRequestModel();
        subnet03.setName("COMMERCIAL");
        subnet03.setNeededSize(24);
        subnetRequestModelList.add(subnet03);

        SubnetRequestModel subnet05 = new SubnetRequestModel();
        subnet05.setName("OPERATIONNELS");
        subnet05.setNeededSize(104);
        subnetRequestModelList.add(subnet05);

        SubnetRequestModel subnet06 = new SubnetRequestModel();
        subnet06.setName("INFORMATIQUE");
        subnet06.setNeededSize(4);
        subnetRequestModelList.add(subnet06);

        SubnetRequestModel subnet07 = new SubnetRequestModel();
        subnet07.setName("TELEPHONIE");
        subnet07.setNeededSize(148);
        subnetRequestModelList.add(subnet07);

        requestBody.setSubnets(subnetRequestModelList);

         mockMvc.perform(post("/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(requestBody)))
        .andExpect(status().isOk())
        .andReturn();

    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }  
    

}