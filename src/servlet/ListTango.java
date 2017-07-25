package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import common.Constants;
import dao.ITangoDao;
import dao.impl.TangoDao;
import entity.Tango;

/**
 * Servlet implementation class ListTango
 */
@WebServlet("/ListTango")
public class ListTango extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ITangoDao tangoDao = new TangoDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListTango() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html"); // 设置文本形式和字符编码
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = response.getWriter();
		JSONObject res = new JSONObject();
		try {
			List<Tango> tangoList = tangoDao.selectAll();
			if (tangoList != null && tangoList.size() > 0) {
				res.put(Constants.RESPONSE_STATUS, Constants.STATUS_QUERY_SUCCESS);
				res.put(Constants.RESPONSE_PROMPT, "获取成功");
				JSONArray entityList = new JSONArray();

				for (Tango t : tangoList) {
					entityList.put(t.convertToCvs());
				}
				res.put(Constants.RESPONSE_ENTITIES, entityList);
			} else {
				res.put(Constants.RESPONSE_STATUS, Constants.STATUS_EXECUTE_SUCCESS);
				res.put(Constants.RESPONSE_PROMPT, "暂无数据");
			}

			writer.append(res.toString());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				res.put(Constants.RESPONSE_STATUS, 0);
				res.put(Constants.RESPONSE_PROMPT, "获取失败");
				writer.append(res.toString());
			} catch (JSONException ex) {
				ex.printStackTrace();
			}
		} finally {
			writer.flush();
			writer.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
