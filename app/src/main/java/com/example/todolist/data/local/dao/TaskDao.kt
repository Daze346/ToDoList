import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao{

    @Insert
    fun addTask(task: Task)

    @Query(value = "SELECT * FROM tasks")
    fun getAllTasks(): List<Task>

    @Query(value = "DELETE FROM tasks WHERE id = :taskId")
    fun deleteTask(taskId: Long)

    @Query(value = "DELETE FROM tasks")
    fun deleteAllTasks()

    @Query(value = "UPDATE tasks SET isCompleted = 1 WHERE id = :taskId")
    fun setCompleted(taskId: Long)

    @Query(value = "UPDATE tasks SET isCompleted = 0 WHERE id = :taskId")
    fun setUncompleted(taskId: Long)

    @Query(value = "UPDATE tasks SET title = :title, description = :description WHERE id = :taskId")
    fun renameTask(title: String, description: String, taskId: Long)

}