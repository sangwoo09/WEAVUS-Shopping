package com.example.weavusshoppingmall.controller;

import com.example.weavusshoppingmall.dto.UserDto;
import com.example.weavusshoppingmall.entity.User;
import com.example.weavusshoppingmall.repository.UserMapper;
import com.example.weavusshoppingmall.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final UserMapper userMapper;
    private final LoginService loginService;


    //todo 중요기록을 하는 명령어 현장방식이 존재함
    @GetMapping("/login")
    public String moveLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String id, String password, HttpServletRequest request, Model model){
        User user = userMapper.findByIdAndPassword(id, password);

        if (user != null){
            HttpSession session = request.getSession();//세션 사용 방법 (1
            session.setAttribute("user", user);// (2 통째로 넣어두 됨 ("sessionUserName",seresult)
            log.info("user={}", user);
            // 회원가입 완료시 로그인 화면에 회원가입 완료 문구 표시하
            return "redirect:/";
        }else {
            model.addAttribute("msg", "아이디 또는 비밀번호를 확인해주세요.");
            return "login";//컨트롤러를 재 구축하는 것->"redirect:/main" **
        }
    }

    @GetMapping("/logout")
    private String logout(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        session.invalidate();
        model.addAttribute("msg", "로그아웃 되었습니다.");
        return "index";
    }

    @GetMapping("/signup")
    public String moveSignup(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(UserDto userDto, Model model){
        boolean result = loginService.signup(userDto);
        if (result){
            model.addAttribute("msg","회원가입이 완료 되었습니다.");
            return "redirect:/login";
        }else {
            model.addAttribute("msg", "한번 더 확인해 주세요.");
            return "signup";
        }
    }

    @GetMapping("/idcheck")
    private String moveIdCheck(){
        return "idcheck";
    }

    @PostMapping("/idcheck")
    private String idcheck(String id, Model model){
        User user = userMapper.findById(id);
        if (user != null){
            model.addAttribute("msg","중복된 아이디 입니다.");
        }else {
            model.addAttribute("msg","사용 가능한 아이디 입니다.");
        }
        return "idcheck";
    }
}
