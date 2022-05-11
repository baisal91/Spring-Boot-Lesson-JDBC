package springdata.tennisplayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

//PlayerDao to interact with the database
@Repository
public class PlayerDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * SELECT Method
     * @return
     */
    public List<Player> getAllPlayers(){
        String sql = "SELECT * FROM PLAYER";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Player>(Player.class));
    }

    /**
     * SELECT BY ID
     * @param id
     * @return
     */
    public Player getPlayerById(int id){
        String sql = "SELECT * FROM PLAYER WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql,
                                            new BeanPropertyRowMapper<Player>(Player.class),
                                            new Object[] {id});
    }

    /**
     * UPDATE "INSERT" method
     * @param player
     * @return
     */
    public int insertPlayer(Player player){
        String sql = "INSERT INTO PLAYER (ID, Name, Nationality,Birth_date, Titles) " +
                "VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[] {player.getId(), player.getName(), player.getNationality(),
                            new Timestamp(player.getBirthDate().getTime()),
                            player.getTitles()});
    }

    /**
     * UPDATE method
     * @param player
     * @return
     */
    public int updatePlayer(Player player){
        String sql = "UPDATE PLAYER " +
                "SET Name = ?, Nationality = ?, Birth_date = ? , Titles = ? " +
                "WHERE ID = ?";

        return jdbcTemplate.update( sql, new Object[] {
                player.getName(),
                player.getNationality(),
                new Timestamp(player.getBirthDate().getTime()),
                player.getTitles(),
                player.getId() }
        );
    }

    /**
     * DELETE method
     * @param id
     * @return
     */
    public int deletePlayerById(int id){
        String sql="DELETE FROM PLAYER WHERE ID = ?";
        return jdbcTemplate.update(sql, new Object[] {id});
    }

}
