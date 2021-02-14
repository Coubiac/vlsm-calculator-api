package com.begr.vlsm.model.request;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;


@Data
@Valid
@Component
public class NetworkDetailRequestModel implements Serializable {

    @Pattern(regexp="^([0-9]{1,3}\\.){3}[0-9]{1,3}(/([0-9]|[1-2][0-9]|3[0-2]))?$", message="Invalid CIDR")
    private String cidr;

    @Valid
    private List<SubnetRequestModel> subnets;

}
