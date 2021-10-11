package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "CityInfo.deleteAllRows", query = "DELETE from CityInfo")
public class CityInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int zipCode;
    private String city;

    @OneToMany(mappedBy = "cityInfo", cascade = CascadeType.PERSIST)
    private List<Address> addresses;

    public CityInfo(){}


    public CityInfo(Integer zipCode, String city){
        this.zipCode = zipCode;
        this.city = city;
        this.addresses = new ArrayList<>();
    }

    //ID getter/setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    //Address getter/setter
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address address){
        this.addresses.add(address);
        if(address != null){
            address.setCityInfo(this);
        }
    }

}