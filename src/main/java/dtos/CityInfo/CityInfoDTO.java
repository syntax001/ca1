package dtos.CityInfo;

import entities.CityInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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

    public CityInfoDTO(){ }

    public CityInfoDTO(CityInfo cityInfo){
        if(cityInfo.getZipCode() != null){
            this.id = cityInfo.getId();
            this.city =cityInfo.getCity();
            this.zipCode=cityInfo.getZipCode();
        }
    }


    //zipcode
    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    //city
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    //id
    public Integer getId(){return id;}

    public void setId(Integer id){this.id = id;}


    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        CityInfoDTO that = (CityInfoDTO) o;
        return Objects.equals(zipCode, that.getZipCode()) && Objects.equals(city, that.getCity()) && Objects.equals(id, that.getId());
    }


    @Override
    public int hashCode(){
        return Objects.hash(id,zipCode,city);
    }



}
