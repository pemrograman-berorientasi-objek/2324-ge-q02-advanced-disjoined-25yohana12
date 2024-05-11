package academic.model;

/**
 * @author 12S22003 Yohana Natalia Siahaan
 * @author NIM Nama 
 */
public class Detail {
    private String id;
    private String name;
    private String year;
    private String studyProgram;
    private double gpa;
    private String totalCredit;

    public Detail(String id, String name, String year, String studyProgram, double gpa, String totalCredit) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.studyProgram = studyProgram;
        this.gpa = gpa;
        this.totalCredit = totalCredit;
    }

    // Getter dan setter untuk masing-masing atribut

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(String totalCredit) {
        this.totalCredit = totalCredit;
    }
}
