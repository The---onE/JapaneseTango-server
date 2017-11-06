package xmx.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	 * 
	 * @return 后台主页界面
	 */
	@RequestMapping(value = "/main", produces = "text/html;charset=UTF-8")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView("admin/main");
		List<Tango> list = tangoService.searchTango();
		mav.addObject("list", list);

		return mav;
	}

	/**
	 * 添加单语页
	 * 
	 * @param session
	 *            当前Session
	 * @return 添加单语界面
	 */
	@RequestMapping(value = "/addTango", produces = "text/html;charset=UTF-8")
	public String addTango() {
		return "admin/addTango";
	}

	/**
	 * 添加单语
	 * 
	 * @param writing
	 *            写法
	 * @param pronunciation
	 *            发音
	 * @param meaning
	 *            解释
	 * @param tone
	 *            音调
	 * @param partOfSpeech
	 *            词性
	 * @param type
	 *            类型
	 * @return 添加结果
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

	/**
	 * 删除单语
	 * 
	 * @param id
	 *            ID
	 * @return 删除结果
	 */
	@RequestMapping(value = "/DeleteTango", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String deleteTango(int id) {
		PromptResult result = new PromptResult();
		try {
			int re = tangoService.deleteTango(id);
			if (re > 0) {
				result.setStatus(1).setPrompt("删除成功");
			} else {
				result.setStatus(-2).setPrompt("删除失败");
			}

			return result.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-1).setPrompt("删除失败").toJson();
		}
	}
}
