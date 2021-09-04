package org.jana.dropwizard;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/brands")
@Produces(MediaType.APPLICATION_JSON)
public class BrandResource {
    private final int defaultSize;
    private final BrandRepo brandRepository;

    public BrandResource(int defaultSize, BrandRepo brandRepository) {
        this.defaultSize = defaultSize;
        this.brandRepository = brandRepository;
    }

//    @GET
//    @Timed
//    public Brand sayHello(@QueryParam("name") Optional<String> name) {
//        return new Brand(300L, "threeHundred");
//    }

    @GET
    public List<Brand> getBrands(@QueryParam("size") Optional<Integer> size) {
        return brandRepository.findAll(size.orElse(defaultSize));
    }

    @GET
    @Path("/{id}")
    public Brand getById(@PathParam("id") Long id) {
        return brandRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }

}
