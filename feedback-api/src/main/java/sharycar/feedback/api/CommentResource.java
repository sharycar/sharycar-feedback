package sharycar.feedback.api;

import sharycar.feedback.api.Comment;


import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
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



    @GET
    @Path("/info")
    public Response getProjectInfo() {
        ProjectInfoClass pic = new ProjectInfoClass();
        return Response.ok(pic).build();
    }


}
