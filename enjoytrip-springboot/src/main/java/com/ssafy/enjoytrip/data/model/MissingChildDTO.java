package com.ssafy.enjoytrip.data.model;

public class MissingChildDTO {
	int child_idx;
	String file2;		// 사진
	String nm;			// 이름
	String sexdstnDscd;	// 성별(남자 1, 여자 2)
	String etcSpfeatr;	// 신체 특징
	String age1;		// 실종 당시 나이
	String detailDate1;	// 시작 발생일
	String occrAdres;	// 발생 장소
	
	@Override
	public String toString() {
		return "MissingChildDTO [child_idx=" + child_idx + ", file2=" + file2 + ", nm=" + nm + ", sexdstnDscd="
				+ sexdstnDscd + ", etcSpfeatr=" + etcSpfeatr + ", age1=" + age1 + ", detailDate1=" + detailDate1
				+ ", occrAdres=" + occrAdres + "]";
	}

	public int getChild_idx() {
		return child_idx;
	}

	public void setChild_idx(int child_idx) {
		this.child_idx = child_idx;
	}

	public String getFile2() {
		return file2;
	}

	public void setFile2(String file2) {
		this.file2 = file2;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getSexdstnDscd() {
		return sexdstnDscd;
	}

	public void setSexdstnDscd(String sexdstnDscd) {
		this.sexdstnDscd = sexdstnDscd;
	}

	public String getEtcSpfeatr() {
		return etcSpfeatr;
	}

	public void setEtcSpfeatr(String etcSpfeatr) {
		this.etcSpfeatr = etcSpfeatr;
	}

	public String getAge1() {
		return age1;
	}

	public void setAge1(String age1) {
		this.age1 = age1;
	}

	public String getDetailDate1() {
		return detailDate1;
	}

	public void setDetailDate1(String detailDate1) {
		this.detailDate1 = detailDate1;
	}

	public String getOccrAdres() {
		return occrAdres;
	}

	public void setOccrAdres(String occrAdres) {
		this.occrAdres = occrAdres;
	}
	
	
}
