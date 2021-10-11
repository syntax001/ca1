package dtos;

import entities.Hobby;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HobbyDTO {
    private String name;
    private String category;
    private String type;


    public static List<HobbyDTO> getHobbyDtos(List<Hobby> hobbies){
        List<HobbyDTO> hbDtos = new ArrayList();
        hobbies.forEach(hobby->hbDtos.add(new HobbyDTO(hobby)));
        return hbDtos;
    }

    public static List<HobbyDTO> getFromList(List<Hobby> hobbies){
        return hobbies.stream()
                .map(hobby -> new HobbyDTO(hobby))
                .collect(Collectors.toList());
    }

    public HobbyDTO(){}

    public HobbyDTO(String name, String category, String type){
        this.name = name;
        this.category = category;
        this.type = type;
    }

    public HobbyDTO(Hobby hobby){
        if(hobby.getName() != null)
            this.name = hobby.getName();
            this.category = hobby.getCategory();
            this.type = hobby.getType();
    }

    //Name getter/setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Category getter/setter
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    //Type getter/setter
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
