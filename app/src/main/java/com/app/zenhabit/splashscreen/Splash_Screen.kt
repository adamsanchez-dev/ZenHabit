package com.app.zenhabit.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.app.zenhabit.R
import com.app.zenhabit.activities.IntroductionActivity_1
import com.google.android.play.core.review.ReviewManagerFactory


class Splash_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val reviewManager = ReviewManagerFactory.create(this)
            reviewManager.requestReviewFlow().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Get the review flow
                    val reviewInfo = task.result
                    // Launch the review flow
                    val flow = reviewManager.launchReviewFlow(this, reviewInfo)
                    flow.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // The review has been submitted
                        }
                    }
                } else {
                    // There was a problem launching the review flow
                }
            }
            val intent = Intent(this@Splash_Screen, IntroductionActivity_1::class.java)
            startActivity(intent)
            finish()
        }, 2000)

    }
}