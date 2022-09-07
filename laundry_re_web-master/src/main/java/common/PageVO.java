package common;

public class PageVO {
	private int pageList = 10; //페이지당 보여질 목록수
	private int blockPage = 10; //블럭당 보여질 페이지의 수
	private int totalList; //총 목록수: DB에서 조회
	private int totalPage; //총 페이지수 : 8 페이지 = 30 / 4 = 7 ... 2
	private int totalBlock; //총 블록수 : 3 블록 = 8 / 3 = 2 ... 2
	private int curPage; //현재 페이지번호
	//각 페이지의 끝 목록번호 :  총 목록수 - (페이지번호-1) * 페이지당 보여질 목록수  
	//각 페이지의 시작 목록번호 :  끝 목록번호 - (페이지당 보여질 목록수-1)
	private int endList, beginList;
	private int curBlock; //현재 블록번호: 페이지번호 / 블록당 보여질 페이지수
	
	//각 블럭의 끝 페이지번호 : 블록번호 * 블록당 보여질 페이지수
	//각 블럭의 시작 페이지번호 : 끝 페이지번호 - (블럭당 보여질 페이지수-1)
	private int endPage, beginPage;
	//마지막 블럭에서 끝 페이지번호가 총 페이지수보다 클 수 없으므로
	//총 페이지수를 끝 페이지번호로 한다.

	private String search, keyword; //검색조건, 검색어
	private String viewType; //보기형태(리스트/그리드)
	
	//계산데이터를 넣는 필드, 데이터값 고정필드 는 setter 메소드가 없어도 된다
	//: blockPage, totalPage, totalBlock, endList, beginList
	//  curBlock, endPage, beginPage
	
	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getPageList() {
		return pageList;
	}

	public void setPageList(int pageList) {
		this.pageList = pageList;
	}

	public int getBlockPage() {
		return blockPage;
	}

	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}

	public int getTotalList() {
		return totalList;
	}

	public void setTotalList(int totalList) {
		this.totalList = totalList; //DB에서 조회해와 담는다
		
		//총목록수에 따라 총페이지수가 결정
		//총 페이지수 : 77 페이지 = 768/ 10 = 76 ... 8
		totalPage = totalList / pageList;
		if( totalList % pageList > 0 ) ++totalPage;
		
		//총 블록수 : 3 블록 = 총 페이지수 / 블럭당보여질페이지수 = 2 ... 2
		totalBlock = totalPage / blockPage;
		if( totalPage % blockPage > 0 ) ++totalBlock;
		
		//각 페이지의 끝 목록번호 :  총 목록수 - (페이지번호-1) * 페이지당 보여질 목록수  
		//각 페이지의 시작 목록번호 :  끝 목록번호 - (페이지당 보여질 목록수-1)
		endList = totalList - (curPage-1) * pageList;
		beginList = endList - (pageList-1);
		
		//현재 블록번호: 페이지번호 / 블록당 보여질 페이지수
		curBlock = curPage / blockPage;
		if( curPage % blockPage > 0 ) ++curBlock;
		
		//각 블럭의 끝 페이지번호 : 블록번호 * 블록당 보여질 페이지수
		//각 블럭의 시작 페이지번호 : 끝 페이지번호 - (블럭당 보여질 페이지수-1)
		endPage = curBlock * blockPage;
		beginPage = endPage - (blockPage-1);
		//마지막 블럭에서 끝 페이지번호가 총 페이지수보다 클 수 없으므로
		//총 페이지수를 끝 페이지번호로 한다.
		if( endPage > totalPage ) endPage = totalPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getEndList() {
		return endList;
	}

	public void setEndList(int endList) {
		this.endList = endList;
	}

	public int getBeginList() {
		return beginList;
	}

	public void setBeginList(int beginList) {
		this.beginList = beginList;
	}

	public int getCurBlock() {
		return curBlock;
	}

	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
}
