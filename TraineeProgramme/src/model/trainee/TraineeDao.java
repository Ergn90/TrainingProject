package model.trainee;

import java.util.Set;

public interface TraineeDao {
    Trainee getTrainee(int id);
    Set<Trainee> getAllTrainee();
    boolean insertTrainee(Trainee trainee);
    boolean updateTrainee(Trainee Trainee);
    boolean deleteTrainee(int id);
}
