package model.enrolled_trainees;

public class Enrolled_trainees {
    private int traineeID;
    private int courseID;
    private int skalaID;

    public Enrolled_trainees() {
    }

    public Enrolled_trainees(int traineeID, int courseID, int skalaID) {
        this.traineeID = traineeID;
        this.courseID = courseID;
        this.skalaID = skalaID;
    }

    public int getTraineeID() {
        return traineeID;
    }

    public void setTraineeID(int traineeID) {
        this.traineeID = traineeID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getSkalaID() {
        return skalaID;
    }

    public void setSkalaID(int skalaID) {
        this.skalaID = skalaID;
    }
}
