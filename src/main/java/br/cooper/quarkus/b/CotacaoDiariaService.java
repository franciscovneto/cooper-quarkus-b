package br.cooper.quarkus.b;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/cotacaoDiaria")
@RegisterRestClient
public interface CotacaoDiariaService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projetoA")
    public CotacaoDiaria findCotacaoDia(@QueryParam("@dataCotacao") String dataCotacao);
}