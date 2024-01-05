package com.cedulas;

import com.cedulas.models.ReqGenera;
import com.cedulas.models.Request;
import com.cedulas.services.CedulaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api")
public class Api {
    @Inject
    CedulaService cs;

    @Path("lista")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response lista() {
        System.out.println("Listando cédulas");
        return Response.status(200).entity(cs.listar()).build();
    }

    @Path("graba")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response graba(Request req) {
        return Response.ok(cs.grabar(req)).build();
    }

    @Path("genera")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response genera(ReqGenera req) {
        return Response.ok(cs.genera(req)).build();
    }

    @Path("generaAleatorio")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generaA(ReqGenera req) {
        return Response.ok(cs.generaA(req)).build();
    }

    @Path("valida")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response valida(Request req) {
        return Response.ok(cs.valida(req)).build();
    }

    @Path("elimina")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response elimina() {
        cs.elimina();
        return Response.ok("ELIMINADO").build();
    }
}