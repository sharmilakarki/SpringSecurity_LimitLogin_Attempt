/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.controller;

import com.sharmila.hibernatespringsecurity.dao.RoleDao;
import com.sharmila.hibernatespringsecurity.entity.Role;
import com.sharmila.hibernatespringsecurity.service.UserService;
import com.sharmila.hibernatespringsecurity.entity.User;
import com.sharmila.hibernatespringsecurity.entity.UserRoles;
import com.sharmila.hibernatespringsecurity.service.CustomUserDetailsService;
import com.sharmila.hibernatespringsecurity.service.UserRolesService;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.security.krb5.internal.Krb5;

/**
 *
 * @author sharmila
 */
@Controller
@RequestMapping(value = "/")
public class DefaultController {

    @Autowired
    private UserService userS;
    @Autowired
    private UserRolesService userRolesService;

    private CustomUserDetailsService userService;

    @Autowired
    private RoleDao roleDao;
    private Session session;

    public DefaultController() {
    }

    public DefaultController(UserRolesService userRolesService, RoleDao roleDao, Session session) {
        this.userRolesService = userRolesService;

        this.roleDao = roleDao;
        this.session = session;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView defaultPage(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        if (error != null) {
            model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
//            String username = request.getParameter("username");
//            User u = userS.getByUserName(username);
//            if (u != null) {
//                u.setAccountNonLocked(false);
//                userS.update(u);
//            }
//            HttpSession session = request.getSession(false);
//            session.setAttribute("activation-time", System.currentTimeMillis());

        }
        if (logout != null) {
            model.addObject("message", "Logged out successfully");
            request.getSession().invalidate();
        }

        model.setViewName("login");
        return model;
    }

    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);
        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username or password";

        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username or password";
        }
        return error;
    }

    @RequestMapping(value = "/user/SignupPage")
    public ModelAndView signup() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("Signup");
        return mv;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminProfile(Principal principal, ModelMap map) {
        String name = principal.getName();

        map.addAttribute("username", name);
        return "adminDashboard";
    }

    @RequestMapping(value = {"/userprofile"})
    public ModelAndView userProfile() {

        return new ModelAndView("profile");
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView errorPage() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("error page");
        return model;
    }

    @RequestMapping(value = "/admin/AllUsers")
    public ModelAndView getUsers() {

        return new ModelAndView("AllUsers", "user", userS.getAll());
    }

    @RequestMapping(value = "user/add", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user, BindingResult result) {
        System.out.println("inside insert");

        Role role = roleDao.getById(2);
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);
        user.setRole(roles);
        user.setStatus(true);
        user.setAccountNonExpired(Boolean.TRUE);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        userS.insert(user);
//            System.out.println("Users "+user.toString());

        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "admin/editUser", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("userAdd") User user, BindingResult result, @RequestParam("id") int id) {
        String view = "";
        User u = userS.getById(id);
        if (u.getId() != 0) {
            System.out.println("ok");
            Date date = new Date();
            Timestamp t = new Timestamp(date.getTime());
            user.setModifiedDate(t);

            userS.update(user);

        }
        System.out.println(u.getId() + "hhh");
        return "redirect:/admin/AllUsers";
    }

    @RequestMapping(value = "delete")
    public ModelAndView deleteUser(@RequestParam int id) {
        userS.delete(id);
        return new ModelAndView("redirect:/admin/AllUsers");
    }

    @RequestMapping(value = "edit")
    public ModelAndView editUser(@RequestParam int id, @ModelAttribute("userEdit") User user, @ModelAttribute UserRoles userRoles) {
        user = userS.getById(id);

        return new ModelAndView("editUser", "user", user);
    }

    @RequestMapping("/info")
    public String info() {
        return "InformationPage";
    }

}
