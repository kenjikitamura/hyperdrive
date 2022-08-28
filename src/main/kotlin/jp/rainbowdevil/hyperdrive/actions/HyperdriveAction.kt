package jp.rainbowdevil.hyperdrive.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import jp.rainbowdevil.hyperdrive.services.MyProjectService

class HyperdriveAction: AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return

        println("HyperdriveAction: actionPerformed")
        val service = project.service<MyProjectService>()
        service.test()
    }
}