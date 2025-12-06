
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


//Hashmap<String, Student>

// Student
// -studentId (unique)
// -nationalID(unique)
// -name
// -dateOfBirth

// Student Management
// POST		|/student				        :create a new student.                                      x
// GET		|/student/id		        	:get a student by student id.                               x
// DELETE	|/student/id			        :delete a student by student id.                            x
// PUT		|/student/id			        :update a student by student id.                            x
// GET		|/student/national/nationalId	:(search) get a student by national ID.
// GET		|/students				        :get all students.
package swdesign.uit.pj1;

public class ApiResponse<T> {

    private int status; 
    private String message;
    private T data; 

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
