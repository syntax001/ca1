package dtos.Hobbies;

import dtos.Hobbies.HobbyDTO;
import dtos.Person.PersonDTO;
import entities.Hobby;
import entities.Person;

import java.util.ArrayList;
import java.util.List;

public class HobbiesDTO {
    List<HobbyDTO> all = new ArrayList<>();

    public HobbiesDTO(){}

    public HobbiesDTO(List<Hobby> hobbies) {
        hobbies.forEach((h) -> {
            all.add(new HobbyDTO(h));
        });
    }

    public int getSize(){
        int counter = 0;
        for(HobbyDTO p : all){
            counter ++;
        }
        return counter;
    }


}
