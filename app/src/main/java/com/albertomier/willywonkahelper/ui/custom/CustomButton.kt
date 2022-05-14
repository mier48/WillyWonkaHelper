package com.albertomier.willywonkahelper.ui.custom

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class CustomButton : AppCompatButton {
    constructor(context: Context) : super(context) {
        customize(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        customize(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        customize(context)
    }

    private fun customize(
        context: Context
    ) {
        if (!isInEditMode) {
            typeface = Typeface.createFromAsset(context.assets, "fonts/Taviraj-SemiBold.ttf")
        }
    }
}