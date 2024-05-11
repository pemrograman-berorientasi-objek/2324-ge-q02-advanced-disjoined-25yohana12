package academic.model;
/**
 * @author 12S22003 Yohana Natalia Siahaan
 * @author NIM Nama 
 */
public class Lecturer implements Actor {
    private String id;
    private String name;
    private String intial;
    private String email;
    private String studyprogram;

    public Lecturer(String id, String name, String intial, String email, String studyprogram) {
        this.id = id;
        this.name = name;
        this.intial = intial;
        this.email = email;
        this.studyprogram = studyprogram;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getIntial() {
        return intial;
    }

    public String getEmail() {
        return email;
    }

    public String getStudyprogram() {
        return studyprogram;
    }
}
