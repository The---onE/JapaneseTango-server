package xmx.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import xmx.dao.TangoMapper;
import xmx.model.Tango;
import xmx.model.TangoExample;
import xmx.model.TinyTango;
import xmx.service.ITangoService;

/**
 * 单语服务实现
 * 
 * @author The_onE
 *
 */
@Service("tangoService")
public class TangoService implements ITangoService {

	@Resource
	private TangoMapper tangoDao;

	@Override
	public List<TinyTango> searchTinyTango() {
		TangoExample example = new TangoExample();
		List<Tango> list = tangoDao.selectByExample(example);
		
		return makeTinyTangoList(list);
	}

	@Override
	public List<TinyTango> searchTinyTango(String type) {
		TangoExample example = new TangoExample();
		example.or().andTypeEqualTo(type);
		List<Tango> list = tangoDao.selectByExample(example);
		
		return makeTinyTangoList(list);
	}

	private TinyTango makeTinyTango(Tango tango) {
		TinyTango tinyTango = new TinyTango();
		tinyTango.setWriting(tango.getWriting());
		tinyTango.setPronunciation(tango.getPronunciation());
		tinyTango.setMeaning(tango.getMeaning());
		tinyTango.setPartOfSpeech(tango.getPartOfSpeech());
		tinyTango.setTone(tango.getTone());

		return tinyTango;
	}

	private List<TinyTango> makeTinyTangoList(List<Tango> list) {
		List<TinyTango> tinyTangoList = new ArrayList<>();
		for (Tango tango : list) {
			TinyTango tinyTango = makeTinyTango(tango);
			tinyTangoList.add(tinyTango);
		}
		return tinyTangoList;
	}

	@Override
	public List<Tango> searchTango() {
		TangoExample example = new TangoExample();
		return tangoDao.selectByExample(example);
	}

	@Override
	public List<Tango> searchTango(String type) {
		TangoExample example = new TangoExample();
		example.or().andTypeEqualTo(type);
		return tangoDao.selectByExample(example);
	}

	@Override
	public int addTango(Tango tango) {
		return tangoDao.insertSelective(tango);
	}

	@Override
	public int deleteTango(int id) {
		return tangoDao.deleteByPrimaryKey(id);
	}

}
