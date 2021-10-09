package dtos;

import dtos.CityInfo.CityInfoDTO;
import entities.Address;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AddressDTO {

    private CityInfoDTO cityInfoDTO;
    private String streetName;
    private String additionalInfo;

    public static List<AddressDTO> getAddressDtos(List<Address> adds){
        List<AddressDTO> addressdtos = new ArrayList();
        adds.forEach(add->addressdtos.add(new AddressDTO(add)));
        return addressdtos;
    }

    public AddressDTO(){}

    public AddressDTO(Address address){
        this.streetName = address.getStreetName();
        this.additionalInfo = address.getAdditionalInfo();
        this.cityInfoDTO = new CityInfoDTO(address.getCityInfo());
    }

    public AddressDTO(String streetName, String additionalInfo, CityInfoDTO cityInfoDTO){
        this.streetName = streetName;
        this.additionalInfo = additionalInfo;
        this.cityInfoDTO = cityInfoDTO;
    }

/*
    public static AddressDTO getFromList(Address address){
        return address.stream();
                .map(address -> new AddressDTO(address))
                .collect(Collectors.toList());
    }
  */


    //additional info
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    //city
    public CityInfoDTO getCityInfo() {
        return cityInfoDTO;
    }

    public void setCityInfo(CityInfoDTO cityInfo) {
        this.cityInfoDTO = cityInfo;
    }

    //streetname
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }


    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        AddressDTO that = (AddressDTO) o;
        return Objects.equals(streetName, that.streetName)&& Objects.equals(additionalInfo, that.additionalInfo) && Objects.equals(cityInfoDTO, that.cityInfoDTO);
    }


    @Override
    public int hashCode(){
        return Objects.hash(streetName, additionalInfo, cityInfoDTO);
    }




}
