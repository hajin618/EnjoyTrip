package com.ssafy.enjoytrip.notice.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.notice.model.NoticeDTO;
import com.ssafy.enjoytrip.util.PageNavigation;

public interface NoticeService {
	// 공지사항 작성
	public void createNotice(NoticeDTO noticeDto) throws Exception;

	// 공지사항 리스트 조회
	public List<NoticeDTO> listNotice(Map<String, String> param) throws Exception;

	// 공지사항 조회
	public NoticeDTO getNotice(int noticeIdx) throws Exception;

	// 공지사항 수정
	public void modifyNotice(NoticeDTO noticeDto) throws Exception;

	// 공지사항 삭제
	public void deleteNotice(int noticeIdx) throws Exception;

	// 네비게이션
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception;

	public void updateHit(int notice_idx);
	
}
