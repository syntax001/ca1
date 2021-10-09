package dtos.Person;

import entities.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonsDTO {
    List<PersonDTO> all = new ArrayList();

    public PersonsDTO(){}

    public PersonsDTO(List<Person> persons){
        persons.forEach((p) -> {
            all.add(new PersonDTO(p));
        });
    }


    public int getSize(){
        int counter = 0;
        for(PersonDTO p : all){
            counter ++;
        }
        return counter;
    }



}
