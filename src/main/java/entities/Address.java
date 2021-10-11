package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "Address.deleteAllRows", query = "DELETE from Address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String streetName;
    private String additionalInfo;

    @ManyToOne
    private CityInfo cityInfo;

    @OneToMany(mappedBy = "address")
    private List<Person> persons;

    public Address(){}

    public Address(String streetName, String additionalInfo){
        this.streetName = streetName;
        this.additionalInfo = additionalInfo;
        persons = new ArrayList<>();
    }


    // CityInfo Getter/Setter
    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }

    // Streetname Getter/Setter
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    // AdditionalInfo Getter/Setter
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    //Person Getter/Setter
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person person){
        this.persons.add(person);
        if(person != null){
            person.setAddress(this);
        }
    }
}


