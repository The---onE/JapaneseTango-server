package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by The_onE on 2016/9/13.
 */
public class Tango {
    public long id = -1;
    public String writing = "";
    public String pronunciation = "";
    public String meaning = "";
    public int tone = -1;
    public String partOfSpeech = "";
    public String image = "";
    public String voice = "";
    public int score = 0;
    public int frequency = 0;
    public Date addTime = new Date(0);
    public Date lastTime = new Date(0);
    public String flags = "";
    public int delFlag = 0;
    public String type = "";
    
    public Tango(ResultSet rs) throws SQLException {
    	id = rs.getInt("id");
    	writing = rs.getString("writing");
    	pronunciation = rs.getString("pronunciation");
		tone = rs.getInt("tone");
		meaning = rs.getString("meaning");
		partOfSpeech = rs.getString("part_of_speech");
    }
}
