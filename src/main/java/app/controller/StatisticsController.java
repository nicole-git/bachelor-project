package app.controller;

import app.model.ExerciseInfo;
import app.model.UserInfo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticsController {

    public static List<ExerciseInfo> getExerciseInfo(List<UserInfo> userInfoList) {
        // todo: consider getting IDs some other way ?
        return userInfoList.get(0).getExerciseToSolved().keySet().stream() // get exercise-ids from first student
                .map(exerciseId -> new ExerciseInfo(exerciseId, userInfoList)) // create exercise
                .sorted(Comparator.comparing(ExerciseInfo::getExerciseId))
                .collect(Collectors.toList());
    }

}
