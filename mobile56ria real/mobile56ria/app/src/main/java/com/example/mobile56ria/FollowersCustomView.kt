package com.example.mobile56ria

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import kotlin.math.max
import kotlin.math.min

class FollowersCustomView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textName = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textUser = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textBio = Paint(Paint.ANTI_ALIAS_FLAG)

    private lateinit var vectorProfil: Drawable
    private lateinit var vectorAddFriend: Drawable
    private lateinit var vectorBack: Drawable

    private val buttonRect = RectF()

    private val users = mutableListOf(
        User("Tren Teknologi", "@trentekno", "Tetap update dengan perkembangan teknologi terbaru!", true),
        User("Dapur Lezat", "@dapurlezat", "Resep makanan enak dan review kuliner!", true),
        User("Pecinta Film", "@pecintafilm", "Review dan rekomendasi film terbaru!", true),
        User("Jelajah Dunia", "@jelajahdunia", "Temukan tempat-tempat indah di seluruh dunia!", true),
        User("Sehat Bugar", "@sehatbugar", "Tips kesehatan dan olahraga setiap hari!", true),
        User("Nada Musik", "@nadamusik", "Update lagu terbaru dan tren musik!", true),
        User("Sudut Koding", "@sudutkoding", "Tips pemrograman dan wawasan teknologi!", true),
        User("Surga Buku", "@surgabuku", "Rekomendasi buku bacaan wajib dan diskusi sastra!", true),
        User("Zona Game", "@zonagame", "Update terbaru tentang game dan e-sports!", true),
        User("Gaya Fashion", "@gayafashion", "Tren fashion terbaru dan inspirasi outfit!", true),
        User("Motivasi Harian", "@motivasi_harian", "Kutipan inspiratif untuk memulai harimu!", true)
    )

    private var scrollOffset = 0f
    private var lastTouchY = 0f
    private var contentHeight = 0

    init {
        textPaint.apply {
            color = Color.BLACK
            textSize = 55f
            textAlign = Paint.Align.LEFT
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }
        textName.apply {
            color = Color.BLACK
            textSize = 43f
            textAlign = Paint.Align.LEFT
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }
        textUser.apply {
            color = Color.GRAY
            textSize = 35f
            textAlign = Paint.Align.LEFT
        }
        textBio.apply {
            color = Color.BLACK
            textSize = 40f
            textAlign = Paint.Align.LEFT
        }

        vectorProfil = ContextCompat.getDrawable(context, R.drawable.baseline_account_circle_24)!!
        vectorAddFriend = ContextCompat.getDrawable(context, R.drawable.baseline_group_add_24)!!
        vectorBack = ContextCompat.getDrawable(context, R.drawable.baseline_arrow_back_24)!!
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Color.WHITE
        canvas.drawRect(0f, 0f, width.toFloat(), 150f, paint)

        vectorBack.setBounds(45, 40, 115, 110)
        vectorBack.draw(canvas)

        canvas.drawText("Followers", 190f, 95f, textPaint)

        vectorAddFriend.setBounds(950, 45, 1015, 110)
        vectorAddFriend.draw(canvas)

        paint.color = Color.BLACK
        paint.strokeWidth = 2f
        canvas.drawLine(0f, 150f, width.toFloat(), 150f, paint)

        canvas.save()
        canvas.clipRect(0f, 150f, width.toFloat(), height.toFloat())
        canvas.translate(0f, 150f - scrollOffset)

        var yPosition = 30f
        val spacing = 200f

        for (user in users) {
            vectorProfil.setBounds(50, yPosition.toInt(), 185, (yPosition + 135).toInt())
            vectorProfil.draw(canvas)

            canvas.drawText(user.name, 210f, yPosition + 50f, textName)
            canvas.drawText(user.username, 210f, yPosition + 90f, textUser)
            canvas.drawText(user.bio, 210f, yPosition + 145f, textBio)

            val btnX = 790f
            val btnY = yPosition + 20f
            buttonRect.set(btnX, btnY, btnX + 250f, btnY + 70f)

            paint.color = if (user.isFollowers) Color.LTGRAY else Color.RED
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 3f
            canvas.drawRoundRect(buttonRect, 50f, 50f, paint)

            paint.style = Paint.Style.FILL
            paint.color = if (user.isFollowers) Color.BLACK else Color.RED
            paint.textSize = 40f

            val buttonText = if (user.isFollowers) "Message" else "Follow back"
            val textWidth = paint.measureText(buttonText)
            canvas.drawText(buttonText, buttonRect.centerX() - textWidth / 2, buttonRect.centerY() + 15f, paint)

            yPosition += spacing
        }

        contentHeight = yPosition.toInt()
        canvas.restore()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastTouchY = event.y

                if (event.x in 45f..115f && event.y in 40f..110f) {
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    return true
                }
0
                for (i in users.indices) {
                    val user = users[i]
                    val btnX = 790f
                    val btnY = 150f + 30f + i * 200f - scrollOffset
                    if (event.x in btnX..(btnX + 250f) && event.y in btnY..(btnY + 70f)) {
                        user.isFollowers = !user.isFollowers
                        invalidate()
                        return true
                    }
                }
            }

            MotionEvent.ACTION_MOVE -> {
                val deltaY = lastTouchY - event.y
                lastTouchY = event.y

                val maxScroll = max(0, contentHeight - height + 150)
                scrollOffset = min(max(0f, scrollOffset + deltaY), maxScroll.toFloat())

                invalidate()
                return true
            }
        }
        return true
    }

    data class User(val name: String, val username: String, val bio: String, var isFollowers: Boolean)
}