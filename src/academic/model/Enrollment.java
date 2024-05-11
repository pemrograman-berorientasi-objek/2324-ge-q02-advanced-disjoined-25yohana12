package academic.model;

/**
 * @author 12SS2003 Yohana Natalia Siahaan
 */ 
public class Enrollment {

    // class definition
    private String courseID;
    private String studentID;
    private String year;
    private String semester;
    private String grade = "None";
    private String perbaikan = "";
    private int cek = 0;

    //buat konstruktor untuk enrollment
    public Enrollment(String courseID, String studentID, String year, String semester, String grade) {
        this.courseID = courseID;
        this.studentID = studentID;
        this.year = year;
        this.semester = semester;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getYear() {
        return year;
    }

    public String getSemester() {
        return semester;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String _grade){
        this.grade = _grade;
    }

    public String getPerbaikan(){
        return this.perbaikan;
    }

    public void setPerbaikan(String _perbaikan){
        this.perbaikan = _perbaikan;
    }

    public int getCek(){
        return this.cek;
    }

    public void setCek(int _cek){
        this.cek = _cek;
    }
}
 