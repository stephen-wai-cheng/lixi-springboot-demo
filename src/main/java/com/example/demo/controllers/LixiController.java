package com.example.demo.controllers;

import com.ixi.val.Package;
import com.ixi.val.Residential;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
@OpenAPIDefinition(
        info = @Info(
                version = "1.0.0",
                title = "OpenApi LIXI demo",
                description = "Generate typescript client from LIXI schema"),
        servers = @Server(url = "http://localhost:8080")
)
@RestController
@Path("/api")
public class LixiController {
    @GET
    @Operation(
            summary = "Demonstrate returning LIXI object",
            description = "Return a Residential LIXI object"
    )
    @ApiResponse(
            description = "Return a Residential LIXI object",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Residential.class))
    )
    @Path("/residential")
    @Produces("application/json")
    public ResponseEntity<Residential> getApiResidential() {
        var residential = new Residential();
        residential.setDisplayHome(Package.YesNoList.NO);
        residential.setType(Residential.ResidentialTypeList.FULLY_DETACHED_HOUSE);
        residential.setWillOwn3UnitsInComplex(Package.YesNoList.YES);

        try {
            return new ResponseEntity<>(residential, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
