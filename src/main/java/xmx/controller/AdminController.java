package xmx.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xmx.model.Tango;
import xmx.service.ITangoService;
import xmx.util.ObjectResult;
import xmx.util.PromptResult;

/**
 * 后台控制器
 * 
 * @author The_onE
 *
 */
@Controller
@RequestMapping("/Admin")
public class AdminController {
	/**
	 * 业务层
	 */
	@Resource
	private ITangoService tangoService;

	/**
	 * 后台首页
	 * 
	 * @return 首页逻辑视图
	 */
	@RequestMapping(value = "/index")
	public String ListTangoEntity() {
		return "admin/index";
	}

	/**
	 * 登录请求
	 * 
	 * @param name
	 *            用户名
	 * @param pwd
	 *            密码
	 * @param session
	 *            当前Session
	 * @return 登录信息
	 */
	@RequestMapping(value = "/Login", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String login(String name, String pwd, HttpSession session) {
		ObjectResult<Object> result = new ObjectResult<>();
		try {
			if (name != null && name.trim().length() > 0 && pwd != null && pwd.trim().length() > 0) {
				// Admin admin = adminService.login(name, pwd);
				Object admin = new Object();
				if (admin != null) {
					result.setStatus(1).setPrompt("登录成功").setObject(admin);

					session.setAttribute("admin", admin);
				} else {
					result.setStatus(-3).setPrompt("用户名或密码错误");
				}
			} else {
				result.setStatus(-2).setPrompt("用户名或密码不能为空");
			}
			return result.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-1).setPrompt("登录失败").toJson();
		}
	}

	/**
	 * 后台主页
	 * @return 后台主页界面
	 */
	@RequestMapping(value = "/main", produces = "text/html;charset=UTF-8")
	public String main() {
		return "admin/main";
	}

	/**
	 * 后台主页
	 * 
	 * @param session
	 *            当前Session
	 * @return 已登录返回后台主页界面，未登录返回登录界面
	 */
	@RequestMapping(value = "/addTango", produces = "text/html;charset=UTF-8")
	public String addTango() {
		return "admin/addTango";
	}

	/**
	 * 
	 * @param writing
	 * @param pronunciation
	 * @param meaning
	 * @param tone
	 * @param partOfSpeech
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/AddTango", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addTango(String writing, String pronunciation, String meaning, int tone, String partOfSpeech,
			String type) {
		PromptResult result = new PromptResult();
		try {
			Tango tango = new Tango();
			tango.setWriting(writing);
			tango.setPronunciation(pronunciation);
			tango.setMeaning(meaning);
			tango.setTone(tone);
			tango.setPartOfSpeech(partOfSpeech);
			tango.setType(type);

			int re = tangoService.addTango(tango);
			if (re > 0) {
				result.setStatus(1).setPrompt("添加成功");
			} else {
				result.setStatus(-2).setPrompt("添加失败");
			}

			return result.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-1).setPrompt("添加失败").toJson();
		}
	}
}
