package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({@NamedQuery(name = "Hobby.deleteAllRows", query = "DELETE from Hobby")})
public class Hobby implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;
    private String category;
    private String type;

    @ManyToMany
    private List<Person> persons;

    public Hobby(){}

    public Hobby(String name, String category, String type){
        this.name = name;
        this.category = category;
        this.type = type;
        this.persons = new ArrayList<>();
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


    //Person getter/setter
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
