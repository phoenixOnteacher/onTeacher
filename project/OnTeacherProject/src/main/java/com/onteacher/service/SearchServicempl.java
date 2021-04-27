package com.onteacher.service;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SearchServicempl {

	@Autowired
	SearchDAO searchDAO;
	
	@Override
	public Search querysearchById(int searchId) throws Exception {
		return searchDAO.selectsearchById(searchId);
	}
}
