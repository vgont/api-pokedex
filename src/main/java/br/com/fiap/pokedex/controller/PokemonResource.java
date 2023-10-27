package br.com.fiap.pokedex.controller;

import java.util.ArrayList;

import br.com.fiap.pokedex.model.entity.Pokemon;
import br.com.fiap.pokedex.model.repository.PokemonRepository;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

@Path("/pokedex")
public class PokemonResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		ArrayList<Pokemon> foundedPokemons = PokemonRepository.findAll();

		if (foundedPokemons.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(foundedPokemons).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(Pokemon pokemon) {
		Pokemon savedPokemon = PokemonRepository.save(pokemon);

		if (savedPokemon == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.created(null).entity(savedPokemon).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Pokemon pokemon) {
		Pokemon updatedPokemon = PokemonRepository.update(pokemon);

		if (updatedPokemon == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.created(null).entity(updatedPokemon).build();
	}

	@DELETE
	@Path("/{numero}")
	public Response delete(@PathParam("numero") Long numero) {
		if (PokemonRepository.delete(numero)) {
			return Response.noContent().build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
}
