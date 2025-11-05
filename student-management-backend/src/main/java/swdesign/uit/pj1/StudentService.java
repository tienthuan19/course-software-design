//                         _ooOoo_
//                        o8888888o
//                        88" . "88
//                        (| -_- |)
//                        O\  =  /O
//                     ____/`---'\____
//                   .'  \\|     |//  `.
//                  /  \\|||  :  |||//  \
//                 /  _||||| -:- |||||_  \
//                 |   | \\\  -  /'| |   |
//                 | \_|  `\`---'//  |_/ |
//                 \  .-\__ `-. -'__/-.  /
//               ___`. .'  /--.--\  `. .'___
//            ."" '<  `.___\_<|>_/___.' _> \"".
//           | | :  `- \`. ;`. _/; .'/ /  .' ; |
//           \  \ `-.   \_\_`. _.'_/_/  -' _.' /
// ===========`-.`___`-.__\ \___  /__.-'_.'_.-'================
//                       `=--=-'
//       CHĂM NGOAN - HỌC GIỎI - CODE ĐẸP - KHÔNG BUG 


//Hashmap<String, Student>

// Student
// -studentId (unique)
// -nationalID(unique)
// -name
// -dateOfBirth

// Student Management
// POST		|/student				:add a new student.
// GET		|/student/id			:get a student by student id.
// DELETE	|/student/id			:delete a student by student id.
// PUT		|/student/id			:update a student by student id.
// GET		|/student/national_id	:(search) get a student by national ID.
// GET		|/students/name			:(search) get a list of students by name (partial match).
// GET		|/students				:get all students.


// Student Service
// Logic xử lý truy xuất, thêm, xóa, sửa dữ liệu sinh viên trong database (hashmap).
package swdesign.uit.pj1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


@Service
public class StudentService {
    private final Map<String, Student> studentDatabase = new HashMap<>();

    // Add a new student to the database (hashmap)
    public Student addStudent(Student student) {
        //1. Check data is valid
        if (student.getStudentId() == null || student.getStudentId().isEmpty()) {
            return null;
        }

        //2. Check if studentId already exists
        if (studentDatabase.containsKey(student.getStudentId())) {
            return null;
        }

        //3. Add student to database
        studentDatabase.put(student.getStudentId(), student);
        return student;

    }
    
    // Get a student by student ID
    public Student getStudentById(String studentId) {
        return studentDatabase.get(studentId);
    }

    // Delete a student by student ID
    public Student deleteStudentById(String studentId) {
        return studentDatabase.remove(studentId);
    }
    // Update a student by student ID
    public Student updateStudent(String studentId, Student updatedStudent) {
        //Check if student exists
        if (studentDatabase.containsKey(studentId)) {
            // Update student details without changing studentId
            updatedStudent.setStudentId(studentId);
            studentDatabase.put(studentId, updatedStudent);
            return updatedStudent;
        }
        // Student not found
        return null; 
    }

    // Get a student by national ID
    public Student getStudentByNationalId(String nationalId) {
        for (Student student : studentDatabase.values()) {
            if (student.getNationalID().equals(nationalId)) {
                return student;
            }
        }
        // Student not found
        return null; 
        
    }

    // Get a list of students by name (partial match)
    public List<Student> getStudentsByName(String name) {
        // Dùng Stream API để lọc
        return studentDatabase.values().stream()
                .filter(student -> student.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Logic cho GET /students
    public List<Student> getAllStudents() {
        return new ArrayList<>(studentDatabase.values());
    }
}
