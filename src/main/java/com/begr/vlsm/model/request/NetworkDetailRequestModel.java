package com.begr.vlsm.model.request;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;


@Data
@Valid
public class NetworkDetailRequestModel implements Serializable {
  
    /**
     *
     */
    private static final long serialVersionUID = 5799896952687654927L;

    private static final String CIDR_PATTERN = "(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{0,3})(\\/)(0?[1-9]|[12][0-9]|3[02])";
    

    @Pattern(regexp=CIDR_PATTERN, message="Invalid CIDR")
    private String cidr;

    @Valid
    @NotNull
    @NotEmpty
    private List<SubnetRequestModel> subnets;

}
