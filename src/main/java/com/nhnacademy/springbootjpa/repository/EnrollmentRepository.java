package com.nhnacademy.springbootjpa.repository;

import com.nhnacademy.springbootjpa.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// TODO #4: Enrollment Entity에 대한 Repository interface
//          Enrollment Entity 매핑 내용에 따라 interface 를 수정하세요.

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findAllByStudentName(String studentIdName);

    // TODO #6: 강의 이름으로 수강신청 목록을 가져오는 쿼리가 실행되도록 메서드 이름을 정의하세요.
    List<Enrollment> findAllByCourseName(String courseName);

}
