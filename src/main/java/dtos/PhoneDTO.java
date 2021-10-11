package dtos;

import entities.Phone;
import java.util.ArrayList;
import java.util.List;

public class PhoneDTO {
    private Integer id;
    private String number;


    public static List<PhoneDTO> getPhoneDtos(List<Phone> ph){
        List<PhoneDTO> phoneDtos = new ArrayList();
        ph.forEach(phone->phoneDtos.add(new PhoneDTO(phone)));
        return phoneDtos;
    }

    public PhoneDTO (){}

    public PhoneDTO(Phone phone){
        if(phone.getId()!= null){
            this.number = phone.getNumber();
        }
    }

    //ID getter/setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //Number getter/setter
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
