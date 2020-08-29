package rest.rest1;

import com.google.gson.Gson;
import entity.Animal;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Sebastian
 */
@Path("animals_db")
public class AnimalFromDb {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalFromDb
     */
    public AnimalFromDb() {
    }

    @Path("animals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }

    @Path("animalbyid/{animalbyid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalById(@PathParam("animalbyid") int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Animal an = em.find(Animal.class, id);
            return new Gson().toJson(an);
        } finally {
            em.close();
        }
    }

    @Path("animalbytype/{animalbytype}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByType(@PathParam("animalbytype") String type) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query
                    = em.createQuery("SELECT animal FROM Animal animal WHERE animal.type = :type", Animal.class);
            Animal animal = (Animal) query.setParameter("type", type).getSingleResult();
            return new Gson().toJson(animal);
        } finally {
            em.close();
        }
    }

    @Path("random_animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomAnimal() {
        EntityManager em = emf.createEntityManager();
        Random random = new Random();
        try {
            TypedQuery<Animal> query
                    = em.createQuery("SELECT animal FROM Animal animal", Animal.class);
            List<Animal> animals = query.getResultList();
            if (animals.isEmpty()) {
                return new Gson().toJson(null);
            }
            int randomAnimalNumber = random.nextInt(animals.size());
            Animal randomAnimal = animals.get(randomAnimalNumber);
            return new Gson().toJson(randomAnimal);
        } finally {
            em.close();
        }
    }

}
