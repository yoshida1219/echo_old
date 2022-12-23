package com.example.demo01.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo01.entity.User;
import com.example.demo01.entity.select.SelectFollowerMovie;
import com.example.demo01.form.CreateUserForm;
import com.example.demo01.form.LoginForm;
import com.example.demo01.service.HomeService;
import com.example.demo01.service.UserService;

@Controller
@RequestMapping("")
public class HomeController {
	@Autowired
	HomeService homeService;

	@Autowired
	UserService userService;

    @ModelAttribute
    public LoginForm setUpLoginForm() {
        LoginForm form = new LoginForm();
        
        return form;
    }

    @ModelAttribute
    public CreateUserForm setUpCreateUserForm() {
        CreateUserForm form = new CreateUserForm();
        
        return form;
    }

    /*
     * URL : /
     * 
     * 初期画面
     */
    @GetMapping("/")
    public String start() {
        return "login";
    }

    /*
     * URL : /createUser
     * 
     * アカウント作成画面
     */
    @GetMapping("/createUser")
    public String showcCreateUser() {
        return "loginNew";
    }

    /*
     * URL : /createUser/complete
     * 
     * アカウント作成
     * 
     * 正常に登録できた　-> / 初期画面にリダイレクト
     * 登録できなかった -> アカウント作成画面を表示
     */
    @PostMapping("/createUser/complete")
    public String createUser(@Validated CreateUserForm form, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "loginNew";
        }

        if(!checkMail(form.getMail())) {
            model.addAttribute("mailError", "メールアドレスはすでに使われています");
            return "loginNew";
        }

        if(!checkSearchName(form.getSearch_name())) {;
            model.addAttribute("searchNameError", "このIDはすでに使われています");
            return "loginNew";
        }

        User user = makeUser(form);
        userService.insertUser(user);

        redirectAttributes.addFlashAttribute("complete", "アカウントを作成しました");
        return "redirect:/";
    }

    /*
     * URL : /login
     * 
     * ログイン画面
     */
    @GetMapping("/login")
    public String showLogin() {
        return "login2";
    }

    @PostMapping("/login/complete")
    public String login(@Validated LoginForm form, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        String search_name = "@" + form.getSearch_name();
        String pass = form.getPass();

        if(result.hasErrors()) {
            return "login2";
        }

        if(!login(search_name, pass)) {
            model.addAttribute("error", "ログインに失敗しました");
            return "login2";
        }

        return "redirect:/home";
    }

    /*
     * URL : /home
     * 
     * ホーム画面
     */
    @GetMapping("/home")
    public String showHome(Model model) {

        

        

        Iterable<SelectFollowerMovie> list = homeService.selectFollowerMovie();
        model.addAttribute("timeline", list);

        return "home";
    }



    /*
     * ログイン処理
     * 
     * 正常にログインできるとき -> true
     * ログインできないとき -> false
     */
    private boolean login(String search_name, String pass) {
		Optional<User> user = userService.selectUserOne(search_name);

        //ユーザが見つからない
		if (!user.isPresent()) {
			return false;
		}

        //パスワードが違う
		if (!user.get().getPassword().equals(pass)) {
			return false;
		}

        return true;
    }

    /*
     * CreateUserForm から User への詰め替え
     */
    private User makeUser(CreateUserForm form) {
        User user = new User();

        user.setSearch_name("@" + form.getSearch_name());
        user.setUser_name(form.getUser_name());
        user.setMail(form.getMail());
        user.setPassword(form.getPass());

        String user_id = userService.selectUserIdMax();
        user.setUser_id(createId(user_id));

        return user;
    }

    /*
     * mail の重複チェック
     * 
     * していない -> true
     * 重複している -> false
     */
    private boolean checkMail(String mail) {
        Optional<String> mailOpt = userService.selectMail(mail);
        if(mailOpt.isPresent()) {
            return false; 
        }
        return true;
    }

    /*
     * search_name の重複チェック
     * 
     * していない -> true
     * 重複している -> false
     */
    private boolean checkSearchName(String search_name) {
        Optional<String> searchNameOpt = userService.selectSearchName(search_name);
        if(searchNameOpt.isPresent()) {
            return false; 
        }
        return true;
    }

    /*
     * Id の生成
     */
    private String createId(String id) {
        String id_head = id.substring(0, 1);
        int id_length = id.length();
        id = id.substring(1, id.length());

        String format = "%0" + Integer.toString(id_length-1) + "d";
        int id_int = Integer.parseInt(id) + 1;

        id = String.format(format, id_int);
        id = id_head + id;

        return id;
    }
}
