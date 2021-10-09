package rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.Person.PersonDTO;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;

@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final PersonFacade FACADE = PersonFacade.getInstance(EMF);
    private static final Gson GSON  = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces("text/plain")
    public String hello(){
        return "Hello, World!";
    }

    @GET
    @Produces("application/json")
    @Path("/all")
    public String getAllPersons(){
        return GSON.toJson(FACADE.getAll());
    }

    @GET
    @Produces("applications/json")
    @Path("/{id}")
    public String getByID(@PathParam("id") int id){
        return GSON.toJson(FACADE.getPersonById(id));
    }

    @GET
    @Produces("application/json")
    @Path("/hobby/{hobby}")
    public String getByHobby(@PathParam("hobby") String hobby){
        return GSON.toJson(FACADE.getPersonsByHobby(hobby));
    }

    @GET
    @Produces("application/json")
    @Path("/number/{number}")
    public String getByNumber(@PathParam("number") String number){
        return GSON.toJson(FACADE.getPersonByPhone(number));
    }

    @GET
    @Produces("application/json")
    @Path("/city/{zipCode}")
    public String getByZip(@PathParam("zipCode") int zipCode){
        return GSON.toJson(FACADE.getPersonbyCity(zipCode));
    }

    @GET
    @Produces("application/json")
    @Path("/hobby/count/{hobby}")
    public String getHobbyCount(@PathParam("hobby") String hobby){
        return GSON.toJson(FACADE.getNumberOfPeopleByHobby(hobby));
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public String addPerson(String person){
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
        PersonDTO pNew = FACADE.createPerson(p);
        return GSON.toJson(pNew);
    }

    @PUT
    @Path("/{id}")
    public String editPerson(@PathParam("id") Integer id, String person){
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
        p.setId(id);
        PersonDTO pEdited = FACADE.editBasicInfo(p);
        return GSON.toJson(pEdited);

    }




}




