package xyz.mazuninky.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*

fun Int.toTimerText(): String {
    val hour = this / 60 / 60
    val minute = this / 60
    val seconds = this % 60
    return "${hour.toTwoDigits()}:${minute.toTwoDigits()}:${seconds.toTwoDigits()}"
}

fun Int.toTwoDigits(): String {
    return if (this < 10)
        "0$this"
    else
        this.toString()
}

class MainActivity : AppCompatActivity(), Runnable {

    private val SECONDS_KEY = "seconds"
    private val RUNNING_KEY = "running"

    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null && savedInstanceState.containsKey(SECONDS_KEY)
            && savedInstanceState.containsKey(RUNNING_KEY)
        ) {
            running = savedInstanceState.getBoolean(RUNNING_KEY)
            seconds = savedInstanceState.getInt(SECONDS_KEY)
            timerView.text = seconds.toTimerText()
        }

        initView()

        startBtn.setOnClickListener { onClickStart() }
        resetBtn.setOnClickListener { onClickReset() }
        stopBtn.setOnClickListener { onClickStop() }

        handler = Handler()

        runTimer()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(SECONDS_KEY, seconds)
        outState.putBoolean(RUNNING_KEY, running)
        super.onSaveInstanceState(outState)
    }

    private fun initView() {
        startBtn.isEnabled = !running
        stopBtn.isEnabled = running
    }

    override fun run() {
        if (running) {
            seconds++
            timerView.text = seconds.toTimerText()
        }
        runTimer()
    }

    private var seconds = 0
    private var running = false

    fun onClickStart() {
        running = true
        initView()
    }

    fun onClickStop() {
        running = false
        initView()
    }

    fun onClickReset() {
        seconds = 0
        onClickStop()
        timerView.setText(R.string.default_timer)
    }

    fun runTimer() {
        handler.postDelayed(this, 1000L)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(this)
    }
}
