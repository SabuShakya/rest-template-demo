package com.sabu.resttemplate.controller;


import com.sabu.resttemplate.aop.MethodLogging;
import com.sabu.resttemplate.dto.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class RestTemplateController {

    private static String BASE = "http://localhost:9090/api/v1";
    private static String STUDENT_BASE = BASE + "/student";

    private final RestTemplate restTemplate;

    public RestTemplateController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/getStudentsAsString")
    @MethodLogging
    public String getStudents() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(STUDENT_BASE, HttpMethod.GET, entity, String.class);
        log.info("RESPONSE::::::::::::::" + responseEntity);
        log.info("RESPONSE::::::::::::::" + responseEntity.getBody());
        return responseEntity.getBody();
    }

    @GetMapping("/getStudentList")
    @MethodLogging
    public List<Student> getStudentList() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(httpHeaders);

        ResponseEntity<Student[]> responseEntity = restTemplate.exchange(STUDENT_BASE, HttpMethod.GET, entity, Student[].class);
        log.info("RESPONSE::::::::::::::,{}", responseEntity);
        log.info("RESPONSE::::::::::::::{}", responseEntity.getBody());
        Student[] students = responseEntity.getBody();
        return Arrays.asList(students != null ? students : new Student[0]);

        // or create a wrapperClass
       /* public class StudentList {
            private List<Student> students;

            public StudentList() {
                students = new ArrayList<>();
            }

            // standard constructor and getter/setter
        }
        StudentList response = restTemplate.getForObject(
                        "http://localhost:8080/getStudents",
                        StudentList.class);
        List<Student> students = response.getStudents();
        */
    }

    @GetMapping("/getStudentById/{studentId}")
    public Student getStudentById(@PathVariable("studentId") Long studentId) {
        Map<String, Long> param = new HashMap<>();
        param.put("studentId", studentId);

        Student student = restTemplate.getForObject(STUDENT_BASE + "/byId/{studentId}", Student.class, param);
        log.info("STUDENT RESPONSE::::::::::::" + student);
        return student;
    }

    @PostMapping("/add")
    public void addStudent(@RequestBody Student student) {
        // returns response entity
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(STUDENT_BASE, student, Void.class);
        log.info("SAVE STUDENT RESPONSE:::::::::" + responseEntity);

//        HttpEntity<Student> request = new HttpEntity<>(student);
        // returns void or only whatever class is specified as return type
//        restTemplate.postForObject(STUDENT_BASE, request, Void.class);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateStudent(@RequestBody Student student) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Student> studentHttpEntity = new HttpEntity<>(student, httpHeaders);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(STUDENT_BASE.concat("/update"), HttpMethod.PUT, studentHttpEntity, Void.class);
        log.info("UPDATE RESPONSE ::::::::::: {}", responseEntity);
        return responseEntity;
//        or, if return type is void
//        restTemplate.put(STUDENT_BASE + "/update", student);
    }

    @DeleteMapping("/delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        restTemplate.delete(STUDENT_BASE + "/{studentId}", studentId);
    }

    @DeleteMapping("/deleteWithResponseEntity/{studentId}")
    public ResponseEntity<Void> deleteStudentWithResponseEntity(@PathVariable("studentId") Long studentId) {
        ResponseEntity<Void> responseEntity = restTemplate.exchange(STUDENT_BASE + "/{studentId}",
                HttpMethod.DELETE,
                null,
                Void.class,
                studentId);
        log.info("DELETE::::::::::::{}", responseEntity);
        return responseEntity;
    }

//    @GetMapping("/get/headers")
//    public HttpHeaders headForHeaders() {
//        return restTemplate.headForHeaders(STUDENT_BASE.concat("/byId"));
//    }
}
