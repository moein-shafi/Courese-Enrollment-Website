package ie.diyar_moein_ca5.Classes;

import ie.diyar_moein_ca5.Exceptions.AlreadyAddedCourseToPlanException;
import ie.diyar_moein_ca5.Exceptions.ClassesTimeCollisionException;
import ie.diyar_moein_ca5.Exceptions.ExamsTimeColisionException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Course {
    private String code;
    private String classCode;
    private String name;
    private String instructor;
    private String type;
    private Integer units;
    private Integer signedUp;
    private ArrayList<String> classDays = new ArrayList<String>();
    private String classTime;
    private HashMap<String, LocalDateTime> examTime;
    private Integer capacity;
    private ArrayList<String> prerequisites = new ArrayList<String>();
    private ArrayList<ie.diyar_moein_ca5.Classes.Student> waitingList = new ArrayList<ie.diyar_moein_ca5.Classes.Student>();

    public Course(String code, String classCode, String name, String instructor, String type, Integer units, ArrayList<String> classDays,
                  String classTime, HashMap<String, LocalDateTime> examTime, Integer capacity,
                  ArrayList<String> prerequisites) {
        this.code = code;
        this.classCode = classCode;
        this.name = name;
        this.instructor = instructor;
        this.type = type;
        this.units = units;
        this.classDays.addAll(classDays);
        this.classTime = classTime;
        this.capacity = capacity;
        this.signedUp = 0;
        this.prerequisites.addAll(prerequisites);
        this.examTime = new HashMap<String, LocalDateTime>(examTime);   // it makes shallow copy
    }

    public ArrayList<String> getClassDays() {
        return this.classDays;
    }

    public ArrayList<String> getPrerequisites() {
        return this.prerequisites;
    }

    public Integer getCapacity() {
        return this.capacity;
    }

    public Integer getUnits() {
        return this.units;
    }

    public String getClassTime() {
        return this.classTime;
    }

    public String getCode() {
        return this.code;
    }

    public String getClassCode() {
        return this.classCode;
    }

    public String getInstructor() {
        return this.instructor;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public HashMap<String, LocalDateTime> getExamTime() {
        return examTime;
    }

    public void decreaseSignedUp() {
        signedUp--;
    }

    public void increaseSignedUp() {
        signedUp++;
    }

    public Integer getSignedUp() {
        return signedUp;
    }

    public void checkWaitingList() throws AlreadyAddedCourseToPlanException, ExamsTimeColisionException, ClassesTimeCollisionException {
        while (this.signedUp < this.capacity && this.waitingList.size() >= 1) {
            increaseSignedUp();
            this.waitingList.get(0).removeWaitingStatus(this);
            this.waitingList.remove(0);
        }

    }

    public void addToWaitingList(ie.diyar_moein_ca5.Classes.Student student) {
        this.waitingList.add(student);
    }
}