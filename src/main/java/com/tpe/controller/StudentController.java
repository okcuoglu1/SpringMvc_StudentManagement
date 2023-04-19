package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller //Asagıdaki url ile gelen istekleri bu class karsılasın demis olduk.
@RequestMapping("/students")//http://localhost:8080/SpringMVC/students -> Sabit urlimden sonra /students ile gelen requestleri karşıla diye mappledik.
//class level:tüm methodlar için
// method level :sadece belirtilen method için mapping yapar
public class StudentController {

    @Autowired
    private StudentService service;

    //controllerdan requeste göre geriye ModelAndView(data+view name) veya
    //String tipinde view name döndürülür.


    //@RequestMapping("/students/hi")
    @GetMapping("/hi")
    public ModelAndView sayHi(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("message","Hi; ");
        mav.addObject("messagebody","I am a Student Management System");
        mav.setViewName("hi");//hi.jsp
        return mav;
    }
    //view resolver mav içindeki view name e göre hi.jsp dosyasını bulur.
    //mav içindeki model ı hi.jsp içerisine bind eder.


    //1-Student Creation
    //kullanıcıdan bilgileri almak için form göstrelim
    @GetMapping("/new")//http://localhost:8080/SpringMVC/students/new
    public String sendStudentForm(@ModelAttribute("student") Student student){
        return "studentForm";
    }

    //@ModelAttribute ann:Studentformdaki bilgilerle Student tipinde bir obje oluşturur,
    //daha sonra bu objenin kullanılmasını sağlar. view ile controller arasında data transferini sağlar




    // http://localhost:8080/SpringMVC/students/saveStudent, methodu : POST
    //Öğrenciyi kaydedelim ve kaydedilen öğrencilerin hepsini gösterelim
    @PostMapping("/saveStudent")
    public String createStudent(@ModelAttribute Student student){

        service.saveStudent(student);
        listAllStudent();



        return "redirect:/students" ; //request den requeste tekrar yönlendirmiş olduk. /students url'e yönlendir.
    }


    //Tüm studentları listeleyen request: http://localhost:8080/SpringMVC/students
    //2-read : Data okuma(read) işlemi gerçekleştiriceğimiz için get kullanıyoruz
    @GetMapping //http://localhost:8080/SpringMVC/students
    //jsp dosyalarının içine dataları koyucagımız icin ModelAndView data tipinde olmalı.

    public ModelAndView listAllStudent(){
      List<Student> students = service.getAll();
      ModelAndView mav = new ModelAndView();
      mav.addObject("studentList",students);
      mav.setViewName("students"); //students.jsp
        return mav;
    }





}