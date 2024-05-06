package autonoleggio.dao;

import java.util.List;

public interface IBaseDao<T> {
	
	List<T> list() throws Exception;;
	
	T get(String text);
	
	void insert(T o) throws Exception;
	
	void delete(T o);
	
	void update(T o) throws Exception;
	
	// warning

}
