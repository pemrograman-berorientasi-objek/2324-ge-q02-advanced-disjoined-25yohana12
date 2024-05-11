package academic.driver;

import academic.model.Course;
import academic.model.CourseOpening;
import academic.model.Detail;
import academic.model.Enrollment;
import academic.model.Lecturer;
import academic.model.Student;
import academic.model.Best;

import java.util.*;

/**
 * @author 12S22003 Yohana Natalia Siahaan
 */
public class Driver1 {

    public static void main(String[] _args) {

        ArrayList<Course> course = new ArrayList<Course>();
        ArrayList<Lecturer> lecturer = new ArrayList<Lecturer>();
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        ArrayList<Detail> details = new ArrayList<Detail>();
        ArrayList<Detail> detailsTranscript = new ArrayList<Detail>();
        ArrayList<CourseOpening> courseOpenings = new ArrayList<CourseOpening>();
        ArrayList<Best> best = new ArrayList<Best>();

        Scanner scan = new Scanner(System.in);
        String str;

        while (scan.hasNextLine()) {
            str = scan.nextLine();

            if (str.equals("---")) {
                break;
            } else {
                String[] tokens = str.split("#");

                switch (tokens[0]) {

                    case "student-add":
                        boolean studentExists = false;
                        for (Student existingStudent : students) {
                            if (existingStudent.getId().equals(tokens[1])) {
                                studentExists = true;
                                break;
                            }
                        }
                        if (!studentExists) {
                            students.add(new Student(tokens[1], tokens[2], tokens[3], tokens[4]));
                        }
                        break;

                    case "student-details":
                        String studentId = tokens[1];
                        double totalGpa = 0.0;
                        int totalCredit = 0;
                        double gpa = 0.0;
                        double grade = 0;

                        List<String> crs = new ArrayList<>();
                        List<String> grd = new ArrayList<>();

                        for (Enrollment existingEnrollment : enrollments) {
                            if (existingEnrollment.getStudentID().equals(studentId)
                                    && existingEnrollment.getGrade() != "None") {
                                if (existingEnrollment.getCek() == 1) {
                                    grd.add(existingEnrollment.getPerbaikan());
                                    crs.add(existingEnrollment.getCourseID());
                                } else {
                                    crs.add(existingEnrollment.getCourseID());
                                    grd.add(existingEnrollment.getGrade());
                                }
                            }
                        }

                        for (int i = 0; i < crs.size(); i++) {
                            for (int j = i + 1; j < crs.size(); j++) {
                                if (crs.get(i).equals(crs.get(j))) {
                                    crs.remove(i);
                                    grd.remove(i);
                                }
                            }
                        }

                        for (int i = 0; i < crs.size(); i++) {
                            for (int j = 0; j < course.size(); j++) {
                                if (crs.get(i).equals(course.get(j).getCode())) {
                                    totalCredit += course.get(j).getCredits();
                                    switch (grd.get(i)) {
                                        case "A":
                                            grade = 4.0 * course.get(j).getCredits();
                                            break;
                                        case "AB":
                                            grade = 3.5 * course.get(j).getCredits();
                                            break;
                                        case "B":
                                            grade = 3.0 * course.get(j).getCredits();
                                            break;
                                        case "BC":
                                            grade = 2.5 * course.get(j).getCredits();
                                            break;
                                        case "C":
                                            grade = 2.0 * course.get(j).getCredits();
                                            break;
                                        case "D":
                                            grade = 1.0 * course.get(j).getCredits();
                                            break;
                                        case "E":
                                            grade = 0.0 * course.get(j).getCredits();
                                            break;
                                    }
                                    totalGpa += grade;
                                }
                            }
                        }

                        if (totalCredit == 0) {
                            gpa = 0;
                        } else {
                            gpa = totalGpa / totalCredit;
                        }

                        for (Student student : students) {
                            if (student.getId().equals(studentId)) {
                                details.add(new Detail(student.getId(), student.getName(), student.getAngkatan(),
                                        student.getProdi(), gpa, String.valueOf(totalCredit)));
                            }
                        }
                        for (Student S : students) {
                            if (S.getId().equals(studentId)) {
                                System.out.println(S.toString() + "|" + String.format("%.2f", gpa) + "|" + totalCredit);
                            }
                        }

                        break;

                    case "lecturer-add":
                        boolean ExitLecturer = false;
                        for (Lecturer exitlecLecturer : lecturer) {
                            if (exitlecLecturer.getId().equals(tokens[1])) {
                                ExitLecturer = true;
                                break;
                            }
                        }
                        if (!ExitLecturer) {
                            lecturer.add(new Lecturer(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]));
                        }
                        break;

                    case "course-open":
                        CourseOpening courses = new CourseOpening(tokens[1], tokens[2], tokens[3], tokens[4]);
                        String[] lecturerIds = tokens[4].split(",");
                        for (String lecturerId : lecturerIds) {
                            for (Lecturer exitlecLecturer : lecturer) {
                                if (exitlecLecturer.getId().equals(lecturerId)) {
                                    break;
                                }
                            }
                        }
                        courseOpenings.add(courses);
                        break;

                    case "course-add":
                        boolean courseExists = false;
                        for (Course existingCourse : course) {
                            if (existingCourse.getCode().equals(tokens[1])) {
                                courseExists = true;
                                break;
                            }
                        }
                        if (!courseExists) {
                            course.add(new Course(tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4]));
                        }
                        break;

                    case "enrollment-grade":
                        for (int i = 0; i < enrollments.size(); i++) {
                            if (enrollments.get(i).getCourseID().equals(tokens[1])
                                    && enrollments.get(i).getStudentID().equals(tokens[2])
                                    && enrollments.get(i).getYear().equals(tokens[3])
                                    && enrollments.get(i).getSemester().equals(tokens[4])) {
                                enrollments.get(i).setGrade(tokens[5]);
                            }
                        }
                        break;

                    case "enrollment-add":
                        boolean foundExistingEnrollment = false;

                        if (!foundExistingEnrollment) {
                            enrollments.add(new Enrollment(tokens[1], tokens[2], tokens[3], tokens[4], ("None")));
                        }
                        break;

                    case "enrollment-remedial":
                        String remedial = tokens[5];
                        for (int i = 0; i < enrollments.size(); i++) {
                            if (enrollments.get(i).getCourseID().equals(tokens[1])
                                    && enrollments.get(i).getStudentID().equals(tokens[2])
                                    && !enrollments.get(i).getGrade().equals("None") && enrollments.get(i).getCek() == 0
                                    && enrollments.get(i).getYear().equals(tokens[3])
                                    && enrollments.get(i).getSemester().equals(tokens[4])) {
                                enrollments.get(i).setPerbaikan(remedial);
                                enrollments.get(i).setCek(1);
                            }
                        }
                        break;

                    case "course-history":
                        String courseCode = tokens[1];

                        // urutkan course opening berdasarkan semester
                        Collections.sort(courseOpenings, new Comparator<CourseOpening>() {
                            @Override
                            public int compare(CourseOpening o2, CourseOpening o1) {
                                if (o1.getYear().equals(o2.getYear())) {
                                    return o1.getSemester().compareTo(o2.getSemester());
                                } else {
                                    return o1.getSemester().compareTo(o2.getSemester());
                                }
                            }
                        });

                        for (Course existingCourse : course) {
                            for (CourseOpening existingCourseOpening : courseOpenings) {
                                if (existingCourse.getCode().equals(courseCode)
                                        && existingCourseOpening.getCourseId().equals(courseCode)) {
                                    System.out.print(existingCourse.getCode() + "|" + existingCourse.getName() + "|"
                                            + existingCourse.getCredits() + "|" + existingCourse.getGrade() + "|"
                                            + existingCourseOpening.getYear() + "|"
                                            + existingCourseOpening.getSemester() + "|");
                                    String[] lecturerInitials = existingCourseOpening.getLecturerInitials().split(",");
                                    for (int i = 0; i < lecturerInitials.length; i++) {
                                        for (Lecturer associatedLecturer : lecturer) {
                                            if (lecturerInitials[i].equals(associatedLecturer.getIntial())) {
                                                System.out.println(associatedLecturer.getIntial() + " ("
                                                        + associatedLecturer.getEmail() + ")");
                                                if (i < lecturerInitials.length - 1) {
                                                    System.out.print(";");
                                                }
                                            }
                                        }
                                    }

                                    for (Enrollment existingEnrollment : enrollments) {
                                        if (existingEnrollment.getCourseID().equals(courseCode)
                                                && existingEnrollment.getYear().equals(existingCourseOpening.getYear())
                                                && existingEnrollment.getSemester()
                                                        .equals(existingCourseOpening.getSemester())) {
                                            // print remedial (grade sebelumnya) jika pernah mengikuti remedial
                                            if (existingEnrollment.getPerbaikan() != "") {
                                                System.out.println(existingEnrollment.getCourseID() + "|"
                                                        + existingEnrollment.getStudentID() + "|"
                                                        + existingEnrollment.getYear() + "|"
                                                        + existingEnrollment.getSemester() + "|"
                                                        + existingEnrollment.getPerbaikan() + "("
                                                        + existingEnrollment.getGrade() + ")");
                                            } else {
                                                System.out.println(existingEnrollment.getCourseID() + "|"
                                                        + existingEnrollment.getStudentID() + "|"
                                                        + existingEnrollment.getYear() + "|"
                                                        + existingEnrollment.getSemester() + "|"
                                                        + existingEnrollment.getGrade());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;

                    case "student-transcript":
                        String idS = tokens[1];
                        double totGPA = 0.0;
                        int totCred = 0;
                        double GPA = 0.0;
                        double GRADE = 0;

                        List<String> CRS = new ArrayList<>();
                        List<String> GRD = new ArrayList<>();
                        List<String> ACA = new ArrayList<>();

                        for (Enrollment existingEnrollment : enrollments) {
                            if (existingEnrollment.getStudentID().equals(idS)
                                    && existingEnrollment.getGrade() != "None") {
                                if (existingEnrollment.getCek() == 1) {
                                    GRD.add(existingEnrollment.getPerbaikan());
                                    CRS.add(existingEnrollment.getCourseID());
                                    ACA.add(existingEnrollment.getYear());
                                } else {
                                    CRS.add(existingEnrollment.getCourseID());
                                    GRD.add(existingEnrollment.getGrade());
                                    ACA.add(existingEnrollment.getYear());
                                }
                            }
                        }
                        int edge = CRS.size();

                        for (int i = 0; i < edge; i++) {
                            for (int j = i + 1; j < edge; j++) {
                                // Periksa apakah kursus sama
                                if (CRS.get(i).equals(CRS.get(j))) {
                                    if (CRS.get(i).equals(CRS.get(j))) {
                                        if (ACA.get(i).compareTo(ACA.get(j)) > 0) {
                                            CRS.remove(j);
                                            GRD.remove(j);
                                            ACA.remove(j);
                                        } else {
                                            CRS.remove(i);
                                            GRD.remove(i);
                                            ACA.remove(i);        
                                        }
                                        edge--;
                                        j--;
                                    }
                                }
                            }
                        }
                        
                        for (int i = 0; i < CRS.size(); i++) {
                            for (int j = 0; j < course.size(); j++) {
                                if (CRS.get(i).equals(course.get(j).getCode())) {
                                    totCred += course.get(j).getCredits();
                                    switch (GRD.get(i)) {
                                        case "A":
                                            GRADE = 4.0 * course.get(j).getCredits();
                                            break;
                                        case "AB":
                                            GRADE = 3.5 * course.get(j).getCredits();
                                            break;
                                        case "B":
                                            GRADE = 3.0 * course.get(j).getCredits();
                                            break;
                                        case "BC":
                                            GRADE = 2.5 * course.get(j).getCredits();
                                            break;
                                        case "C":
                                            GRADE = 2.0 * course.get(j).getCredits();
                                            break;
                                        case "D":
                                            GRADE = 1.0 * course.get(j).getCredits();
                                            break;
                                        case "E":
                                            GRADE = 0.0 * course.get(j).getCredits();
                                            break;
                                    }
                                    totGPA += GRADE;
                                }
                            }
                        }

                        if (totCred == 0) {
                            GPA = 0;
                        } else {
                            GPA = totGPA / totCred;
                        }

                        for (Student student : students) {
                            if (student.getId().equals(idS)) {
                                detailsTranscript.add(new Detail(student.getId(), student.getName(),
                                        student.getAngkatan(), student.getProdi(), GPA, String.valueOf(totCred)));
                            }
                        }

                        // print transcript
                        for (Detail detail : detailsTranscript) {
                            if (detail.getId().equals(idS)) {
                                System.out.println(detail.getId() + "|" + detail.getName() + "|" + detail.getYear()
                                        + "|"
                                        + detail.getStudyProgram()
                                        + "|" + String.format("%.2f", detail.getGpa()) + "|" + detail.getTotalCredit());
                            }
                        }

                        for (Enrollment E : enrollments) {
                            for (int i = 0; i < CRS.size(); i++) {
                                if (idS.equals(E.getStudentID()) && E.getCourseID().equals(CRS.get(i))
                                        && E.getYear().equals(ACA.get(i))) {
                                            if (E.getPerbaikan() == "") {
                                                System.out.println(E.getCourseID() + "|" + E.getStudentID() + "|" + E.getYear()
                                                        + "|" + E.getSemester() + "|" + E.getGrade());
                                            } else {
                                                System.out.println(E.getCourseID() + "|" + E.getStudentID() + "|" + E.getYear()
                                                        + "|" + E.getSemester() + "|" + E.getPerbaikan() + "(" + E.getGrade() + ")");
                                            }
                                }
                            }
                        }
                        break;

                        case "find-the-best-student":
                        String bestStudent = null;
                        String highestGradeOdd = null;
                        String highestGradeEven = null;
                        
                        for (Enrollment enrollment : enrollments) {
                            // Check if the last digit of the student ID is even
                            if (Character.getNumericValue(enrollment.getStudentID().charAt(enrollment.getStudentID().length() - 1)) % 2 == 0) {
                                // Compare the highest grade
                                if(enrollment.getYear().equals(tokens[1])) {
                                    if (enrollment.getSemester().equals("odd")) {
                                        if (highestGradeOdd == null || enrollment.getGrade().compareTo(highestGradeOdd) > 0) {
                                            highestGradeOdd = enrollment.getGrade();
                                            bestStudent = enrollment.getStudentID();
                                        }
                                    } else if (enrollment.getSemester().equals("even")) {
                                        if (highestGradeEven == null || enrollment.getGrade().compareTo(highestGradeEven) > 0) {
                                            highestGradeEven = enrollment.getGrade();
                                            bestStudent = enrollment.getStudentID();
                                        }
                                    }
                                }
                            }
                        }
                        
                        if (bestStudent != null) {
                            best.add(new Best(bestStudent, highestGradeOdd, highestGradeEven));
                        } else {
                            System.out.println("No students found.");
                        }                    }
                }
            }
        
                // Print lecturers
                for (Lecturer lecturers : lecturer) {
                    System.out.println(lecturers.getId() + "|" + lecturers.getName() + "|" + lecturers.getIntial() + "|"
                            + lecturers.getEmail() + "|" + lecturers.getStudyprogram());
                }
        
                // Print courses
                for (Course courses : course) {
                    System.out.println(courses.getCode() + "|" + courses.getName() + "|" + courses.getCredits() + "|"
                            + courses.getGrade());
                }
        
                for (Student student : students) {
                    System.out.println(
                            student.getId() + "|" + student.getName() + "|" + student.getAngkatan() + "|" + student.getProdi());
                }
        
                // enrollment
                for (Enrollment enrollment : enrollments) {
                    if (enrollment.getPerbaikan() == "") {
                        System.out.println(enrollment.getCourseID() + "|" + enrollment.getStudentID() + "|"
                                + enrollment.getYear() + "|" + enrollment.getSemester() + "|" + enrollment.getGrade());
                    } else {
                        System.out.println(enrollment.getCourseID() + "|" + enrollment.getStudentID() + "|"
                                + enrollment.getYear() + "|" + enrollment.getSemester() + "|" + enrollment.getPerbaikan() + "("
                                + enrollment.getGrade() + ")");
                    }
                } 
                
                //print best student
                for (Best bestStudent : best) {
                    System.out.println(bestStudent.getYear() + "|" + bestStudent.getbestGradeOdd() + "/" + bestStudent.getHighestGradeEven());
                }
        scan.close();
    }
}
