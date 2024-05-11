package academic.model;

/**
 * @author 12S22003 Yohana Natalia Siahaan
 * @author NIM Nama
 */ 
public class CourseOpening {

    // class definition
    private String courseId;
    private String year;
    private String semester;
    private String lecturerInitials;

    public CourseOpening(String courseId, String year, String semester, String lecturerInitials) {
        this.courseId = courseId;
        this.year = year;
        this.semester = semester;
        this.lecturerInitials = lecturerInitials;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getYear() {
        return year;
    }

    public String getSemester() {
        return semester;
    }

    public String getLecturerInitials() {
        return lecturerInitials;
    }
}
 