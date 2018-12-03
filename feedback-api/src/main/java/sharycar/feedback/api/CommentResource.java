package sharycar.feedback.api;


import sharycar.feedback.persistence.Comment;


import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.persistence.Query;

@Path("/comments")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {

    @PersistenceContext
    private EntityManager em;


    @GET
    public Response getComments() {

        TypedQuery<Comment> query = em.createNamedQuery("Comment.findAll", Comment.class);

        List<Comment> comments= query.getResultList();

        return Response.ok(comments).build();
    }


    @POST
    public Response createComment(Comment comment) {
        if (comment.getUsername() == null || comment.getUsername().isEmpty()
                || comment.getComment() == null || comment.getComment().isEmpty()
                || comment.getCarId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            // Create record in database
            try {
                em.getTransaction().begin();
                em.persist(comment);
                em.getTransaction().commit();
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(comment).build();
            }
        }

        if (comment.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(comment).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(comment).build();
        }
    }

    @GET
    @Path("/{carId}")
    public Response getCommentForCar(@PathParam("carId") Integer carId) {

        try {
            Query query = em.createQuery("SELECT c FROM Comment c WHERE c.car_id = :carId");
            query.setParameter("carId", carId);
            return Response.ok(query.getResultList()).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }


}
