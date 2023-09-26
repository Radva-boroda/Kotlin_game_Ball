package com.example.kotlin_game_test
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.widget.TextView
import android.media.MediaPlayer

class MainActivity : AppCompatActivity() {

    private lateinit var ballView: BallView
    private lateinit var directionText: TextView
    private var directionChangeCount = 0
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ballView = findViewById(R.id.ballView)
        directionText = findViewById(R.id.directionText)

        directionText.text = "Direction changes: $directionChangeCount"

        mediaPlayer = MediaPlayer.create(this, R.raw.click_sound) 

        val handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                ballView.moveBall()
                ballView.invalidate()
                handler.postDelayed(this, 10)
            }
        })
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            ballView.changeDirection()
            directionChangeCount++
            directionText.text = "Direction changes: $directionChangeCount"
            playClickSound()
        }
        return true
    }

    private fun playClickSound() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.seekTo(0)
        }
        mediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}