package entities;
import dtos.PhoneDTO;
import javax.persistence.*;

    @Entity
    @NamedQuery(name = "Phone.deleteAllRows", query = "DELETE from Phone")
    public class Phone {
        private static final long SerialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String number;

        @ManyToOne
        private Person person;

        public Phone(){}

        public Phone(PhoneDTO phoneDTO){
            this.number = phoneDTO.getNumber();
            this.id = phoneDTO.getId();
        }

        public Phone(String number){
            this.number = number;
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

        //Person getter/setter
        public Person getPerson() {
            return person;
        }

        public void setPerson(Person person) {
            this.person = person;
        }
    }

