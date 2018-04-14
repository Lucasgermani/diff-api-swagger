package com.diff.api.service;

import com.diff.api.archive.DataArchive;
import com.diff.api.comparator.Comparator;
import com.diff.api.errorhandlers.ErrorResponseBuilder;
import com.diff.api.resource.DiffOutput;
import com.diff.api.resource.ValuesEntry;
import com.diff.api.resource.enums.Direction;
import com.wordnik.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/v1/diff")
@Api(value = "/v1/diff", description = "Base64String Diff")
public class DiffRestService {

	@Produces({MediaType.APPLICATION_JSON})
	@POST
	@Path("/{ID}/left")
	@ApiOperation(value = "add left value", notes = "add left string to compare")
	@ApiResponses({
			@ApiResponse(code = 201, message = "OK"),
			@ApiResponse(code = 404, message = "Invalid Data")
	})
	public Response left( @Context final UriInfo uriInfo,
						  @ApiParam(value = "ID", required = true) @PathParam("ID") final int ID,
						  @ApiParam(value = "Base64String", required = true) @FormParam("value") final String value) {

		DataArchive.add(ID, value, Direction.LEFT);
		return Response.created(uriInfo.getRequestUriBuilder().build()).build();
	}

	@Produces({MediaType.APPLICATION_JSON })
	@POST
	@Path("/{ID}/right")
	@ApiOperation(value = "add right value", notes = "add right string to compare")
	@ApiResponses({
			@ApiResponse(code = 201, message = "OK"),
			@ApiResponse(code = 404, message = "Invalid Data")
	})
	public Response right( @Context final UriInfo uriInfo,
						  @ApiParam(value = "ID", required = true) @PathParam("ID") final int ID,
						  @ApiParam(value = "Base64String", required = true) @FormParam("value") final String value) {

		DataArchive.add(ID, value, Direction.RIGHT);
		return Response.created(uriInfo.getRequestUriBuilder().build()).build();
	}


	@Produces({MediaType.APPLICATION_JSON})
	@GET
	@Path("/{ID}")
	@ApiOperation(value = "get string diff", notes = "get diff from left and right values")
	@ApiResponses({
			@ApiResponse(code = 201, message = "OK"),
			@ApiResponse(code = 404, message = "Invalid Data")
	})
	public Response diff(@Context final UriInfo uriInfo,
						   @ApiParam(value = "ID", required = true) @PathParam("ID") final int ID){

		ValuesEntry reg = DataArchive.getInstance(ID);

		if(reg != null){
			if(StringUtils.isEmpty(reg.getLeft())){
				return ErrorResponseBuilder.buildErrorResponseWithMessage(Response.Status.NOT_FOUND,
						String.format("Left entry not found for id = '%s'", ID));
			}else if(StringUtils.isEmpty(reg.getRight())){
				return ErrorResponseBuilder.buildErrorResponseWithMessage(Response.Status.NOT_FOUND,
						String.format("Right entry not found for id = '%s'", ID));
			}
		}else{
			return ErrorResponseBuilder.buildErrorResponseWithMessage(Response.Status.NOT_FOUND,
					String.format("Entry not found for id = '%s'", ID));
		}

		DiffOutput diffOutput = new Comparator().compare(reg);
		return Response.ok(diffOutput).build();
	}
}
