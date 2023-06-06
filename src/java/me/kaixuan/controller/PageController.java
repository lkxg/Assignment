package me.kaixuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PageController {
    @RequestMapping("/success")
    public String success(){
        return "success";
    }
    @RequestMapping("/yule")
    public String yule(){
        return "yule";
    }

     @RequestMapping("/comment")
    public String comment() {
         return "comment";
     }

     @RequestMapping("/newstype")
    public String newstype() {
         return "newstype";
     }

     @RequestMapping("/usermanage")
    public String usermanage() {
         return "usermanage";
     }

     @RequestMapping("/release")
    public String release() {
            return "release";
        }

    @RequestMapping("/reg")
    public String reg(){
        return "reg";
    }

    @RequestMapping("/dianying")
    public String dianying(){
        return "dianying";
    }

    @RequestMapping("/keji")
    public String keji(){
        return "keji";
    }

    @RequestMapping("/tiyu")
    public String tiyu(){
        return "tiyu";
    }

    @RequestMapping("/youxi")
    public String youxi(){
        return "youxi";
    }

    @RequestMapping("/ruanjian")
    public String ruanjian(){
        return "ruanjian";
    }

    @RequestMapping("/qita")
    public String qita(){
        return "qita";
    }
    @RequestMapping("/forgetpwd")
    public String forgetpwd(){
        return "forgetpwd";
    }
    @RequestMapping("/totest")
    public String test(){
        return "test";
    }
    @RequestMapping("/head")
    public String head(){
        return "head";
    }
    @RequestMapping("/search")
    public String search(){
        return "search";
    }
}
