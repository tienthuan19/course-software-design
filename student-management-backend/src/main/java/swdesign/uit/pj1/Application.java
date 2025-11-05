
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
// POST		|/student				        :create a new student.                                      x
// GET		|/student/id		        	:get a student by student id.                               x
// DELETE	|/student/id			        :delete a student by student id.                            x
// PUT		|/student/id			        :update a student by student id.                            x
// GET		|/student/national/nationalId	:(search) get a student by national ID.
// GET		|/students				        :get all students.

package swdesign.uit.pj1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
