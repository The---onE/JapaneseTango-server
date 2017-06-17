package service;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import bean.Tango;
import common.Constants;
import util.StrUtil;

/**
 * Servlet implementation class ListTango
 */
@WebServlet("/ListTango")
public class ListTango extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListTango() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");   //设置文本形式和字符编码
		response.setCharacterEncoding("UTF-8");
		Writer out=response.getWriter();  

		try {
			JSONObject res = new JSONObject();
			res.put(Constants.RESPONSE_STATUS, Constants.STATUS_QUERY_SUCCESS);
			res.put(Constants.RESPONSE_PROMPT, "获取成功");
			JSONArray entityList = new JSONArray();
			
			List<Tango> tangoList = new ArrayList<>();
			
			Tango t1 = new Tango();
			t1.id = -2;
			t1.writing = "大切";
			t1.pronunciation = "たいせつ";
			t1.tone = 0;
			t1.meaning = "重要,珍贵";
			t1.partOfSpeech = "形容动词";
			tangoList.add(t1);
			
			Tango t2 = new Tango();
	        t2.id = -3;
	        t2.writing = "ありがとうございます";
	        t2.pronunciation = "ありがとうございます";
	        t2.meaning = "谢谢";
	        t2.partOfSpeech = "惯用语";
	        tangoList.add(t2);
			
			for (Tango t : tangoList) {
				String strings[] = new String[]{
	                    t.writing, //0
	                    t.pronunciation, //1
	                    t.meaning, //2
	                    String.valueOf(t.tone), //3
	                    t.partOfSpeech, //4
	                    t.image, //5
	                    t.voice, // 6
	                    String.valueOf(t.score), //7
	                    String.valueOf(t.frequency), //8
	                    String.valueOf(t.addTime.getTime()), //9
	                    String.valueOf(t.lastTime.getTime()), //10
	                    t.flags, //11
	                    String.valueOf(t.delFlag), //12
	                    t.type //13
	            };
				String item = StrUtil.join(strings, ",");
				entityList.put(item);
			}
			
			res.put(Constants.RESPONSE_ENTITIES, entityList);
			out.append(res.toString());
		} catch (JSONException e) {
			e.printStackTrace();
			try {
				JSONObject res = new JSONObject();
				res.put(Constants.RESPONSE_STATUS, 0);
				res.put(Constants.RESPONSE_PROMPT, "获取失败");
				out.append(res.toString());
			} catch (JSONException ex) {
				ex.printStackTrace();
			}
		} finally {
			out.flush();
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
