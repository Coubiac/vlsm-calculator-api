package com.begr.vlsm.service;

import com.begr.vlsm.exceptions.VlsmCalculatorServiceException;
import com.begr.vlsm.model.request.NetworkDetailRequestModel;
import com.begr.vlsm.model.request.SubnetRequestModel;
import com.begr.vlsm.model.response.SubnetResponseModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class VlsmCalculatorService {

    /**
     * @param networkDetailRequestModel
     * @return a subnet list
     */
    public static List<SubnetResponseModel> calculate(NetworkDetailRequestModel networkDetailRequestModel) throws VlsmCalculatorServiceException{
        List<SubnetResponseModel> output = new ArrayList<>();
        Collections.sort(networkDetailRequestModel.getSubnets());
        Collections.reverse(networkDetailRequestModel.getSubnets());
        String majorNetwork = networkDetailRequestModel.getCidr();
        String[] parsedCidr = majorNetwork.split("/");
        int requestmask = Integer.parseInt(parsedCidr[1]);
        int majorNetworkAllocatedSize = findUsableHosts(requestmask);
        int globalAllocatedSize = 0;


        int currentIp = findFirstIp(majorNetwork);


        for (SubnetRequestModel requestSubnet : networkDetailRequestModel.getSubnets()
        ) {
   
            SubnetResponseModel responseModel = new SubnetResponseModel();
            responseModel.setName(requestSubnet.getName());
            responseModel.setAddress(convertIpToQuartet(currentIp));
            responseModel.setNeededSize(requestSubnet.getNeededSize());
            int mask = calcMask(requestSubnet.getNeededSize());
            responseModel.setMask("/" + mask);
            responseModel.setDecMask(toDecMask(mask));
            int allocatedSize = findUsableHosts(mask);
            globalAllocatedSize = globalAllocatedSize + allocatedSize;
            responseModel.setAllocatedSize(allocatedSize);
            responseModel.setBroadcast(convertIpToQuartet(currentIp + allocatedSize + 1));
            responseModel.setFirstUsableAddress(convertIpToQuartet(currentIp + 1));
            responseModel.setLastUsableAddress(convertIpToQuartet(currentIp + allocatedSize));
            output.add(responseModel);

            currentIp += allocatedSize + 2;
        }
        if(majorNetworkAllocatedSize < globalAllocatedSize){
            throw new VlsmCalculatorServiceException("The global Network is too small for containing those subnets");
        }

        return output;
    }


    /**
     * Merge IP address quartet into a single binary string.
     * <p/>
     * Example: <code>192.168.0.1</code> to <code>11000000101010000000000000000001</code>
     *
     * @param ipAddress IP address
     * @return A single integer that stores IP address binary string
     */
    private static int convertQuartetToBinaryString(String ipAddress) {
        String[] ip = ipAddress.split("\\.|/");

        int octet1 = Integer.parseInt(ip[0]);
        int octet2 = Integer.parseInt(ip[1]);
        int octet3 = Integer.parseInt(ip[2]);
        int octet4 = Integer.parseInt(ip[3]);

        int output = octet1;
        output = (output << 8) + octet2;
        output = (output << 8) + octet3;
        output = (output << 8) + octet4;

        return output;
    }

    /**
     * Separate IP address binary string into quartet.
     * <p/>
     * Example: <code>11000000101010000000000000000001</code> to <code>192.168.0.1</code>
     *
     * @param ipAddress IP address binary string
     * @return A string of IP address in the form of quartet
     */
    private static String convertIpToQuartet(int ipAddress) {
        int octet1 = (ipAddress >> 24) & 255;
        int octet2 = (ipAddress >> 16) & 255;
        int octet3 = (ipAddress >> 8) & 255;
        int octet4 = ipAddress & 255;

        return octet1 + "." + octet2 + "." + octet3 + "." + octet4;
    }

    /**
     * Find the first IP address for the specified network.
     *
     * @param majorNetwork Major network IP
     * @return The first IP address
     */
    private static int findFirstIp(String majorNetwork) {
        String[] ip = majorNetwork.split("/");
        int mask = Integer.parseInt(ip[1]); // parse CIDR mask
        int offset = Integer.SIZE - mask;
        int majorAddress = convertQuartetToBinaryString(majorNetwork);
        int firstIp = (majorAddress >> offset) << offset;
        return firstIp;
    }

    /**
     * Calculate mask and return it in CIDR notation.
     *
     * @param neededSize The size for subnet
     * @return Mask
     */
    private static int calcMask(int neededSize) {
        int highestBit = Integer.highestOneBit(neededSize + 1); // +1 because broadcast address
        int position = (int) (Math.log(highestBit) / Math.log(2));
        return Integer.SIZE - (position + 1);   // +1 because position starts with 0
    }

    /**
     * Find the total number of usable IP address/hosts.
     *
     * @param mask Mask
     * @return Total number of hosts
     */
    private static int findUsableHosts(int mask) {
        return (int) Math.pow(2, Integer.SIZE - mask) - 2;
    }

    /**
     * Convert mask from CIDR notation to quartet form.
     * <p/>
     * Example: <code>'/24'</code> to <code>'255.255.255.0'</code>
     *
     * @param mask Mask in CIDR notation
     * @return Mask in quartet form
     */
    private static String toDecMask(int mask) {
        if (mask == 0) {
            return "0.0.0.0";
        }
        int allOne = -1;    // '255.255.255.255'
        int shifted = allOne << (Integer.SIZE - mask);

        return convertIpToQuartet(shifted);
    }

}
