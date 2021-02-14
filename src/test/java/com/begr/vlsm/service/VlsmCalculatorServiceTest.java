package com.begr.vlsm.service;

import com.begr.vlsm.model.request.NetworkDetailRequestModel;
import com.begr.vlsm.model.request.SubnetRequestModel;
import com.begr.vlsm.model.response.SubnetResponseModel;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class VlsmCalculatorServiceTest {



    NetworkDetailRequestModel request;
    List<SubnetResponseModel> theResponse;

    @BeforeEach
    void setup(){
        request = new NetworkDetailRequestModel();
        request.setCidr("192.168.1.0/23");

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

        request.setSubnets(subnetRequestModelList);
        theResponse = VlsmCalculatorService.calculate(request);

    }

    @Test
    void calculate() {
        assertThat(theResponse.size()).isEqualTo(6);
        assertThat(theResponse.get(0).getName()).isEqualTo("INFORMATIQUE");
        assertThat(theResponse.get(0).getLastUsableAddress()).isEqualTo("192.168.0.6");

        assertThat(theResponse.get(1).getName()).isEqualTo("COMPTABILITE");
        assertThat(theResponse.get(1).getLastUsableAddress()).isEqualTo("192.168.0.14");

        assertThat(theResponse.get(2).getName()).isEqualTo("RH");
        assertThat(theResponse.get(2).getLastUsableAddress()).isEqualTo("192.168.0.30");

        assertThat(theResponse.get(3).getName()).isEqualTo("COMMERCIAL");
        assertThat(theResponse.get(3).getLastUsableAddress()).isEqualTo("192.168.0.62");

        assertThat(theResponse.get(4).getName()).isEqualTo("OPERATIONNELS");
        assertThat(theResponse.get(4).getLastUsableAddress()).isEqualTo("192.168.0.190");

        assertThat(theResponse.get(5).getName()).isEqualTo("TELEPHONIE");
        assertThat(theResponse.get(5).getLastUsableAddress()).isEqualTo("192.168.1.190");

    }
}