package com.ltp.gradesubmission;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ltp.gradesubmission.pojo.Grade;
import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.service.GradeService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {
    @Mock
    private GradeRepository gradeRepository;
    
    @InjectMocks
    private GradeService gradeService;

    @Test
    public void getGradesFromRepoTest() {
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(
            new Grade("Harry", "Potions", "C-"),
            new Grade("Hermoine", "Charms", "A+")
        ));

        List<Grade> result = gradeService.getGrades();

        assertEquals("Harry", result.get(0).getName());
        assertEquals("Charms", result.get(1).getSubject());
    }

    @Test
    public void getGradeIndexTest(){
        List<Grade> students = Arrays.asList(
            new Grade("Harry", "Potions", "C-"),
            new Grade("Hermoine", "Charms", "A-")
        );
        when(gradeRepository.getGrades()).thenReturn(students);
        when(gradeRepository.getGrade(0)).thenReturn(students.get(0));
        when(gradeRepository.getGrade(1)).thenReturn(students.get(1));

        List<Grade> result = gradeService.getGrades();

        int valid = gradeService.getGradeIndex(result.get(0).getId());
        int invalid = gradeService.getGradeIndex("123");

        assertEquals(0, valid);
        assertEquals(Constants.NOT_FOUND, invalid);
    }

    @Test
    public void getGradeByIdTest() {
        List<Grade> student = Arrays.asList(
            new Grade("Harry", "Potions", "C-"),
            new Grade("Hermoine", "Charms", "A+")
        );
        when(gradeRepository.getGrades()).thenReturn(student);
        when(gradeRepository.getGrade(0)).thenReturn(student.get(0));
        when(gradeRepository.getGrade(1)).thenReturn(student.get(1));

        Grade found = gradeService.getGradeById(student.get(0).getId());
        Grade notFound = gradeService.getGradeById("123");

        assertEquals(found, student.get(0));
        assertEquals(null, notFound.getName());
        assertEquals(null, notFound.getSubject());
        assertEquals(null, notFound.getScore());
    }

    @Test
    public void addGradeTest() {
        List<Grade> student = Arrays.asList(
            new Grade("Harry", "Potions", "C-"),
            new Grade("Hermoine", "Charms", "A+")
        );
        when(gradeRepository.getGrades()).thenReturn(student);
        when(gradeRepository.getGrade(0)).thenReturn(student.get(0));
        when(gradeRepository.getGrade(1)).thenReturn(student.get(1));

        Grade newGrade = new Grade("Ron", "Arithmancy", "F");
        gradeService.submitGrade(newGrade);
        verify(gradeRepository, times(1)).addGrade(newGrade);
    }

    @Test
    public void updateGradeTest(){
        List<Grade> student = Arrays.asList(
            new Grade("Harry", "Potions", "C-"),
            new Grade("Hermoine", "Charms", "A+")
        );
        when(gradeRepository.getGrades()).thenReturn(student);
        when(gradeRepository.getGrade(0)).thenReturn(student.get(0));
        
        student.get(0).setScore("A-");
        gradeService.submitGrade(student.get(0));
        verify(gradeRepository, times(1)).updateGrade(student.get(0), 0);
    }
}
