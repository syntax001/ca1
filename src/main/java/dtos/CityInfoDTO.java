package dtos;

import entities.CityInfo;
import java.util.ArrayList;
import java.util.List;

public class CityInfoDTO {
    private Integer id;
    private Integer zipCode;
    private String city;


    public static List<CityInfoDTO> getCityDtos(List<CityInfo> CI){
        List<CityInfoDTO> CIdtos = new ArrayList();
        CI.forEach(cityI->CIdtos.add(new CityInfoDTO(cityI)));
        return CIdtos;
    }

    public static void getCityInfo(){};

    public CityInfoDTO(){}

    public CityInfoDTO(CityInfo cityInfo){
        if(cityInfo.getZipCode() != null){
            this.id = cityInfo.getId();
            this.city =cityInfo.getCity();
            this.zipCode=cityInfo.getZipCode();
        }
    }


    //Zipcode getter/setter
    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    //City getter/setter
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    //ID getter/setter
    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

}
