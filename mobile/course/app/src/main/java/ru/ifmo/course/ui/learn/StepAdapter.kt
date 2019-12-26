package ru.ifmo.course.ui.learn

import java.time.temporal.TemporalAdjusters.previous
import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.NonNull
import com.liefery.android.vertical_stepper_view.VerticalStepperAdapter
import com.liefery.android.vertical_stepper_view.VerticalStepperItemView
import com.liefery.android.vertical_stepper_view.VerticalStepperItemView.STATE_COMPLETE


data class Step(val title: String, val summary: String)

/**
 * public int getState( int position ) {
if ( position == focus )
return STATE_ACTIVE;
else if ( position < focus )
return STATE_COMPLETE;
else
return STATE_INACTIVE;
}
 */

class MainStepperAdapter(context: Context) : VerticalStepperAdapter(context) {
    private val steps = listOf(
        Step("2005", "Поступление в Гимназию №22 в Калининграде"),
        Step("2014 - 2015", "Прошёл IT-школу Samsung"),
        Step("2016", "Закончил школу"),
        Step("2016", "Поступил в ИТМО")
    )

    override fun getState(position: Int): Int {
        return STATE_COMPLETE
    }

    override fun getTitle(position: Int): CharSequence {
        return steps[position].title
    }

    override fun getSummary(position: Int): CharSequence? {
        return steps[position].summary
    }

    override fun isEditable(position: Int): Boolean {
        return false
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Void? {
        return null
    }

    override fun onCreateContentView(context: Context, position: Int): View {
        return View(context)
    }
}