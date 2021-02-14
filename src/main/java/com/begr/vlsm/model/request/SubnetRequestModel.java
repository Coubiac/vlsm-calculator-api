package com.begr.vlsm.model.request;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Component
@Valid
public class SubnetRequestModel implements Comparable<SubnetRequestModel> {
    @NotNull
    public String name;
    @NotNull
    public Integer neededSize;

    @Override
    public int compareTo(SubnetRequestModel subnet) {
        if(getNeededSize() == null || subnet.getNeededSize() == null){
            return 0;
        }
        return getNeededSize().compareTo(subnet.getNeededSize());
    }
}
