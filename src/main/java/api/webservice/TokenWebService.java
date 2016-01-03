package api.webservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import api.beans.request.InitTokenBeanRequest;
import api.beans.response.TokenInfoResponse;
import api.webservice.implementation.TokenWebServiceImplementation;

@Path("token")
public class TokenWebService {
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("{idToken}/info")
	public TokenInfoResponse tokenInfos(@Context HttpServletRequest req, @PathParam("idToken") int idToken,
			@QueryParam("select") List<String> select) {
		return new TokenWebServiceImplementation().tokenInfos(req, idToken, select);
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("init/{idToken}")
	public Response init(@Context HttpServletRequest req, InitTokenBeanRequest r, @PathParam("idToken") int idToken) {
		return new TokenWebServiceImplementation().init(req, r, idToken);

	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("reset/{idToken}")
	public Response reset(@Context HttpServletRequest req, InitTokenBeanRequest r, @PathParam("idToken") int idToken) {
		return new TokenWebServiceImplementation().reset(req, r, idToken);

	}
}