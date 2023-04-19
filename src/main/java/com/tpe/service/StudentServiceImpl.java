package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exceptions.ResourceNotFound;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //Bu classdan bean olusturulur. Service class ı oldugunu belirtmiş olduk.
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository repository;

    @Override
    public void saveStudent(Student student) {
        repository.save(student);

    }

    @Override
    public List<Student> getAll() {
//        List<Student> studentList =  repository.findAll();
//        return studentList;
        return repository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
       Student student =  repository.findById(id)
               .orElseThrow(()-> new ResourceNotFound("Student not Found by id : " + id)); //Eğer optionalın içi boş ise custom olarak exception fırlatmak istersek.
        //.get() de kullanabilirdik No Such Element exception döner. Biz kendi excep atmasını istediğimiz icin diğerini yaptık.
        return student; //student döndürücek eğer içi boşsa exception atıcak.
    }

    @Override
    public void deleteStudent(Long id) {

       Student student =  getStudentById(id); //İlk önce belirtilen id ile öğrenciyi bulduk. Eğer öğrenci var ise onu siliyoruz. Yok ise getStudentById exception atacak.
        repository.delete(student.getId());


    }
}
