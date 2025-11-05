
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
// POST		|/student				:create a new student.                                      x
// GET		|/student/id			:get a student by student id.                               x
// DELETE	|/student/id			:delete a student by student id.                            x
// PUT		|/student/id			:update a student by student id.                            x
// GET		|/student/national_id	:(search) get a student by national ID.
// GET		|/students/name			:(search) get a list of students by name (partial match).
// GET		|/students				:get all students.
package swdesign.uit.pj1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    // Dependency injection of StudentService
    // của Springboot để sử dụng các phương thức trong StudentService mà không cần khởi tạo thủ công.
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/student")
    public ResponseEntity<ApiResponse<Student>> addStudent(@RequestBody Student student) {
        // Gọi phương thức thêm sinh viên từ StudentService (đầu vào kiểu Student)
        // Đồng thời gán kết quả trả về cho biến createdStudent
        Student createdStudent = studentService.addStudent(student);

        if (createdStudent != null) {
            ApiResponse<Student> response = new ApiResponse<>(
                    HttpStatus.CREATED.value(),
                    "Tạo sinh viên thành công!",
                    createdStudent);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } else {
            ApiResponse<Student> response = new ApiResponse<>(
                    HttpStatus.CONFLICT.value(),
                    "Mã sinh viên " + student.getStudentId() +  " đã tồn tại.",
                    null);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable String studentId) {
        Student student = studentService.getStudentById(studentId);

        if (student != null) {
            ApiResponse<Student> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Lấy sinh viên thành công!",
                    student);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Student> response = new ApiResponse<>(
                    HttpStatus.NOT_FOUND.value(),
                    "Không tìm thấy sinh viên: " + studentId,
                    null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<Student>> deleteStudentById(@PathVariable String studentId) {
        Student deletedStudent = studentService.deleteStudentById(studentId);

        if (deletedStudent != null) {
            ApiResponse<Student> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Xóa sinh viên thành công!",
                    deletedStudent);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Student> response = new ApiResponse<>(
                    HttpStatus.NOT_FOUND.value(),
                    "Không tìm thấy sinh viên: " + studentId,
                    null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(@PathVariable String studentId,
            @RequestBody Student updatedStudent) {
        Student student = studentService.updateStudent(studentId, updatedStudent);
        if (student != null) {
            ApiResponse<Student> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Cập nhập sinh viên thành công",
                    student);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Student> response = new ApiResponse<>(
                    HttpStatus.NOT_FOUND.value(),
                    "Không tìm thấy sinh viên: " + studentId,
                    student);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/student/national/{nationalId}")
    public ResponseEntity<ApiResponse<Student>> getStudentByNationalId(@PathVariable String nationalId) {
        Student student = studentService.getStudentByNationalId(nationalId);

        if (student != null) {
            // TÌM THẤY
            ApiResponse<Student> response = new ApiResponse<>(
                    HttpStatus.OK.value(), // 200
                    "Sinh viên có số CMND " + nationalId,
                    student);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Student> response = new ApiResponse<>(
                    HttpStatus.NOT_FOUND.value(), // 404
                    "Không tìm thấy sinh viên: " + nationalId,
                    null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/students")
    public ResponseEntity<ApiResponse<List<Student>>> getStudents(@RequestParam(name = "name", required = false) String name) {
    
        List<Student> students;
    String message;

    if (name != null && !name.isEmpty()) {
        students = studentService.getStudentsByName(name);
        message = "";
    } else {
        students = studentService.getAllStudents();
        message = "";
    }
    ApiResponse<List<Student>> response = new ApiResponse<>(
            HttpStatus.OK.value(),
            message,
            students);
    return ResponseEntity.ok(response);
}



    @GetMapping("/students/all")
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        
        ApiResponse<List<Student>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "",
                students
        );
        return ResponseEntity.ok(response);
    }
}
