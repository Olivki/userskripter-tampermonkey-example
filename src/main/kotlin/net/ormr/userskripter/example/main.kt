package net.ormr.userskripter.example

import kotlinx.browser.document
import net.ormr.userskripter.engine.tampermonkey.BlockingTamperMonkey
import net.ormr.userskripter.userskript
import net.ormr.userskripter.util.appendNewElement
import org.w3c.dom.HTMLButtonElement

fun main() = userskript {
    val element = document.querySelector("body > div")!!
    element.appendNewElement<HTMLButtonElement>("button") {
        textContent = "Click Me!"
        onclick = {
            BlockingTamperMonkey.notification(text = "This is a notification!")
            // it's also possible to do GM_notification(text = "This is a notification!")
            // if you want to use suspend API:
            //  launch {
            //      TamperMonkey.notification(text = "This is a notification!")
            //  }
            // you'll also need to change the `grant` function from `grant("GM_notification")` to
            // `grant("GM.notification")`.
        }
    }
}