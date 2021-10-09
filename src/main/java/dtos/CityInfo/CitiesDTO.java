package dtos.CityInfo;

import dtos.Person.PersonDTO;
import entities.CityInfo;
import entities.Person;

import java.util.ArrayList;
import java.util.List;

public class CitiesDTO {
    List<CityInfoDTO> all = new ArrayList();

    public CitiesDTO(){}

    public CitiesDTO(List<CityInfo> cities){
        cities.forEach((c) -> {
            all.add(new CityInfoDTO(c));
        });
    }


    public int getSize(){
        int counter = 0;
        for(CityInfoDTO p : all){
            counter ++;
        }
        return counter;
    }
}
