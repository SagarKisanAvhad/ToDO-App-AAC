package developer.android.google.todoaac.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task ORDER BY priority")
    LiveData<List<TaskEntry>> loadAllTasks();

    @Insert
    void insertTask(TaskEntry taskEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(TaskEntry taskEntry);

    @Delete
    void deleteTask(TaskEntry taskEntry);

    @Query("SELECT * FROM task WHERE id = :id")
    LiveData<TaskEntry> loadTaskById(int id);
}

