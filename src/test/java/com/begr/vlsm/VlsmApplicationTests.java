package com.begr.vlsm;

import com.begr.vlsm.controller.MainController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class VlsmApplicationTests {

    @Autowired
    MainController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
