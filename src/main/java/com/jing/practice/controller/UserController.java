package com.jing.practice.controller;

import com.jing.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.font.MultipleMaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:UserController
 * @Description TODO
 * @author:RanMoAnRan
 * @Date:2019/5/23 16:27
 * @Version 1.0
 */
@Controller
@RequestMapping("user")
//@RestController//声明所有的方法都使用json格式进行响应  下面方法上就可以不使用@ResponseBody注解

public class UserController {
    //使用jstl标签在页面上显示数据
    @RequestMapping("test1")
    public String test1(Model model) {
        ArrayList<User> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setId(i + 1l);
            user.setUsername("jingge" + i);
            user.setName("靖哥" + i);
            user.setAge(20 + i);
            list.add(user);
        }
        model.addAttribute("users", list);
        return "userList";
    }


    //@ResponseBody	是把Controller方法返回值转化为JSON，称为序列化
    //@RequestBody	是把接收到的JSON数据转化为Pojo对象，称为反序列化


    /**
     * @param model
     * @return java.util.List<com.jing.pojo.User>
     * @Description //TODO 将集合转化为json数据
     * @Date 17:04 2019/5/23
     **/
    @RequestMapping("test2")
    @ResponseBody//将数据响应成json格式的数据
    public List<User> test2(Model model) {
        ArrayList<User> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setId(i + 1l);
            user.setUsername("jingge" + i);
            user.setName("靖哥" + i);
            user.setAge(20 + i);
            list.add(user);
        }
        return list;
    }

    /**
     * @Description //TODO 将提交的json格式的数据封装到user对象中
     * @Date 17:11 2019/5/23
     * @param model
     * @param user
     * @return java.lang.String
     **/
    @RequestMapping("test3")
    public String test3(Model model, @RequestBody User user) {
        model.addAttribute("msg", user);
        return "hello";
    }

    //用String类型接收
    @RequestMapping("test4")
    public String test4(Model model, @RequestBody String user) {
        model.addAttribute("msg", user);
        return "hello";
    }


    //单个文件上传
    @RequestMapping("test5")
    public String test5(Model model, @RequestParam("file")MultipartFile file) throws IOException {
        if (file!=null) {
            //将文件存储到指定路径
            file.transferTo(new File("f://upload//"+file.getOriginalFilename()));
        }
        model.addAttribute("msg","上传成功");
        return "hello";
    }


    //上传多个文件
    @RequestMapping("test6")
    public String test6(Model model, @RequestParam("file")MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            if (file!=null) {
                //将文件存储到指定路径
                file.transferTo(new File("f://upload//"+file.getOriginalFilename()));
            }
        }
        model.addAttribute("msg","上传成功");
        return "hello";
    }

    //转发
    @RequestMapping("test7")
    public String test7() {
        return "forward:test9.do?id=101&type=forword";
    }

    //重定向
    @RequestMapping(value="test8")
    public String test8() {
        return "redirect:test9.do?id=101&type=redirect";
    }

    @RequestMapping(value="test9")
    public String test9(Model model,@RequestParam("id")Long id,@RequestParam("type")String type) {
        model.addAttribute("msg", "是转发还是重定向？"+id+"..."+type);
        return "hello";
    }


    //自定义拦截器测试
    @RequestMapping(value="test10")
    public String test10() {
        System.out.println("自定义处理器正在执行！！");
        return "hello";
    }

    //post乱码处理
    @RequestMapping("test11")
    public String test11(Model model ,@RequestParam("name") String name) {
        System.out.println(name);
        model.addAttribute("msg",name);
        return "hello";
    }








}
