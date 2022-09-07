package store;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class StoreDAO implements StoreService {

	@Override
	public boolean store_join(StoreVO vo) {
		 return sql.insert("store.mapper.storejoin", vo)>0 ? true : false; }
	 
	
			@Autowired @Qualifier("ateam") private SqlSession sql;
	

	@Override
	public void store_update(StoreVO vo) {
		
		sql.update("store.mapper.update", vo);
	}

	@Override
	public void store_delete(StoreVO vo) {
		sql.delete("store.mapper.delete", vo);
	}

	@Override
	public StoreVO store_info(int storeid) {
		return sql.selectOne("store.mapper.info", storeid);
		
	}

	

}
