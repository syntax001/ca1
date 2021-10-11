package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public String firstName;
    public String lastName;
    public String email;

    @OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST)
    private List<Phone> phones = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address address;

    @ManyToMany(mappedBy = "persons", cascade = CascadeType.PERSIST)
    private List<Hobby> hobbyList = new ArrayList<>();


    public Person(){
    }

    public Person(String email, String firstName, String lastName){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, String email, List<Phone> phones, Integer age, Address address, List<Hobby> hobbyList){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phones = phones;
        this.address = address;
        this.hobbyList = hobbyList;
    }


    //ID getter/setter
    public Integer getId(){
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //FirstName getter/setter
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //LastName getter/setter
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
    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public void addPhone(Phone phone){
        phones.add(phone);
    }

    //Address getter/setter
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    //Hobby getter/setter
    public List<Hobby> getHobbyList(){
        return hobbyList;
    }

    public void setHobbyList(List<Hobby> hobbyList){
        this.hobbyList = hobbyList;
    }

    public void addHobby(Hobby hobby){
        hobbyList.add(hobby);
    }
}
