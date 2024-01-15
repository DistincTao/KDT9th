package com.jspminipjt.dto.board;

public class SearchCriteriaDto {
	private String searchWord;
	private String searchType;
	
	public SearchCriteriaDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchCriteriaDto(String searchWord, String searchType) {
		super();
		this.searchWord = searchWord;
		this.searchType = searchType;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	@Override
	public String toString() {
		return "SearchCriteria [searchWord=" + searchWord + ", searchType=" + searchType + "]";
	}
	
	
}
