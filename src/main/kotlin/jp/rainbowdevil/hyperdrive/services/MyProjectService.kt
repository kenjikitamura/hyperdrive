package jp.rainbowdevil.hyperdrive.services

import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.impl.EditorHistoryManager
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl
import com.intellij.openapi.project.Project
import com.intellij.openapi.vcs.changes.ChangeListManager
import jp.rainbowdevil.hyperdrive.MyBundle
import java.io.File

class MyProjectService(private val project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }

    fun test() {
        println("test start")
        getOpenedFiles()
        getRecentFiles()
        getChangedFiles()
        getAllFiles()
        println("test end")
    }

    /**
     * 開いているファイル一覧を取得するテスト
     */
    private fun getOpenedFiles() {
        println("現在開いているファイル")
        val editorManager = FileEditorManager.getInstance(project) as FileEditorManagerImpl
        editorManager.selectionHistory.forEach { virtualFileEditorWindowPair ->
            val virtualFile = virtualFileEditorWindowPair.first
            val editorWindow = virtualFileEditorWindowPair.second
            println("  file=${virtualFile.canonicalPath} ${virtualFile.name} editor=${editorWindow}")
        }
    }

    /**
     * 最近使ったファイル一覧を取得するテスト
     */
    private fun getRecentFiles() {
        println("最近使ったファイル")
        val fileList = EditorHistoryManager.getInstance(project).fileList
        fileList.forEach { file ->
            println(" ${file.name}")
        }
    }

    private fun getChangedFiles() {
        println("変更ファイル")
        val changeListManager = ChangeListManager.getInstance(project)
        changeListManager.allChanges.forEach {
            println("  ${it.virtualFile?.name}")
        }
    }

    /**
     *
     */
    private fun getAllFiles() {
        println("全ファイル")
        val path = project.basePath ?: return
        fun func(dir: File) {
            dir.list().forEach {
                val file = File(dir.absolutePath, it)
                println("  ${file.absolutePath}")
                if(file.isDirectory) func(file)
            }
        }
        func(File(path))
    }
}
