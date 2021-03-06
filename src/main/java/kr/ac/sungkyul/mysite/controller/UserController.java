package kr.ac.sungkyul.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.sungkyul.mysite.service.UserService;
import kr.ac.sungkyul.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping("/joinform")
	public String joinform() {

		return "user/joinform";
	}

	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo vo) {
		userService.join(vo);
		return "redirect:/user/joinsuccess"; // redirect해야함
	}

	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess"; // redirect해야함
	}

	@RequestMapping("/loginform")
	public String loginform() {

		return "user/loginform";
	}

	/*
	 * @RequestMapping("/login") public String login(@MoelAttribute String
	 * email, String password ){ userService.login(email, password); return
	 * "/main"; }
	 */

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session,
			@RequestParam(value = "email", required = false, defaultValue = "") String email,
			@RequestParam(value = "password", required = false, defaultValue = "") String password) {

		UserVo authUser = userService.login(email, password);
		if (authUser == null) {
			return "redirect:/user/loginform";
		}
		session.setAttribute("authUser", authUser);
		return "redirect:/main";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate(); //
		return "redirect:/main";
	}

	@RequestMapping("/modifyform")
	public String modifyform(HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		userService.getUserInfo(authUser.getNo());
		return "user/modifyform";

	}

	@RequestMapping(value="/modify", method=RequestMethod.POST)
//	public String modify(HttpSession session,@RequestParam(value="name", required=false, defaultValue="") String name, @RequestParam(value="password", required=false, defaultValue="") String password, @RequestParam(value="gender", required=false, defaultValue="") String gender ){

	public String modify(HttpSession session, @ModelAttribute UserVo vo) {

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		vo.setNo(authUser.getNo());
		userService.updateInfo(vo);

		authUser.setName(vo.getName());
		return "redirect:/main";

	}

}
