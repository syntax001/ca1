package entities;



import dtos.Phone.PhoneDTO;

import javax.persistence.*;

    @Entity
    @NamedQuery(name = "Phone.deleteAllRows", query = "DELETE from Phone")
    public class Phone {

        private static final long SerialVersionUID = 1L;
        @Id
        @GeneratedValue
        @Column(name = "id", nullable = false)
        private Integer id;
        private String number;
        private String description;

        @ManyToOne
        private Person person;

        public Phone(){}

        public Phone(PhoneDTO phoneDTO){
            this.number = phoneDTO.getNumber();
            this.description = phoneDTO.getDescription();
            this.id = phoneDTO.getId();
        }

        public Phone(String number, String description){
            this.number = number;
            this.description = description;
        }

        //id
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        //number
        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        //description
        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        //person
        public Person getPerson() {
            return person;
        }

        public void setPerson(Person person) {
            this.person = person;
        }
    }

