package com.begr.vlsm.model.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubnetResponseModel {
    public String name;
    public Integer neededSize;
    public Integer allocatedSize;
    public String address;
    public String mask;
    public String decMask;
    public String broadcast;
    public String firstUsableAddress;
    public String lastUsableAddress;
}
