package dtos;

import entities.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDTO {
    public Integer id;
    public String firstName;
    public String lastName;
    public String email;
    public List<PhoneDTO> phones;
    public AddressDTO addressDTO;
    public List<HobbyDTO> hobbyListDTO;

    public static List<PersonDTO> getPersonDtos(List<Person> person){
        List<PersonDTO> personDTO = new ArrayList();
        person.forEach(ps->personDTO.add(new PersonDTO(ps)));
        return personDTO;
    }

    public PersonDTO(){}

    public PersonDTO(Person person){
        this.id=person.getId();
        this.firstName= person.getFirstName();
        this.lastName = person.getLastName();
        this.email = person.getEmail();
        this.phones = PhoneDTO.getPhoneDtos(person.getPhones());
        this.addressDTO = new AddressDTO(person.getAddress());
        this.hobbyListDTO = HobbyDTO.getHobbyDtos(person.getHobbyList());
    }

    public PersonDTO(String email, String firstName, String lastName, List<HobbyDTO> hobbies, List<PhoneDTO> phones, AddressDTO address){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbyListDTO = hobbies;
        this.phones = phones;
        this.addressDTO = address;
    }


    //ID getter/setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //Firstname getter/setter
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //Lastname getter/setter
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //Email getter/setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Phone getter/setter
    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }

    //Address getter/setter
    public AddressDTO getAddress() {
        return addressDTO;
    }

    public void setAddress(AddressDTO address) {
        this.addressDTO = address;
    }

    //Hobby getter/setter
    public List<HobbyDTO> getHobby() {
        return hobbyListDTO;
    }

    public void setHobby(List<HobbyDTO> hobby) {
        this.hobbyListDTO = hobby;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phones=" + phones +
                ", addressDTO=" + addressDTO +
                ", hobbyListDTO=" + hobbyListDTO +
                '}';
    }
}



