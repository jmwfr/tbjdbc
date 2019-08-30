package jdbctp.engineer;

import java.util.List;
import java.util.Map;

public interface IngenieurInterface {
	public void insert(List<Object> args);
	public void update(int id, Map<String, Object> args);
	public void delete(int id);
	public Ingenieur findById(int id);
	public List<Ingenieur> findAll();
}
