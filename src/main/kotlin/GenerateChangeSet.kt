import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.ProjectManager
import java.io.File
import javax.swing.JOptionPane

class GenerateChangeSet : AnAction() {

    companion object {

        var id = 1
    }

    override fun actionPerformed(e: AnActionEvent?) {

        val curentProject = ProjectManager.getInstance().openProjects[0]
        val currentfile = FileEditorManager.getInstance(curentProject).selectedFiles[0].name
        if (Author.authorName == null) {
            JOptionPane.showMessageDialog(
                null, "Author name is empty!\n " +
                        "Set it in File -> Settings -> Tools -> Changesets Author."
            )
            return
        }
        if (Changelog.changelogFileName == null) {
            JOptionPane.showMessageDialog(
                null, "Changelog file is not determined!\n " +
                        "Click on prefered as a changelod file and click Liquibase -> Set as a changelog."
            )
            return

        }
        insertChangeSet(currentfile)
    }

    private fun insertChangeSet(filename: String) {
        val changelogPath = Changelog.changelogFileName
        val changelogFile = File(changelogPath)

        if (changelogFile.length() < 10) {
            changelogFile.writeText("databaseChangeLog:\n")
        }
        changelogFile.appendText(
            "- changeSet:\n" +
                    "   id: $id\n" +
                    "   author: ${Author.authorName}\n" +
                    "   changes:\n" +
                    "   - sqlfile:\n" +
                    "       path: $filename\n" +
                    "       relativeToChangelogFile: true \n\n"
        )
        id++
    }

}