package app.util.fakedata;

import app.model.Attempt;

import app.util.FirebaseUtil;
import com.google.common.collect.ImmutableMap;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Random;

public class FakeAttempts {
    public static void writeFakeData(FirebaseDatabase db) {
        FirebaseUtil.synchronizeWrite(db, "attempts", new ImmutableMap.Builder<String, Attempt>()
        .put("1",  new Attempt(1525938300000L, "1",  "student1", "exercise-1", "JAVASCRIPT", "", randomPercentComplete()))
        .put("2",  new Attempt(1525938300000L, "2",  "student1", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()))
        .put("3",  new Attempt(1525938300000L, "3",  "student1", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()))
        .put("4",  new Attempt(1525938300000L, "4",  "student1", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()))
        .put("5",  new Attempt(1525938300000L, "5",  "student1", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()))
        .put("6",  new Attempt(1525938300000L, "6",  "student1", "exercise-2", "PYTHON",     "", randomPercentComplete()))
        .put("7",  new Attempt(1525938300000L, "7",  "student1", "exercise-2", "PYTHON",     "", randomPercentComplete()))
        .put("8",  new Attempt(1525938300000L, "8",  "student1", "exercise-2", "PYTHON",     "", randomPercentComplete()))
        .put("9",  new Attempt(1526111100000L, "9",  "student1", "exercise-3", "JAVASCRIPT", "", randomPercentComplete()))
        .put("10", new Attempt(1526111100000L, "10", "student1", "exercise-3", "JAVASCRIPT", "", randomPercentComplete()))
        .put("11", new Attempt(1526111100000L, "11", "student1", "exercise-3", "JAVASCRIPT", "", randomPercentComplete()))
        .put("12", new Attempt(1526111100000L, "12", "student1", "exercise-3", "JAVASCRIPT", "", randomPercentComplete()))
        .put("13", new Attempt(1526111100000L, "13", "student1", "exercise-3", "JAVASCRIPT", "", randomPercentComplete()))
        .put("14", new Attempt(1526111100000L, "14", "student1", "exercise-3", "JAVASCRIPT", "", randomPercentComplete()))
        .put("15", new Attempt(1526111100000L, "15", "student1", "exercise-3", "JAVASCRIPT", "", randomPercentComplete()))
        .put("16", new Attempt(1526111100000L, "16", "student1", "exercise-3", "PYTHON",     "", randomPercentComplete()))
        .put("17", new Attempt(1526111100000L, "17", "student1", "exercise-3", "PYTHON",     "", randomPercentComplete()))
        .put("18", new Attempt(1526111100000L, "18", "student1", "exercise-3", "PYTHON",     "", randomPercentComplete()))
        .put("19", new Attempt(1526111100000L, "19", "student1", "exercise-3", "PYTHON",     "", randomPercentComplete()))
        .put("20", new Attempt(1526111100000L, "20", "student1", "exercise-3", "PYTHON",     "", 1))
        .put("21", new Attempt(1526197500000L, "21", "student2", "exercise-1", "PYTHON",     "", randomPercentComplete()))
        .put("22", new Attempt(1526197500000L, "22", "student2", "exercise-1", "PYTHON",     "", randomPercentComplete()))
        .put("23", new Attempt(1526197500000L, "23", "student2", "exercise-1", "PYTHON",     "", randomPercentComplete()))
        .put("24", new Attempt(1526197500000L, "24", "student2", "exercise-1", "PYTHON",     "", randomPercentComplete()))
        .put("25", new Attempt(1526197500000L, "25", "student2", "exercise-1", "JAVASCRIPT", "", randomPercentComplete()))
        .put("26", new Attempt(1526197500000L, "26", "student2", "exercise-1", "JAVASCRIPT", "", randomPercentComplete()))
        .put("27", new Attempt(1526197500000L, "27", "student2", "exercise-1", "JAVASCRIPT", "", randomPercentComplete()))
        .put("28", new Attempt(1526197500000L, "28", "student2", "exercise-1", "JAVASCRIPT", "", 1))
        .put("29", new Attempt(1526370300000L, "29", "student2", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()))
        .put("30", new Attempt(1526370300000L, "30", "student2", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()))
        .put("31", new Attempt(1526370300000L, "31", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()))
        .put("32", new Attempt(1526370300000L, "32", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()))
        .put("33", new Attempt(1526370300000L, "33", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()))
        .put("34", new Attempt(1526370300000L, "34", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()))
        .put("35", new Attempt(1526370300000L, "35", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()))
        .put("36", new Attempt(1526370300000L, "36", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()))
        .put("37", new Attempt(1526370300000L, "37", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()))
        .put("38", new Attempt(1526370300000L, "38", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()))
        .put("39", new Attempt(1526370300000L, "39", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()))
        .put("40", new Attempt(1526370300000L, "40", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()))
        .put("41", new Attempt(1526370300000L, "41", "student2", "exercise-2", "PYTHON",     "", 1))
        .put("42", new Attempt(1526370300000L, "42", "student3", "exercise-2", "PYTHON",     "", randomPercentComplete()))
        .put("43", new Attempt(1526370300000L, "43", "student3", "exercise-2", "PYTHON",     "", randomPercentComplete()))
        .put("44", new Attempt(1526370300000L, "44", "student3", "exercise-2", "PYTHON",     "", randomPercentComplete()))
        .put("45", new Attempt(1526370300000L, "45", "student3", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()))
        .put("46", new Attempt(1526370300000L, "46", "student3", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()))
        .put("47", new Attempt(1526370300000L, "47", "student3", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()))
        .put("48", new Attempt(1526370300000L, "48", "student3", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()))
        .put("49", new Attempt(1526370300000L, "49", "student3", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()))
        .put("50", new Attempt(1526370300000L, "50", "student3", "exercise-2", "JAVASCRIPT", "", 1))
        .build());
    }

    private static double randomPercentComplete() {
        return 1d / (2 + new Random().nextInt(3));
    }

}
