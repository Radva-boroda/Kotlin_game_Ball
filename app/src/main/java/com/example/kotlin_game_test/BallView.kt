package com.example.kotlin_game_test

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class BallView(context: Context, attrs: AttributeSet?) : View(context, attrs){

    private var x = 100f
    private var y = 100f
    private var xSpeed = 5f
    private var ySpeed = 5f
    private val ballRadius = 50f
    private val paint = Paint()
    private var isMovingDown = true

    init {
        paint.color = Color.BLUE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(x, y, ballRadius, paint)
    }

    fun moveBall() {
        if (isMovingDown) {
            y += ySpeed
        } else {
            y -= ySpeed
        }

        if (x - ballRadius <= 0 || x + ballRadius >= width) {
            xSpeed *= -1
        }

        if (y - ballRadius <= 0 || y + ballRadius >= height) {
            isMovingDown = !isMovingDown
        }
    }

    fun changeDirection() {
        isMovingDown = !isMovingDown
    }
}