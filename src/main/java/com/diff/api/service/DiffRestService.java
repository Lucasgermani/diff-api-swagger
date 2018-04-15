package com.diff.api.service;

import com.diff.api.archive.DataArchive;
import com.diff.api.comparator.Comparator;
import com.diff.api.errorhandlers.ErrorResponseBuilder;
import com.diff.api.resource.DiffOutput;
import com.diff.api.resource.ValueInput;
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
public class DiffRestService{

	/**
	 * Left endpoint that gonna save information with ID and side.
	 * @param uriInfo
	 * @param ID
	 * @param valueInput
	 * @return HTTP status 201 if sucessfull
	 */
	@POST
	@Path("/{ID}/left")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(value = "add left value", notes = "add left string to compare")
	@ApiResponses({
			@ApiResponse(code = 201, message = "OK"),
			@ApiResponse(code = 404, message = "Invalid Data")
	})
	public Response left( @Context final UriInfo uriInfo,
						  @ApiParam(value = "ID", required = true) @PathParam("ID") final int ID,
						  @ApiParam(value = "value", required = true) final ValueInput valueInput){

		DataArchive.add(ID, valueInput.getValue(), Direction.LEFT);
		return Response.created(uriInfo.getRequestUriBuilder().build()).build();
	}

	/**
	 * Right endpoint that gonna save information with ID and side.
	 * @param uriInfo
	 * @param ID
	 * @param valueInput
	 * @return HTTP status 201 if sucessfull
	 */
	@POST
	@Path("/{ID}/right")
	@Produces({MediaType.APPLICATION_JSON })
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(value = "add right value", notes = "add right string to compare")
	@ApiResponses({
			@ApiResponse(code = 201, message = "OK"),
			@ApiResponse(code = 404, message = "Invalid Data")
	})
	public Response right( @Context final UriInfo uriInfo,
						  @ApiParam(value = "ID", required = true) @PathParam("ID") final int ID,
						  @ApiParam(value = "value", required = true) final ValueInput valueInput){

		DataArchive.add(ID, valueInput.getValue(), Direction.RIGHT);
		return Response.created(uriInfo.getRequestUriBuilder().build()).build();
	}

	/**
	 * Get endpoint that provides the result of a comparision
	 * @param uriInfo
	 * @param ID
	 * @return HTTP status 200 if the comparision was performed without error and the comparision result as json
	 * or HTTP status 404 if the comparision failed and a error message
	 */
	@GET
	@Path("/{ID}")
	@Produces({MediaType.APPLICATION_JSON})
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
