package com.chan.springmvc.test;

import com.chan.springmvc.dao.EmployeeDao;
import com.chan.springmvc.entity.Employee;
import com.chan.springmvc.exception.UserException;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

/**
 * 测试SpringMVC
 *
 * @author Administrator
 */
@Controller
public class TestSpringMVC {

    @Resource(name = "employeeDao")
    private EmployeeDao employeeDao;

    @Resource(name = "messageSource")
    private ResourceBundleMessageSource messageSource;

    @RequestMapping(value = "/testConversionService", method = RequestMethod.POST)
    public String testConvert(@RequestParam("employee") Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @ResponseBody
    @RequestMapping(value = "/testJson", method = RequestMethod.GET)
    public Collection<Employee> getAllEmployee() {
        return employeeDao.getEmployees();
    }

    /**
     * HttpMessageConverter将请求Body转为@RequestBody注解的参数对应的类型，并传入
     * 将方法返回值传入ResponseBody直接返回
     *
     * @return String
     */
    @ResponseBody
    @RequestMapping(value = "/testHttpMessageConverter", method = RequestMethod.POST)
    public String testHttpMessageConverter(@RequestBody String requestBody) {
        System.out.println(requestBody);
        return "hello!" + new Date();
    }

    /**
     * HttpMessageConverter将ResponseEntity<byte[]>返回值转为文件
     *
     * @param session Session，获取ServletContext
     * @return ResponseEntity<byte [ ]>
     * @throws IOException IOException
     */
    @RequestMapping(value = "/testResponseEntity", method = RequestMethod.GET)
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        ServletContext servletContext = session.getServletContext();
        InputStream inputStream = servletContext.getResourceAsStream("/resource/1.jpg");
        ;
        byte[] body = null;
        try {
            body = new byte[inputStream.available()];
            inputStream.read(body);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Description", "attachment:filename=1.jpg");
        header.add("Content-Type", "image/jpeg");
        HttpStatus statusCode = HttpStatus.OK;

        return new ResponseEntity<byte[]>(body, header, statusCode);
    }

    @RequestMapping(value = "testI18n")
    public String testI18n(Locale locale) {
        String message = messageSource.getMessage("i18.testMessage", null, locale);
        System.out.println(message);
        return "i18nMessage";
    }

    @RequestMapping(value = "testFileUpload")
    public String testFileUpload(@RequestParam String desc, @RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        if (file.isEmpty()) {
            return "redirect:index.jsp";
        }

        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        System.out.println(desc);
        String path = request.getSession().getServletContext().getRealPath("/resource");
        System.out.println(path);
        File filePath = new File(path, fileName);

        if (!filePath.getParentFile().exists()) {
            return "redirect:index.jsp";
        }
        file.transferTo(filePath);

        return "success";
    }

    @RequestMapping(value = "/testExceptionHandler")
    public String testExceptionHandler(@RequestParam(name = "i") int i) {
        System.out.println("结果：" + (100 / i));
        return "success";
    }

    @RequestMapping("/testResponseStatusException")
    public String testResponseStatusException(@RequestParam(name = "i") int i) {
        if (i == 13) {
            throw new UserException("用户名密码不匹配");
        }

        String[] messages = new String[i];
        System.out.println(messages[8]);
        return "success";
    }
}
