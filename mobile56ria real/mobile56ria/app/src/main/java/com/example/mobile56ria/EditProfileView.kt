package com.example.mobile56ria

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat

class EditProfileView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var vectorProfile: Drawable
    private var vectorBack: Drawable
    private val user = User("Edit picture or avatar")

    init {
        textPaint.apply {
            color = Color.BLACK
            textSize = 35f
            textAlign = Paint.Align.CENTER
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }

        rectPaint.apply {
            color = Color.LTGRAY
            style = Paint.Style.STROKE
            strokeWidth = 5f
        }

        rectTextPaint.apply {
            color = Color.DKGRAY
            textSize = 45f
            textAlign = Paint.Align.LEFT
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }

        vectorProfile = ContextCompat.getDrawable(context, R.drawable.baseline_account_circle_24)!!
        vectorBack = ContextCompat.getDrawable(context, R.drawable.baseline_arrow_back_24)!!
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Tombol kembali
        vectorBack.setBounds(45, 40, 115, 110)
        vectorBack.draw(canvas)

        // Teks "Edit Profil" (disejajarkan ke kiri)
        textPaint.apply {
            textSize = 50f
            textAlign = Paint.Align.LEFT
            color = Color.BLACK
        }
        canvas.drawText("Edit profile", 150f, 95f, textPaint)

        // Gambar profil di tengah
        val centerX = width / 2
        val profileSize = 250
        val profileTop = 180
        val profileLeft = centerX - (profileSize / 2)

        vectorProfile.setBounds(profileLeft, profileTop, profileLeft + profileSize, profileTop + profileSize)
        vectorProfile.draw(canvas)

        // Posisi teks "Edit foto atau avatar"
        val textBelowProfileY = profileTop + profileSize + 50f

        textPaint.apply {
            textSize = 40f
            textAlign = Paint.Align.CENTER
            color = Color.BLUE
        }

        // ----- PENAMBAHAN 4 PERSEGI PANJANG BERSUSUN -----
        val rectWidth = 1000f
        val rectHeight = 150f
        val rectLeft = 40f
        val spacing = 50f
        val cornerRadius = 25f
        val labels = listOf("Name", "Username", "Pronouns", "Bio")
        var rectTop = textBelowProfileY + 100f

        for (label in labels) {
            val rectRight = rectLeft + rectWidth
            val rectBottom = rectTop + rectHeight
            canvas.drawRoundRect(RectF(rectLeft, rectTop, rectRight, rectBottom), cornerRadius, cornerRadius, rectPaint)

            val textPaddingLeft = 30f
            val textPaddingTop = 25f
            val textInsideX = rectLeft + textPaddingLeft
            val fontMetrics = rectTextPaint.fontMetrics
            val textInsideY = rectTop - fontMetrics.ascent + textPaddingTop

            rectTextPaint.apply {
                color = Color.LTGRAY
                textSize = 40f
                textAlign = Paint.Align.LEFT
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            }

            // Gambar teks di dalam persegi panjang (mepet ke atas)
            canvas.drawText(label, textInsideX, textInsideY, rectTextPaint)
            rectTop = rectBottom + spacing
        }

        canvas.drawText(user.name, width / 2f, textBelowProfileY, textPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            // Navigasi kembali ke MainActivity jika tombol back ditekan
            if (event.x in 45f..115f && event.y in 40f..110f) {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
                return true
            }
        }
        return true
    }

    data class User(val name: String)
}
