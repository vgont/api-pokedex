package br.com.fiap.pokedex.model.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.pokedex.model.entity.Pokemon;

public class PokemonRepository extends Repository {

	public static ArrayList<Pokemon> findAll() {
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		String sql = "select * from tb_ddd_pokedex";

		try (PreparedStatement ps = getConnection().prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Pokemon pokemon = createPokemonWithResultSet(rs);
				pokemons.add(pokemon);
			}
			return pokemons;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static Pokemon save(Pokemon pokemon) {
		String sql = "insert into tb_ddd_pokedex(numero,nome,altura,peso,categoria,data_da_captura) "
				+ "values(null,?,?,?,?,?)";

		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

			setPreparedStatementParameters(ps, pokemon, false);

			int rowsAffected = ps.executeUpdate();
			
			if (rowsAffected > 0) {
				return pokemon;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static Pokemon update(Pokemon pokemon) {
		String sql = "update tb_ddd_pokedex set nome=?, altura=?, peso=?, categoria=?, data_da_captura=?"
					+ "where numero=?";
		
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			setPreparedStatementParameters(ps, pokemon, true);
			
			int rowsAffected = ps.executeUpdate();
			
			if (rowsAffected > 0) {
				return pokemon;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static boolean delete(Long numero) {
		String sql = "DELETE FROM tb_ddd_pokedex WHERE numero=?";

		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setLong(1, numero);

			int rowsAffected = ps.executeUpdate();
			
			return rowsAffected > 0;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		return false;
	}

	private static Pokemon createPokemonWithResultSet(ResultSet rs) {
		try {
			Pokemon pokemon = new Pokemon();
			pokemon.setNumero(rs.getLong("numero"));
			pokemon.setNome(rs.getString("nome"));
			pokemon.setAltura(rs.getDouble("altura"));
			pokemon.setPeso(rs.getDouble("peso"));
			pokemon.setCategoria(rs.getString("categoria"));
			pokemon.setDataDaCaptura(rs.getDate("data_da_captura").toLocalDate());
			return pokemon;
		} catch (SQLException e) {
			System.out.println("Erro ao criar pokemon: " + e.getMessage());
		}
		return null;
	}
	
	private static void setPreparedStatementParameters(PreparedStatement ps, Pokemon pokemon, boolean isUpdate) {
		try {
			ps.setString(1, pokemon.getNome());
			ps.setDouble(2, pokemon.getAltura());
			ps.setDouble(3, pokemon.getPeso());
			ps.setString(4, pokemon.getCategoria());
			ps.setDate(5, Date.valueOf(pokemon.getDataDaCaptura()));
			
			if (isUpdate) {
				ps.setLong(6, pokemon.getNumero());
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
