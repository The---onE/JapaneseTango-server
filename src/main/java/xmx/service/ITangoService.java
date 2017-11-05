package xmx.service;

import java.util.List;

import xmx.model.Tango;
import xmx.model.TinyTango;

/**
 * 单语服务接口
 * 
 * @author The_onE
 *
 */
public interface ITangoService {
	List<TinyTango> searchTinyTango();
	
	List<TinyTango> searchTinyTango(String type);
	
	List<Tango> searchTango();
	
	List<Tango> searchTango(String type);
	
	int addTango(Tango tango);
}
