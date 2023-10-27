package br.com.fiap.pokedex.model.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {

	private static ConnectionFactory instance;
	private Connection connection;
	private String url;
	private String user;
	private String pass;
	private String driver;

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}

	public String getDriver() {
		return driver;
	}

	public ConnectionFactory(Connection connection, String url, String user, String pass, String driver) {
		this.url = url;
		this.user = user;
		this.pass = pass;
		this.driver = driver;
	}

	public ConnectionFactory(String url, String user, String pass, String driver) {
		this.url = url;
		this.user = user;
		this.pass = pass;
		this.driver = driver;
	}

	public static ConnectionFactory getInstance() {
		ConnectionFactory result = instance;
		if (result != null) {
			return result;
		}
		Properties prop = new Properties();
		try {
			FileInputStream file = new FileInputStream("./src/main/resources/application.properties");
			prop.load(file);
			String url = prop.getProperty("datasource.url");
			String user = prop.getProperty("datasource.username");
			String password = prop.getProperty("datasource.password");
			String driver = prop.getProperty("datasource.driver-class-name");
			System.out.println("" + url + user + password + driver);
			file.close();
			if (instance == null) {
				instance = new ConnectionFactory(url, user, password, driver);
			}
			return instance;
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Connection getConnection() {
		try {
			if (this.connection != null && !this.connection.isClosed())
				return this.connection;

			checkNotBlank(this.getUser(), "usuário incorreto");
			checkNotBlank(this.getUrl(), "url incorreto");
			checkNotBlank(this.getDriver(), "nome da classe incorreta");

			Class.forName(this.getDriver());
			this.connection = DriverManager.getConnection(this.getUrl(), this.getUser(), this.getPass());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}

	public void checkNotBlank(String value, String errorMessage) {
		if (value.isBlank()) {
			throw new RuntimeException("Error: " + errorMessage);
		}
	}
}
