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
// POST		|/student				:create a new student.
// GET		|/student/id			:get a student by student id.
// DELETE	|/student/id			:delete a student by student id.
// PUT		|/student/id			:update a student by student id.
// GET		|/student/national_id	:(search) get a student by national ID.
// GET		|/students/name			:(search) get a list of students by name (partial match).
// GET		|/students				:get all students.
package swdesign.uit.pj1;

public class ApiResponse<T> {

    private int status; // Sẽ là mã HTTP (ví dụ: 200, 404)
    private String message; // Thông điệp (ví dụ: "Success", "Student not found")
    private T data; // Dữ liệu bạn muốn trả về (kiểu T)

    public ApiResponse() {
    }

    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
    

}
