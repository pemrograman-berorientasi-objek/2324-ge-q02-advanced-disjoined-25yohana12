package academic.model;
/**
 * @author 12S22003 Yohana Natalia Siahaan
 * @author NIM Nama 
 */

public class Student implements Actor {
    private String id;
    private String name;
    private String angkatan;
    private String prodi;

    public Student(String id, String name, String angkatan, String prodi) {
        this.id = id;
        this.name = name;
        this.angkatan = angkatan;
        this.prodi = prodi;
    }

    @Override
    public String toString(){
       return this.id + "|" + this.name + "|" + this.angkatan + "|" + this.prodi ;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public String getProdi() {
        return prodi;
    }
}
 