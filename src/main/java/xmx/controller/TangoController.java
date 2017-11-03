package xmx.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xmx.model.TinyTango;
import xmx.service.ITangoService;
import xmx.util.TinyTangoListResult;

/**
 * 后台控制器
 * 
 * @author The_onE
 *
 */
@Controller
@RequestMapping("/")
public class TangoController {
	/**
	 * 业务层
	 */
	@Resource
	private ITangoService tangoService;

	/**
	 * 查询单语
	 * 
	 * @param type
	 *            类型
	 * @return 单语列表
	 */
	@RequestMapping(value = "/ListTangoEntity", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String ListTangoEntity(String type) {
		TinyTangoListResult result = new TinyTangoListResult();
		try {
			List<TinyTango> list;
			if (type != null && type.trim().length() > 0) {
				list = tangoService.searchTinyTango(type);
			} else {
				list = tangoService.searchTinyTango();
			}
			if (list != null && list.size() > 0) {
				result.setStatus(1).setList(list).setPrompt("查询成功").toJson();
			} else {
				result.setStatus(2).setPrompt("没有数据").toJson();
			}
			return result.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			return result.setStatus(-1).setPrompt("查询失败").toJson();
		}
	}
}
