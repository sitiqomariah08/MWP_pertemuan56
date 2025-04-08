package com.example.mobile56ria

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat

class CustomView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val radius = 140f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paintCircle = Paint(Paint.ANTI_ALIAS_FLAG)

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 50f
        textAlign = Paint.Align.LEFT
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }
    private val normalTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.DKGRAY
        textSize = 40f
        textAlign = Paint.Align.LEFT
    }
    private val bioTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 40f
        textAlign = Paint.Align.LEFT
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }
    private val linkTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#87CEFA")
        textSize = 40f
        textAlign = Paint.Align.LEFT
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }

    // Perbaikan: Pastikan ikon ada dalam drawable resource
    private val vectorDrawables: Array<Drawable?> = arrayOf(
        ContextCompat.getDrawable(context, R.drawable.baseline_arrow_back_241),  // Indeks 0
        ContextCompat.getDrawable(context, R.drawable.baseline_more_vert_24)   // Indeks 1
    )

    private val buttonRect = RectF()
    private val followersBounds = Rect()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Header Biru
        paint.color = Color.BLUE
        canvas.drawRect(0f, 0f, width.toFloat(), 330f, paint)

        // Perbaikan: Pastikan drawable tidak null sebelum menggambar
        vectorDrawables[0]?.let { drawDrawable(canvas, it, 45f, 40f, 70, 70) }  // Tombol Back
        vectorDrawables[1]?.let { drawDrawable(canvas, it, width - 85f, 40f, 70, 70) } // Tombol More

        // Lingkaran Profil
        paintCircle.color = Color.GREEN
        val circleX = radius + 30f
        val circleY = 320f
        canvas.drawCircle(circleX, circleY, radius, paintCircle)

        // Nama dan Bio
        val textX = circleX - radius
        canvas.drawText("marimar", textX, circleY + radius + 60f, textPaint)
        canvas.drawText("@riamaria", textX, circleY + radius + 118f, normalTextPaint)
        canvas.drawText("hailoo", textX, circleY + radius + 185f, bioTextPaint)

        // Following & Followers
        val jmlFollowingX = textX
        val jmlFollowingY = circleY + radius + 250f

        canvas.drawText("2134", jmlFollowingX, jmlFollowingY, bioTextPaint)

        val FollowingX = jmlFollowingX + 115f
        canvas.drawText("Following", FollowingX, jmlFollowingY, normalTextPaint)

        val jmlFollowerX = FollowingX + 250f
        canvas.drawText("23,4K", jmlFollowerX, jmlFollowingY, bioTextPaint)

        val FollowerX = jmlFollowerX + 115f
        canvas.drawText("Followers", FollowerX, jmlFollowingY, normalTextPaint)


        // Simpan koordinat untuk deteksi klik
        followersBounds.set(
            jmlFollowerX.toInt(),
            (jmlFollowingY - 40).toInt(),
            (FollowerX + 90).toInt(),
            (jmlFollowingY + 10).toInt()
        )

        // Tombol Edit Profile
        val btnEditX = width - 380f
        val btnEditY = 380f
        val btnEditWidth = 350f
        val btnEditHeight = 100f
        buttonRect.set(btnEditX, btnEditY, btnEditX + btnEditWidth, btnEditY + btnEditHeight)

        paint.color = Color.LTGRAY
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3f
        canvas.drawRoundRect(buttonRect, 50f, 50f, paint)

        paint.style = Paint.Style.FILL
        paint.color = Color.BLACK
        paint.textSize = 40f

        val buttonText = "Edit Profile"
        val textWidth = paint.measureText(buttonText)
        canvas.drawText(buttonText, buttonRect.centerX() - textWidth / 2, buttonRect.centerY() + 15f, paint)
    }

    private fun drawDrawable(canvas: Canvas, drawable: Drawable, x: Float, y: Float, width: Int, height: Int) {
        drawable.setBounds(x.toInt(), y.toInt(), x.toInt() + width, y.toInt() + height)
        drawable.draw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (buttonRect.contains(event.x, event.y)) {
                    context.startActivity(Intent(context, EditProfileActivity::class.java))
                    return true
                }

                if (followersBounds.contains(event.x.toInt(), event.y.toInt())) {
                    context.startActivity(Intent(context, FollowersActivity::class.java))
                    return true
                }
            }
        }
        return super.onTouchEvent(event)
    }
}
