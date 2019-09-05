package com.whitemedia.spring.webflux.html

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.Arrays.toString

@Controller
class HtmlController {

    @Autowired
    lateinit var environment: Environment

    @GetMapping("/")
    fun index(@RequestParam(required = false) searchBlock: String?, @RequestParam(required = false) searchChainId: String?, model: Model): String {
        with(model) {
            addAttribute("activeProfiles", toString(environment.activeProfiles))
        }
        return "index"
    }
}
