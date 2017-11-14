package app.controller;

import app.model.Exercise;
import app.Main;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import app.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ExerciseController {

    public static List<Exercise> getAllExercises() throws InterruptedException {
        return getExercises();
    }

    public static Exercise getExercise(String exerciseId) throws NotFoundException {
        for (Exercise exercise : getExercises()) {
            if (exercise.id.equals(exerciseId)) {
                return exercise;
            }
        }
        throw new NotFoundException();
    }

    private static List<Exercise> getExercises() {
        DatabaseReference ref = Main.firebaseDatabase.getReference("exercises");
        List<Exercise> exercises = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ref.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    exercises.add(child.getValue(Exercise.class));
                }
                countDownLatch.countDown();
            }
            public void onCancelled(DatabaseError databaseError) {}
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return exercises;
    }

}
