package e.rick.duolingoclone.ui.activity.lessonlistactivity

import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import e.rick.duolingoclone.Data.Repository
import e.rick.duolingoclone.R
import kotlin.coroutines.CoroutineContext

class LessonListActivity: AppCompatActivity() {

    lateinit var repository: Repository

    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)
    }
}