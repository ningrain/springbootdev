package com.hh.springbootdev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/5/9
 * Time: 10:00
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Map upload(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        System.out.println(request.getHeader("Content-Type"));
        System.out.println("+++++++++++++++++++++++");
        Map<String, String> result = new HashMap();
        result.put("msg", "success");
        return result;
    }


}
