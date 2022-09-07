package store;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StoreServiceImpl implements StoreService {

	@Override
	public boolean store_join(StoreVO vo) {
		return dao.store_join(vo);
		}

		@Autowired private StoreDAO dao;


	@Override
	public void store_update(StoreVO vo) {
		dao.store_update(vo);
	}

	@Override
	public void store_delete(StoreVO vo) {
		dao.store_delete(vo);
	}

	@Override
	public StoreVO store_info(int storeid) {
		return dao.store_info(storeid);
		
	}


}
