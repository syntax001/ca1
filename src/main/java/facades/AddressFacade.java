package facades;

import dtos.AddressDTO;
import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AddressFacade {

    private static AddressFacade instance;
    private static EntityManagerFactory emf;

    private AddressFacade(){}


    public static AddressFacade getAddressFacade(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new AddressFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager(){return emf.createEntityManager();}

    public AddressDTO createAddress(AddressDTO add){
        Address address = new Address(add.getStreetName(), add.getAdditionalInfo());
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    return new AddressDTO(address);
    }




}
