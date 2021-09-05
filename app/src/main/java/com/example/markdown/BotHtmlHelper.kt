package com.example.markdown

import android.os.Build
import android.text.Html
import android.text.Spanned

const val htmlBL = "<br\\/>"
fun renderHtmlTxt(txt: String): Spanned {
    // support less than and greater than signs "<>"
    val specialTxt = supportLessAndGreaterThanSigns(txt)

    // to enable text to be bold
    val boldTxt = supportTxtBold(specialTxt)

    // to enable text to support break line
    var breakLineTxt = supportTxtBreakLine(boldTxt)

    // remove last index of the break line to not take extra spaces
    breakLineTxt = removeLastHtmlBreakLine(breakLineTxt)

    return convertToHtml(breakLineTxt)
}

@Suppress("DEPRECATION")
fun convertToHtml(txt: String): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(txt, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(txt)
    }
}

fun supportLessAndGreaterThanSigns(txt: String): String {
    val specialTxt = txt.replace("<".toRegex(), "&lt;")
    return specialTxt.replace(">".toRegex(), "&gt;")
}

fun supportTxtBold(txt: String): String {
    return txt.replace("\\*\\*([^*]*)\\*\\*".toRegex(), "<b>\$1<\\/b>")
}

fun supportTxtBreakLine(txt: String): String {
    return txt.replace("\n", htmlBL)
}

fun removeLastHtmlBreakLine(txt: String): String {
    val lastIndexOf = txt.lastIndexOf(htmlBL)
    if (lastIndexOf != -1 && (lastIndexOf + htmlBL.length == txt.length)) {
        return txt.replaceRange(lastIndexOf, txt.length, "")
    } else {
        return txt
    }
}