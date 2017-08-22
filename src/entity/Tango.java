package entity;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import util.StrUtil;

/**
 * Created by The_onE on 2016/9/13.
 */
public class Tango extends BaseEntity {
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

	@Override
	public BaseEntity convert(ResultSet rs) throws SQLException {
		Tango t = new Tango();

		t.id = rs.getInt("id");
		t.writing = rs.getString("writing");
		t.pronunciation = rs.getString("pronunciation");
		t.tone = rs.getInt("tone");
		t.meaning = rs.getString("meaning");
		t.partOfSpeech = rs.getString("part_of_speech");

		return t;
	}

	@Override
	public InsertUtil insertValue() {
		InsertUtil i = new InsertUtil();
		Object[] params = { null, writing, pronunciation, tone, meaning, partOfSpeech };
		i.values = params;
		return i;
	}

	@Override
	public JSONObject convertToJSON() throws JSONException {
		JSONObject object = new JSONObject();
		try {
			// object.put("id", id);
			// object.put("writing", writing);
			// object.put("pronunciation", pronunciation);
			// object.put("tone", tone);
			// object.put("meaning", meaning);
			// object.put("partOfSpeech", partOfSpeech);
			Class<? extends Tango> c = this.getClass();
			Field[] fs = c.getDeclaredFields();
			for (Field f : fs) {
				f.setAccessible(true);
				String fieldName = f.getName();
				Object o;
				o = f.get(this);
				if (o != null) {
					object.put(fieldName, o);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	public String convertToCvs() {
		String strings[] = new String[] { writing, // 0
				pronunciation, // 1
				meaning, // 2
				String.valueOf(tone), // 3
				partOfSpeech, // 4
				image, // 5
				voice, // 6
				String.valueOf(score), // 7
				String.valueOf(frequency), // 8
				String.valueOf(addTime.getTime()), // 9
				String.valueOf(lastTime.getTime()), // 10
				flags, // 11
				String.valueOf(delFlag), // 12
				type // 13
		};
		String item = StrUtil.join(strings, ",");
		return item;
	}
}
