package br.cooper.quarkus.b;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/cotacaoDiaria")
public class CotacaoDiariaResource {

    @Inject
    @RestClient
    CotacaoDiariaService cotService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/projetoB")
	@Operation(summary = "Retorna a cotacao do dia passado e do dia util anterior", description = "Retorna a cotacao do dia passado e do dia util anterior conforme parametro indicado")
	@APIResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(type = SchemaType.OBJECT, implementation = CotacaoDiaria.class)))
    @APIResponse(responseCode = "400", description = "Erro ao tentar acessar o serviço, verifique a rota", content = @Content(schema = @Schema(type = SchemaType.OBJECT, implementation = CotacaoDiaria.class)))
    @APIResponse(responseCode = "404", description = "Objeto não encontrado ", content = @Content(schema = @Schema(type = SchemaType.OBJECT, implementation = CotacaoDiaria.class)))
    @APIResponse(responseCode = "500", description = "Falha no processamento da requisição. ", content = @Content(schema = @Schema(type = SchemaType.OBJECT, implementation = CotacaoDiaria.class)))
    public List<CotacaoDiaria> findCotacaoDia(@QueryParam("@dataCotacao") String dataCotacao) throws ParseException {
    	
    	List<CotacaoDiaria> cot = new ArrayList<CotacaoDiaria>();
    	
    	cot.add(cotService.findCotacaoDia(dataCotacao));
    	Date date = Util.getDateFromXlsx(dataCotacao.replace("'", " "));
    	
    	Date novaDataCotacao = Util.recuperaUltimoDiaUtil(date);
    	
    	String dataCotacaoFormatada= Util.formatarData(novaDataCotacao);
    	cot.add(cotService.findCotacaoDia("'" + dataCotacaoFormatada + "'"));
    	
        return cot;
    }
}