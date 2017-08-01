package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import common.Constants;
import dao.ITangoDao;
import dao.impl.TangoDao;
import entity.Tango;

/**
 * Servlet implementation class ListTangoEntity
 */
@WebServlet("/ListTangoEntity")
public class ListTangoEntity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ITangoDao tangoDao = new TangoDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListTangoEntity() {
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
		try {
			String type = request.getParameter("type");

			List<Tango> tangoList;
			if (type != null && !type.equals("")) {
				tangoList = tangoDao.selectByType(type);
			} else {
				tangoList = tangoDao.selectAll();
			}

			JSONObject res = Constants.createJSONResult(tangoList);

			writer.append(res.toString());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				JSONObject res = new JSONObject();
				res.put(Constants.RESPONSE_STATUS, 0);
				res.put(Constants.RESPONSE_PROMPT, "查询失败");
				writer.append(res.toString());
			} catch (JSONException ex) {
				ex.printStackTrace();
			}
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
