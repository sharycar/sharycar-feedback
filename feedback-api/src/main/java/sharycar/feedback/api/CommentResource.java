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



    @GET //@TODO query comments by car id
    @Path("/{carId}")
    public Response getProjectInfo(@PathParam("carId") Integer carId) {
        TypedQuery<Comment> query = em.createNamedQuery("Comment.findAll", Comment.class);

        List<Comment> comments= query.getResultList();

        return Response.ok(comments).build();
    }


}
