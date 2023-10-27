package br.com.fiap.pokedex.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.pokedex.model.entity.Pokemon;

public class PokemonRepository extends Repository {

	public static ArrayList<Pokemon> findAll() {
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		String sql = "select * from tb_ddd_pokedex";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Pokemon pokemon = createPokemon(rs);
				pokemons.add(pokemon);
			}
			return pokemons;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConnection();
		}
		return null;
	}

	private static Pokemon createPokemon(ResultSet rs) throws SQLException {
		Pokemon pokemon = new Pokemon();
		pokemon.setNumero(rs.getLong("numero"));
		pokemon.setNome(rs.getString("nome"));
		pokemon.setAltura(rs.getDouble("altura"));
		pokemon.setPeso(rs.getDouble("peso"));
		pokemon.setCategoria(rs.getString("categoria"));
		pokemon.setDataDaCaptura(rs.getDate("data_da_caputura").toLocalDate());
		return pokemon;
	}
}
