package springdata.tennisplayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

//PlayerDao to interact with the database
@Repository
public class PlayerDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Select Method
     * @return
     */
    public List<Player> getAllPlayers(){
        String sql = "SELECT * FROM PLAYER";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Player>(Player.class));
    }

    public Player getPlayerById(int id){
        String sql = "SELECT * FROM PLAYER WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql,
                                            new BeanPropertyRowMapper<Player>(Player.class),
                                            new Object[] {id});
    }

}
