package app.util.fakedata;

import app.model.Attempt;

import app.util.FirebaseUtil;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Random;

public class FakeAttemptsUtil {
    public static void writeFakeData(FirebaseDatabase db) {
        FirebaseUtil.synchronizeWrite(db, "attempts", "{}"); // clear data
        FirebaseUtil.synchronizeWrite(db, "attempts/1",  new Attempt(1525938300000L, "1",  "student1", "exercise-1", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/2",  new Attempt(1525938300000L, "2",  "student1", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/3",  new Attempt(1525938300000L, "3",  "student1", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/4",  new Attempt(1525938300000L, "4",  "student1", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/5",  new Attempt(1525938300000L, "5",  "student1", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/6",  new Attempt(1525938300000L, "6",  "student1", "exercise-2", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/7",  new Attempt(1525938300000L, "7",  "student1", "exercise-2", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/8",  new Attempt(1525938300000L, "8",  "student1", "exercise-2", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/9",  new Attempt(1526111100000L, "9",  "student1", "exercise-3", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/10", new Attempt(1526111100000L, "10", "student1", "exercise-3", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/11", new Attempt(1526111100000L, "11", "student1", "exercise-3", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/12", new Attempt(1526111100000L, "12", "student1", "exercise-3", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/13", new Attempt(1526111100000L, "13", "student1", "exercise-3", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/14", new Attempt(1526111100000L, "14", "student1", "exercise-3", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/15", new Attempt(1526111100000L, "15", "student1", "exercise-3", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/16", new Attempt(1526111100000L, "16", "student1", "exercise-3", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/17", new Attempt(1526111100000L, "17", "student1", "exercise-3", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/18", new Attempt(1526111100000L, "18", "student1", "exercise-3", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/19", new Attempt(1526111100000L, "19", "student1", "exercise-3", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/20", new Attempt(1526111100000L, "20", "student1", "exercise-3", "PYTHON",     "", 1));
        FirebaseUtil.synchronizeWrite(db, "attempts/21", new Attempt(1526197500000L, "21", "student2", "exercise-1", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/22", new Attempt(1526197500000L, "22", "student2", "exercise-1", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/23", new Attempt(1526197500000L, "23", "student2", "exercise-1", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/24", new Attempt(1526197500000L, "24", "student2", "exercise-1", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/25", new Attempt(1526197500000L, "25", "student2", "exercise-1", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/26", new Attempt(1526197500000L, "26", "student2", "exercise-1", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/27", new Attempt(1526197500000L, "27", "student2", "exercise-1", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/28", new Attempt(1526197500000L, "28", "student2", "exercise-1", "JAVASCRIPT", "", 1));
        FirebaseUtil.synchronizeWrite(db, "attempts/29", new Attempt(1526370300000L, "29", "student2", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/30", new Attempt(1526370300000L, "30", "student2", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/31", new Attempt(1526370300000L, "31", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/32", new Attempt(1526370300000L, "32", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/33", new Attempt(1526370300000L, "33", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/34", new Attempt(1526370300000L, "34", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/35", new Attempt(1526370300000L, "35", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/36", new Attempt(1526370300000L, "36", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/37", new Attempt(1526370300000L, "37", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/38", new Attempt(1526370300000L, "38", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/39", new Attempt(1526370300000L, "39", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/40", new Attempt(1526370300000L, "40", "student2", "exercise-2", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/41", new Attempt(1526370300000L, "41", "student2", "exercise-2", "PYTHON",     "", 1));
        FirebaseUtil.synchronizeWrite(db, "attempts/42", new Attempt(1526370300000L, "42", "student3", "exercise-2", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/43", new Attempt(1526370300000L, "43", "student3", "exercise-2", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/44", new Attempt(1526370300000L, "44", "student3", "exercise-2", "PYTHON",     "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/45", new Attempt(1526370300000L, "45", "student3", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/46", new Attempt(1526370300000L, "46", "student3", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/47", new Attempt(1526370300000L, "47", "student3", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/48", new Attempt(1526370300000L, "48", "student3", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/49", new Attempt(1526370300000L, "49", "student3", "exercise-2", "JAVASCRIPT", "", randomPercentComplete()));
        FirebaseUtil.synchronizeWrite(db, "attempts/50", new Attempt(1526370300000L, "50", "student3", "exercise-2", "JAVASCRIPT", "", 1));
    }

    private static double randomPercentComplete() {
        return 1d / (2 + new Random().nextInt(3));
    }

}
