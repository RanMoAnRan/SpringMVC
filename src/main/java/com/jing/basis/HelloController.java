package com.jing.basis;

import com.jing.pojo.User;
import com.jing.pojo.UserVO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName:HelloController
 * @Description TODO
 * @author:RanMoAnRan
 * @Date:2019/5/22 17:10
 * @Version 1.0
 */
@Controller
@RequestMapping("hello")//相当于添加了一个一级路径
public class HelloController {
    @RequestMapping("show")
    public ModelAndView test1() {
        ModelAndView mv = new ModelAndView();
        //设置要显示的页面：视图
        //mv.setViewName("/WEB-INF/views/hello.jsp");

        //设置视图名称:由于在配置文件中已经配置的视图的前缀和后缀，因此只要配置视图名称即可。
        mv.setViewName("hello");
        mv.addObject("msg", "springmvc");
        return mv;
    }

    //?标识可以匹配任意单个字符
    @RequestMapping(value = "show2?")
    public ModelAndView test2() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "ant风格的映射：?的使用");
        return mv;
    }

    //*可以匹配任意多个字符
    @RequestMapping("show3*")
    public ModelAndView test3() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "ant风格的映射：*的使用");
        return mv;
    }

    //**表示可以匹配任意多级路径
    @RequestMapping("show4/**")
    public ModelAndView test4() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "ant风格的映射：**的使用");
        return mv;
    }

    //使用占位符可以获取url中的数据
    @RequestMapping(value = "show5/{name}/{userId}")
    public ModelAndView test5(@PathVariable("name") String name, @PathVariable("userId") Integer userId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "占位符的映射:" + name + "..." + userId);
        return mv;
    }

    //限定请求方法为POST请求
    @RequestMapping(value = "show6", method = {RequestMethod.POST})
    public ModelAndView test6() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "限定请求方法的映射：post");
        return mv;
    }

    //限定多个请求方法
    @RequestMapping(value = "show7", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView test7() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "限定请求方法的映射：post/get");
        return mv;
    }


    @RequestMapping(value = "show8", params = {"userId"})
    public ModelAndView test8() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "限定请求参数的映射：必须有参数userId");
        return mv;
    }

    @RequestMapping(value = "show9", params = {"!userId"})
    public ModelAndView test9() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "限定请求参数的映射：必须没有参数userId");
        return mv;
    }

    @RequestMapping(value = "show10", params = {"userId=100"})
    public ModelAndView test10() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "限定请求参数的映射：userId的值必须为100");
        return mv;
    }

    @RequestMapping(value = "show11", params = {"userId!=100"})
    public ModelAndView test11() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "限定请求参数的映射：参数userId的值必须不能为100，也可以没有参数userId");
        return mv;
    }

    @RequestMapping(value = "show12", params = {"name=zhangsan", "userId!=100"})
    public ModelAndView test12() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "限定请求参数的映射：参数必须name为zhangsan并且userId不为100");
        return mv;
    }

    @GetMapping(value = "show13")
    public ModelAndView test13() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg", "注解限定请求参数的映射：GetMapping");
        return mv;
    }

    @PostMapping("show14")
    public ModelAndView test14() {
        ModelAndView mv = new ModelAndView("hello");//在构造函数中设置视图名称
        mv.addObject("msg", "注解限定请求参数的映射：PostMapping");
        return mv;
    }

    @DeleteMapping("show15")
    public ModelAndView test15() {
        ModelAndView mv = new ModelAndView("hello");//在构造函数中设置视图名称
        mv.addObject("msg", "注解限定请求参数的映射：@DeleteMapping");
        return mv;
    }

    @PutMapping("show16")
    public ModelAndView test16() {
        ModelAndView mv = new ModelAndView("hello");//在构造函数中设置视图名称
        mv.addObject("msg", "注解限定请求参数的映射：@PutMapping");
        return mv;
    }

    //ModelAndView的简写方式和不响应视图的方法的编写方式
    @RequestMapping("show17")
    public String test17(Model model) {
        model.addAttribute("msg", "优化后的代码");//添加数据
        return "hello";//springmvc默认将返回值最为视图名称
    }

    @RequestMapping("show18")
    @ResponseStatus(value = HttpStatus.OK)//如果不响应页面，就需要响应状态
    public void test18() {
        System.out.println("返回值是void");
    }

    //接收servlet常用的内置对象,只需要在方法形参中有该对象就能接收，不需要任何配置
    @RequestMapping("show19")
    public String test19(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        model.addAttribute("msg", request + "</br>" + response + "</br>" + session);
        return "hello";
    }

    //@RequestParam比之@PathVariable的区别是不需要在注解中使用占位符{xxx}
    @RequestMapping("test20")
    public String test20(Model model, @RequestParam(value = "name") String name) {
        model.addAttribute("msg", "使用@RequestParam接收到的参数为：" + name);
        return "hello";
    }

    //只有当手动设置required为false时，才可以不用传递参数
    @RequestMapping("test21")
    public String test21(Model model, @RequestParam(value = "name", required = false) String name) {
        model.addAttribute("msg", "使用@RequestParam接收到的参数为：" + name);
        return "hello";
    }

    //如果设置了defaultValue属性，那么表示可以不用传递参数，一旦不传递参数，就使用默认值
    //一旦设置了默认值，那么required=true就失效了，哪怕手动设置required为true也没用，照样可以不传递参数
    @RequestMapping("test22")
    public String test22(Model model, @RequestParam(value = "name", defaultValue = "lisi") String name) {
        model.addAttribute("msg", "使用@RequestParam接收到的参数为：" + name);
        return "hello";
    }


    //获取cookie  equalsIgnoreCase不区分大小写
    @RequestMapping("test23")
    public String test23(Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {//由于第一次访问，cookie是null,因此需要手动判断
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("jsessionid")) {
                    model.addAttribute("msg", "equalsIgnoreCase:" + cookie.getValue());
                }
            }
        }
        return "hello";
    }

    //注解方式获取cookie
    //如果是清除chrome缓存后发送请求，此时cookie中没有数据，因此也无法在参数中使用注解获取jsessionid,那么就会报400参数列表错误
    //此时当cookie中没有该属性时，可以设置参数非必须或者设置一个默认值即可
    @RequestMapping("test24")
    public String test24(Model model, @CookieValue(value = "JSESSIONID", required = false) String jsessionid) {
        model.addAttribute("msg", "JSESSIONID:" + jsessionid);
        return "hello";
    }

    //从user.html页面获取form表单数据
    @RequestMapping("test25")
    public String test25(Model model, @RequestParam("name") String name,
                         @RequestParam("age") Integer age,
                         @RequestParam("isMarry") Boolean isMarry,
                         @RequestParam("income") Float income,
                         @RequestParam("interests") String[] interests) {

        System.out.println(name);
        System.out.println(age);
        System.out.println(isMarry);
        System.out.println(income);
        System.out.println(Arrays.toString(interests));

        return "hello";
    }


    //参数使用User类型，可以自动封装表单数据
    //测试页面为user2.html
    @RequestMapping("test26")
    public String test26(Model model, User user) {
        model.addAttribute("msg", user);
        return "hello";
    }

    //集合的绑定
    //http://localhost:8080/hello/test27.do?ids=100&ids=120
    @RequestMapping("test27")
    public String test27(Model model, @RequestParam("ids") List<Long> ids) {
        model.addAttribute("msg", ids.toString());
        return "hello";
    }

    //集合中元素为pojo类型
    //测试页面user3.html
    @RequestMapping("test28")
    public String test28(Model model, UserVO userVO) {
        model.addAttribute("msg", "打印参数：" + userVO);
        return "hello";
    }


}
