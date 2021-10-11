package dtos;

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

    //additionalinfo getter/setter
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    //City getter/setter
    public CityInfoDTO getCityInfo() {
        return cityInfoDTO;
    }

    public void setCityInfo(CityInfoDTO cityInfo) {
        this.cityInfoDTO = cityInfo;
    }

    //Streetname getter/setter
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

}
