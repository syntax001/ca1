package facades;

import dtos.HobbyDTO;
import dtos.PersonDTO;
import dtos.PhoneDTO;
import entities.*;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PersonFacadeTest {
    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    private static Person person1, person2;

    @BeforeAll
    public static void setUpClass(){
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = PersonFacade.getInstance(emf);
    }

    @AfterAll
    public static void tearDownClass(){

    }

    //setup
    @BeforeEach
    public void setUp(){

        CityInfo cityInfo = new CityInfo(3000,"TestCity");

        person1 = new Person("Testmail@hotmail.com","TestFirstName","TestLastName");
        Phone phone1 = new Phone("12345678");
        person1.addPhone(phone1);
        Hobby hobby = new Hobby("Tourism","General","Exploration");
        person1.addHobby(hobby);
        Address address1 = new Address("Teststreet", "Teststreet");
        address1.setCityInfo(cityInfo);
        person1.setAddress(address1);


        person2 = new Person("Testmail@gmail.com","TestFirstName","TestLastName");
        Phone phone2 = new Phone("09101112");
        person2.addPhone(phone2);
        person2.addHobby(hobby);
        Address address2 = new Address("Teststreet", "Teststreet");
        address1.setCityInfo(cityInfo);
        person2.setAddress(address2);


        Hobby newHobby = new Hobby("Swimming","Water","Physical");

        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();

            em.createNamedQuery("Phone.deleteAllRows").executeUpdate();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.createNamedQuery("CityInfo.deleteAllRows").executeUpdate();

            em.persist(cityInfo);
            em.persist(hobby);
            em.persist(newHobby);
            em.persist(person1);
            em.persist(person2);

            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }


    @AfterAll
    static void tearDownAll(){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
            em.createNamedQuery("Phone.deleteAllRows").executeUpdate();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
            em.createNamedQuery("CityInfo.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }

    @AfterEach
    public void tearDown(){

    }

    @Test
    public void getPersonByIdTest(){
        String expected = "TestFirstName";
        String actual = facade.getPersonById(person1.getId()).getFirstName();
        assertEquals(expected, actual);
    }

    @Test
    public void getPersonCountByHobbyTest(){
        int expected = 2;
        int actual = facade.getNumberOfPeopleByHobby("Tourism");
        assertEquals(expected, actual);
    }

}
