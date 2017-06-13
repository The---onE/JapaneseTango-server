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
import utils.StrUtil;

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
		response.setContentType("text/html");   //�����ı���ʽ���ַ�����
		response.setCharacterEncoding("UTF-8");
		Writer out=response.getWriter();  

		try {
			JSONObject res = new JSONObject();
			res.put(Constants.RESPONSE_STATUS, Constants.STATUS_QUERY_SUCCESS);
			res.put(Constants.RESPONSE_PROMPT, "��ȡ�ɹ�");
			JSONArray entityList = new JSONArray();
			
			Tango tango = new Tango();
			tango.id = -2;
			tango.writing = "����";
			tango.pronunciation = "��������";
			tango.tone = 0;
			tango.meaning = "��Ҫ,���";
			tango.partOfSpeech = "���ݶ���";
			
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
				res.put(Constants.RESPONSE_PROMPT, "��ȡʧ��");
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
