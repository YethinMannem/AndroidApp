package com.example.basicapp

import android.content.Context
import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue

@LargeTest
@RunWith(AndroidJUnit4::class)
class UiAutomatorTest {

    private val PACKAGE_NAME = "com.example.basicapp"
    private val TIMEOUT = 5000L
    private lateinit var device: UiDevice

    @Before
    fun launchAppFromHomeScreen() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome()

        val launcherPackage = device.launcherPackageName
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), TIMEOUT)

        val context: Context = InstrumentationRegistry.getInstrumentation().context
        val intent = context.packageManager.getLaunchIntentForPackage(PACKAGE_NAME)?.apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), TIMEOUT)
    }

    @Test
    fun testExplicitIntentAndChallengeText() {
        // clicking "start activity explicitly" button
        val explicitBtn = device.wait(Until.findObject(By.res(PACKAGE_NAME, "explicitButton")), TIMEOUT)
        assertNotNull(explicitBtn)
        explicitBtn!!.click()

        device.wait(Until.hasObject(By.pkg(PACKAGE_NAME)), TIMEOUT)

        // Getting the TextView displaying the challenges
        val challengesTextView = device.wait(Until.findObject(By.res(PACKAGE_NAME, "challengesTextView")), TIMEOUT)
        assertNotNull(challengesTextView)

        // Check if the expected challenge is present in the text
        val challengeText = challengesTextView!!.text
        assertTrue(
            "Expected challenge 'Battery Consumption' not found in: $challengeText",
            challengeText.contains("Battery Consumption")
        )
    }
}
