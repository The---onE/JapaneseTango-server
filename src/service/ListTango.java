package service;

import java.io.IOException;
import java.io.Writer;

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
			
			Tango tango = new Tango();
			tango.id = -2;
			tango.writing = "大切";
			tango.pronunciation = "たいせつ";
			tango.tone = 0;
			tango.meaning = "重要,珍贵";
			tango.partOfSpeech = "形容动词";
			
			String strings[] = new String[]{
                    tango.writing, //0
                    tango.pronunciation, //1
                    tango.meaning, //2
                    String.valueOf(tango.tone), //3
                    tango.partOfSpeech, //4
                    tango.image, //5
                    tango.voice, // 6
                    String.valueOf(tango.score), //7
                    String.valueOf(tango.frequency), //8
                    String.valueOf(tango.addTime.getTime()), //9
                    String.valueOf(tango.lastTime.getTime()), //10
                    tango.flags, //11
                    String.valueOf(tango.delFlag), //12
                    tango.type //13
            };
			String item = StrUtil.join(strings, ",");
			entityList.put(item);
			
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
