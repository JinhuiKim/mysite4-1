package kr.ac.sungkyul.mysite.service;

import static java.lang.Math.toIntExact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.sungkyul.mysite.dao.BoardDao;
import kr.ac.sungkyul.mysite.vo.BoardVo;


@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;

	public void list(String kwd, Long pageNo){
		List<BoardVo> pageList = boardDao.getAll(kwd);
		List<BoardVo> list = boardDao.getList(pageNo, kwd);
	
		int totalPage;
		int sPage;
		int ePage;
	
		if(pageList.size()% 10 == 0){
			totalPage = pageList.size() / 10 ;//
		
		}else{
			totalPage = (pageList.size() / 10)+ 1;
		}
		int pPage = totalPage / 5 ;
		int countPage = (toIntExact(pageNo)-1)/5;
		boolean prePage = true;
		boolean nextPage = true;
		
		//시작페이지 구하기
		if(countPage == 0){
			sPage = 1;
			prePage=false;
		}else {
			sPage = (countPage*5)+1;
		}
		
		//마지막페이지 구하기
		if(countPage==pPage){
			ePage = totalPage;
			nextPage=false;
		}else{
			ePage = sPage+4;
		}

	/*	request.setAttribute("list", list);// 이름 지정과 객체
		request.setAttribute("pageList", pageList);// 이름 지정과 객체
		request.setAttribute("sPage", sPage);
		request.setAttribute("ePage", ePage);
		request.setAttribute("prePage", prePage);
		request.setAttribute("nextPage", nextPage);
		request.setAttribute("kwd", kwd);
		return"/board/list";*/

	}
	}

