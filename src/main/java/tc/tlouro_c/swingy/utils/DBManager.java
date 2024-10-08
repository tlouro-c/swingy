package tc.tlouro_c.swingy.utils;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tc.tlouro_c.swingy.models.Character;
import tc.tlouro_c.swingy.models.CharacterClass;
import tc.tlouro_c.swingy.models.HeroBuilder;
import tc.tlouro_c.swingy.models.Level;
import tc.tlouro_c.swingy.models.artifacts.ArtifactFactory;

public class DBManager {

	private static DBManager instance;
	private Connection connection;
	private String url;

	private DBManager() {
		url = "jdbc:sqlite:swingy.db";
	}

	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url);
		}
		return connection;
	} 

	public void createTableIfNotExists() {

		String sqlQuery = "CREATE TABLE IF NOT EXISTS Heroes ("
							+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
							+ "name TEXT NOT NULL, "
							+ "class TEXT NOT NULL CHECK (class IN ('Assassin', 'Bruiser', 'Tank')),"
							+ "sprite INTEGER NOT NULL CHECK (sprite BETWEEN 1 AND 3),"
							+ "level INTEGER NOT NULL DEFAULT 1 CHECK (level BETWEEN 1 AND 6),"
							+ "current_xp INTEGER NOT NULL DEFAULT 0,"
							+ "attack INTEGER NOT NULL,"
							+ "defense INTEGER NOT NULL,"
							+ "hit_points INTEGER NOT NULL,"
							+ "artifact TEXT NULLABLE"
							+ ");";

		try (Connection conn = this.getConnection();
			Statement statement = conn.createStatement()){

			statement.execute(sqlQuery);
			DebugTools.log("Table exists/created Successfully!");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void createHero(Character hero) {

		String sqlQuery = "INSERT INTO heroes(name, class, sprite, attack, defense, hit_points)"
								+ "VALUES(?, ?, ?, ?, ?, ?)";

		try (Connection conn = this.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {
			
			preparedStatement.setString(1, hero.getName());
			preparedStatement.setString(2, hero.getCharacterClass().toString());
			preparedStatement.setInt(3, hero.getSpriteNumber());
			preparedStatement.setInt(4, hero.getAttack());
			preparedStatement.setInt(5, hero.getDefense());
			preparedStatement.setInt(6, hero.getMaxHP());

			preparedStatement.executeUpdate();
			DebugTools.log("Hero entry created Successfully!");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateHero(Character hero) {

		String sqlQuery = "UPDATE heroes SET level = ?, current_xp = ?, attack = ?, defense = ?, hit_points = ?, artifact = ?"
						+ "WHERE id = ?";

		try (Connection conn = this.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {

			var artifact = hero.getArtifact();
			
			preparedStatement.setInt(1, hero.getLevel());
			preparedStatement.setInt(2, hero.getCurrentHP());
			preparedStatement.setInt(3, hero.getAttack());
			preparedStatement.setInt(4, hero.getDefense());
			preparedStatement.setInt(5, hero.getMaxHP());
			preparedStatement.setString(6, artifact != null ? artifact.getClass().getSimpleName() : null);
			preparedStatement.setInt(7, hero.getDbId());

			preparedStatement.executeUpdate();
			DebugTools.log("Hero entry updated Successfully!");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteHero(Character hero) {

		String sqlQuery = "DELETE FROM heroes WHERE id = ?";

		try (Connection conn = this.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery)) {
			
			System.out.println(hero.getDbId());
			preparedStatement.setInt(1, hero.getDbId());

			preparedStatement.executeUpdate();
			DebugTools.log("Hero entry deleted Successfully!");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}


	public ArrayList<Character> fetchHeroes() {

		var heroesList = new ArrayList<Character>();
		var heroBuilder = new HeroBuilder();
		String sqlQuery = "SELECT * FROM heroes";

		try (Connection conn = this.getConnection();
			Statement statement = conn.createStatement()) {

			ResultSet rs = statement.executeQuery(sqlQuery);

			while (rs.next()) {
				heroBuilder.name(rs.getString("name"))
							.dbId(rs.getInt("id"))
							.characterClass(CharacterClass.getCorrespondingClass(rs.getString("class")))
							.level(new Level(rs.getInt("level"), rs.getInt("current_xp")))
							.sprite(rs.getInt("sprite"))
							.attack(rs.getInt("attack"))
							.defense(rs.getInt("defense"))
							.maxHP(rs.getInt("hit_points"))
							.artifact(ArtifactFactory.getInstance().newArtifact(rs.getString("artifact")));
				heroesList.add(heroBuilder.buildExistingHero());
			}
			DebugTools.log("Heroes fetched Successfully!");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return heroesList;
	}

	public int fetchHeroesCount() {
		
		int heroesCount = 0;
		String sqlQuery = "SELECT COUNT(*) FROM heroes";

		try (Connection conn = this.getConnection();
			Statement statement = conn.createStatement()) {

			ResultSet rs = statement.executeQuery(sqlQuery);
			
			rs.next();
			heroesCount = rs.getInt(1);
			DebugTools.log("Heroes count fetched Successfully! Count: " + heroesCount);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return heroesCount;
	}
}
