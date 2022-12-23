package com.example.demo01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo01.entity.Response;
import com.example.demo01.entity.Thread;
import com.example.demo01.entity.select.FollowThread;
import com.example.demo01.entity.select.MyThread;
import com.example.demo01.entity.select.PopularThread;
import com.example.demo01.entity.select.ThreadDetail;
import com.example.demo01.entity.select.ThreadList;
import com.example.demo01.entity.Comment;
import com.example.demo01.entity.Movie;
import com.example.demo01.service.MovieService;
import com.example.demo01.service.MyThreadService;
import com.example.demo01.service.ResponseService;
import com.example.demo01.service.ThreadService;
import com.example.demo01.service.ThreadDetail.ThreadDetailService;
import com.example.demo01.service.CommentService;
import com.example.demo01.service.FollowThreadService;
import com.example.demo01.service.PopularThreadService;
import com.example.demo01.service.ThreadList.ThreadListService;

@Controller
@RequestMapping("/Hiroba")
public class HirobaController {
    @Autowired
    CommentService commentservice;

    @Autowired
    MovieService movieservice;

    @Autowired
    ThreadService threadservice;

    @Autowired
    ResponseService responseservice;

    @Autowired
    PopularThreadService popularThreadservice;

    @Autowired
    FollowThreadService followthreadservice;

    @Autowired
    MyThreadService mythreadservice;

    @Autowired
    ThreadListService threadListService;

    @Autowired
    ThreadDetailService threadDetailService;

    @GetMapping
    public String showList(Model model) {
        Iterable<Response> popularmovie = responseservice.OrderPopular(); 
        Iterable<Response> newthread = responseservice.OrderNewThread();
        Iterable<PopularThread> popularthread = popularThreadservice.OrderPopularThread();
        Iterable<FollowThread> followthread = followthreadservice.OrderFollowThread();
        Iterable<MyThread> mythread = mythreadservice.OrderMyThread();

        model.addAttribute("title", "一覧");
        model.addAttribute("popularmovie", popularmovie);
        model.addAttribute("followthread", followthread);
        model.addAttribute("popularthread", popularthread);
        model.addAttribute("newthread", newthread);
        model.addAttribute("mythread", mythread);

        return "Hiroba";

    }

    /*
     * レスポンス詳細画面
     */
    @GetMapping("RessDatail/{url}")
    public String showRessDatail(Model model, @PathVariable("url") String url,
                                              @RequestParam("user_id") String user_id,
                                              @RequestParam("response_id") String response_id) {
        
        model.addAttribute("title", "登録");

        Iterable<Comment> list = commentservice.SelectComment(user_id, response_id);
        Iterable<Response> response = responseservice.SelectResponse(response_id,user_id);
        Iterable<Movie>  movie = movieservice.SelectMovie();
        Iterable<Thread> thread = threadservice.selectThread();
        
        model.addAttribute("list", list);
        model.addAttribute("movie", movie);
        model.addAttribute("thread", thread);
        model.addAttribute("response", response);
        model.addAttribute("url",url);

        return "RessDatail";
    }

    /*
     * スレッド詳細画面
     */
    @GetMapping("ThreadDetail")
    public String showThreadDetail(Model model, @RequestParam String thread_id) {
        Iterable<ThreadDetail> list_popular3 = threadDetailService.selectThreadDetail_Popular3(thread_id);
        Iterable<ThreadDetail> list = threadDetailService.selectThreadDetailAll(thread_id);

        model.addAttribute("list_popular3", list_popular3);
        model.addAttribute("list", list);
        model.addAttribute("thread_name", list.iterator().next().thread_name);
        return "RessDatail2";
    }


    /*
     * スレッド一覧画面
     */
    @GetMapping("ThreadList/{modeThread}")
    public String showThreadList(Model model, @PathVariable("modeThread") String modeThread) {
        String title = "";
        Iterable<ThreadList> list = null;

        String user_id = "U00000002";
        String genre_id = "J0001";

        switch(modeThread) {
            case "myThread":
                title = "自作スレッド一覧";

                //登録順の一覧
                list = threadListService.selectMyThread_OrderByRegist(user_id);
                model.addAttribute("myThread_OrderByRegist", list);

                //更新順の一覧
                list = threadListService.selectMyThread_OrderByUpdate(user_id);
                model.addAttribute("myThread_OrderByUpdate", list);
                
                break;

            case "followThread":
                title = "フォロースレッド一覧";

                //登録順の一覧
                list = threadListService.selectFollowThread_OrderByRegist(user_id);
                model.addAttribute("followThread_OrderByRegist", list);

                //更新順の一覧
                list = threadListService.selectFollowThread_OrderByUpdate(user_id);
                model.addAttribute("followThread_OrderByUpdate", list);

                break;

            case "genruThread":
                title = "ジャンルスレッド一覧";

                //人気順の一覧
                list = threadListService.selectGenreThread_OrderByPopular(genre_id);
                model.addAttribute("genreThread_OrderByPopular", list);

                //新着順の一覧
                list = threadListService.selectGenreThread_OrderByRegist(genre_id);
                model.addAttribute("genreThread_OrderByRegist", list);

                break;

            case "popularThread":
                title = "急上昇スレッド一覧";
                break;
        }

        model.addAttribute("modeThread", modeThread);
        model.addAttribute("title", title);

        return "ThreadList";
    }


}