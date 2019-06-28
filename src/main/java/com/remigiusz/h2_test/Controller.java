package com.remigiusz.h2_test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/university")
public class Controller {

    @Autowired StudentRepo studentRepo;

    @GetMapping("/student")
    public ResponseEntity<List<Student>> student()
    {
        return ResponseEntity.ok(studentRepo.findAll());
    }


    @GetMapping("/student/{id}")
    public ResponseEntity<?> student(@PathVariable("id") long id)
    {
       Optional<Student> student=studentRepo.findById(id);
       if(!student.isPresent()) return new ResponseEntity<Error404Response>
               (new Error404Response("Nie znaleziono id="+id,HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
       else return ResponseEntity.ok(student);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudentByID(@PathVariable("id") long id)
    {
        if(studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            return ResponseEntity.ok("Usunieto");
        }
        else return new ResponseEntity<Error404Response>
                (new Error404Response("Nie znaleziono id="+id,HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
    }

    @PostMapping("/student")
    public void addNewStudentToDB(@RequestBody  Student student)  {
        studentRepo.save(student);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") long id,@RequestBody Student student) {

        if(studentRepo.existsById(id)) {

            Student student1=studentRepo.findById(id).get();
            System.out.println(student.toString());
            student1.setEmail(student.getEmail());
            student1.setFirst_name(student.getFirst_name());
            student1.setLast_name(student.getLast_name());
            studentRepo.save(student1);

            return ResponseEntity.ok("Zmieniono");

        }else
            return new ResponseEntity<Error404Response>(new Error404Response(
                    "Nie znaleziono id="+id,
                    HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);

    }

    @PostConstruct
    public void initData()
    {
        studentRepo.save(new Student(1l,"Jacek","Kowalski","Jaca@one.pl"));
        studentRepo.save(new Student(2l,"Paweł","Piotr","pepe@wp.pl"));
        studentRepo.save(new Student(3l,"Ania","Nowak","ania@post.com"));
    }




}
