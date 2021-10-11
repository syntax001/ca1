package facades;

import dtos.HobbyDTO;
import dtos.PersonDTO;
import dtos.PhoneDTO;
import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.util.List;

public class PersonFacade {
    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    private PersonFacade(){}

    public static PersonFacade getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public static PersonDTO getPersonById(Integer id){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Person person = em.find(Person.class,id);
        em.getTransaction().commit();
        em.close();

        if(person!=null){
            return new PersonDTO(person);
        }else{
            return null;
        }
    }

    public static PersonDTO getPersonByPhone(String phone){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Person person = em.find(Person.class,phone);
        em.getTransaction().commit();
        em.close();

        if(person != null){
            return new PersonDTO(person);
        }else{
            return null;
        }
    }

    public PersonDTO createPerson(PersonDTO personDTO){
        EntityManager em = emf.createEntityManager();
        Person person = new Person(personDTO.getEmail(),personDTO.getFirstName(),personDTO.getLastName());
        try{

            //Hobby
            for(HobbyDTO hobbyDTO : personDTO.getHobby()){
                TypedQuery<Hobby> query = em.createQuery("SELECT h FROM Hobby h where h.name = :hobby", Hobby.class);
                query.setParameter("hobby", hobbyDTO.getName());
                Hobby tmpHobby = query.getSingleResult();
                person.addHobby(tmpHobby);
            }

            //Phone, address
            for(PhoneDTO phoneDTO: personDTO.getPhones()){
                try{
                    Phone phoneAlreadyInUse = em
                            .createQuery("SELECT p FROM Phone p WHERE p.number = :number", Phone.class)
                            .setParameter("number",phoneDTO.getNumber())
                            .getSingleResult();
                    throw new WebApplicationException(
                            "Phone with number " + phoneAlreadyInUse.getNumber() +", is already in use.",400);

                }catch (NoResultException e){
                    Phone phoneToAdd = new Phone(phoneDTO.getNumber());
                    person.addPhone(phoneToAdd);
                }

                }
            Address address = new Address( personDTO.getAddress().getAdditionalInfo(), personDTO.getAddress().getStreetName());
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return new PersonDTO(person);
    }

    public Integer getPersonCount(){
        EntityManager em = emf.createEntityManager();
        try{
            Integer personCount = (Integer)em.createQuery("SELECT COUNT(p) FROM Person p").getSingleResult();
            return personCount;
        }finally{
            em.close();
        }
    }

    public List<PersonDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> persons = query.getResultList();
        return PersonDTO.getPersonDtos(persons);
    }

    public Integer getNumberOfPeopleByHobby(String hobby){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Integer> query = em.createQuery("SELECT COUNT(p.id) FROM Person p JOIN p.hobbyList h WHERE h.name = :hobby", Integer.class);
            query.setParameter("hobby", hobby);
            Integer numberOfPeople = query.getSingleResult();
            return numberOfPeople;
        } finally{
            em.close();
        }
    }

    public PersonDTO getPersonsByHobby(String hobby) {
    EntityManager em = emf.createEntityManager();
    try{
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.hobbyList h WHERE h.name = :hobby", Person.class);
        query.setParameter("hobby", hobby);
        List<Person> personList = query.getResultList();
        return new PersonDTO((Person) personList);
    }finally{
        em.close();
    }
    }

    public PersonDTO getPersonbyCity(int zipCode) {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.address a WHERE a.cityInfo.zipCode = :zipCode", Person.class);
            query.setParameter("zipCode", zipCode);
            List<Person> personList = query.getResultList();
            return new PersonDTO((Person) personList);
        }finally{
            em.close();
        }
    }


public String createTables(){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();

            em.getTransaction().commit();

            return "Tables created.";
        }finally{
            em.close();
        }
    }
}