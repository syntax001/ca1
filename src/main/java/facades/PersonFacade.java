package facades;

import dtos.Hobbies.HobbyDTO;
import dtos.Person.PersonDTO;
import dtos.Person.PersonsDTO;
import dtos.Phone.PhoneDTO;
import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.lang.reflect.Type;
import java.util.List;

public class PersonFacade {
    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    private PersonFacade(){

    }

    public static PersonFacade getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager(){return emf.createEntityManager();}




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

            //hobby
            for(HobbyDTO hobbyDTO : personDTO.getHobby()){
                TypedQuery<Hobby> query = em.createQuery("SELECT h FROM Hobby h where h.name = :hobby", Hobby.class);
                query.setParameter("hobby", hobbyDTO.getName());
                Hobby tmpHobby = query.getSingleResult();
                person.addHobby(tmpHobby);
            }

            //phone
            for(PhoneDTO phoneDTO: personDTO.getPhones()){
                try{
                    Phone phoneAlreadyInUse = em
                            .createQuery("SELECT p FROM Phone p WHERE p.number = :number", Phone.class)
                            .setParameter("number",phoneDTO.getNumber())
                            .getSingleResult();
                    throw new WebApplicationException(
                            "Phone with number " + phoneAlreadyInUse.getNumber() +", is already in use.",400);

                }catch (NoResultException e){
                    Phone phoneToAdd = new Phone(phoneDTO.getNumber(), phoneDTO.getDescription());
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


    @SuppressWarnings("unchecked")
    public PersonDTO editBasicInfo(PersonDTO personDTO){
        EntityManager em = emf.createEntityManager();

        try {
            Person updated = em.find(Person.class, personDTO.getId());
            em.getTransaction().begin();
            updated.setFirstName(personDTO.getFirstName());
            updated.setLastName(personDTO.getLastName());
            updated.setEmail(personDTO.getEmail());
            em.getTransaction().commit();
            return new PersonDTO(updated);
        }finally {
            em.close();
        }
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

    public PersonsDTO getPersonsByHobby(String hobby) {
    EntityManager em = emf.createEntityManager();
    try{
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.hobbyList h WHERE h.name = :hobby", Person.class);
        query.setParameter("hobby", hobby);
        List<Person> personList = query.getResultList();
        return new PersonsDTO(personList);
    }finally{
        em.close();
    }
    }

    public PersonsDTO getPersonbyCity(int zipCode) {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.address a WHERE a.cityInfo.zipCode = :zipCode", Person.class);
            query.setParameter("zipCode", zipCode);
            List<Person> personList = query.getResultList();
            return new PersonsDTO(personList);
        }finally{
            em.close();
        }
    }


public PersonDTO editPerson(PersonDTO personDTO){
        EntityManager em = emf.createEntityManager();
        EntityManager em2 = emf.createEntityManager();
        try{
            em2.getTransaction().begin();
            Person person = em2.find(Person.class, personDTO.getId());
            em2.createNativeQuery("DELETE FROM link_person_hobby WHERE p_id = ?").setParameter(1, person.getId()).executeUpdate();
            em2.getTransaction().commit();
        }finally {
            em.close();
        }

        //basic info
        try{
            em.getTransaction().begin();
            Person person = em.find(Person.class, personDTO.getId());
            em.getTransaction().commit();
            if(person == null){
                throw new WebApplicationException(String.format("Person not found"));
            }

            person.setEmail(personDTO.getEmail());
            person.setFirstName(personDTO.getFirstName());
            person.setLastName(personDTO.getLastName());


            //phones
            for(int i = 0; i<personDTO.getPhones().size(); i++){
                PhoneDTO phoneDTO = personDTO.getPhones().get(i);
                try{
                    Phone phone = person.getPhones().get(i);
                    phone.setNumber(phoneDTO.getNumber());
                    phone.setDescription(phoneDTO.getDescription());
                }catch (IndexOutOfBoundsException e){
                    person.addPhone(new Phone(phoneDTO));
                }
            }


            //hobbies
            for(int i = 0; i < personDTO.getHobby().size(); i++){
                HobbyDTO hobbyDTO = personDTO.getHobby().get(i);

                try{
                    Hobby foundHobby = em
                            .createQuery("SELECT h FROM Hobby h WHERE h.name = :hobby", Hobby.class)
                            .setParameter("hobby", hobbyDTO.getName())
                            .getSingleResult();
                            person.addHobby(foundHobby);
                }catch (NoResultException error){
                    throw new WebApplicationException("Hobby: " + hobbyDTO.getName() + ", does not exist", 400);
                }
            }

            //address
            Address addressInUse = em.find(Address.class, personDTO.getAddress().getStreetName());

            if(addressInUse != null){
                person.setAddress(addressInUse);
            } else {
                Address newAddress = new Address(personDTO.getAddress().getStreetName(), personDTO.getAddress().getAdditionalInfo());
                TypedQuery<CityInfo> query = em.createQuery("SELECT c FROM CityInfo  c WHERE c.zipCode = :zipCode", CityInfo.class);
                query.setParameter("zipCode",personDTO.getAddress().getCityInfo().getZipCode());
                CityInfo cityInfo = query.getSingleResult();
                if(cityInfo == null){
                    throw new WebApplicationException(
                            "Zipcode: " + personDTO.getAddress().getCityInfo().getZipCode() + " does not exist", 400);
                }
                newAddress.setCityInfo(cityInfo);
                person.setAddress(newAddress);
            }

            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
            return new PersonDTO(person);

        }finally {
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

//Person updated = em.find(Person.class, personDTO.getId());
//updated.setFirstName(personDTO.getFirstName());

/*

    @SuppressWarnings("unchecked")
    public List<DTOMovie> getMoviesByName(String title) throws Exception{
        EntityManager em = emf.createEntityManager();
            try{
                em.getTransaction().begin();
                TypedQuery<Movie> typedQuery = em.createQuery("SELECT m FROM Movie m WHERE m.title=:title",Movie.class);
                typedQuery.setParameter("title",title);
                List<Movie> result = typedQuery.getResultList();
                em.getTransaction().commit();
                return (List<DTOMovie>) (List<?>) result;
            } finally {
                emf.close();
                em.close();

            }
    }

 */