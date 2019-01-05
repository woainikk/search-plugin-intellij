package actions.create

import actions.Changelog
import actions.GenerateChangeSet
import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import java.io.File

class CreateIndex : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertAddIndex()
    }

    private fun insertAddIndex() {
        val changelogFile = File(Changelog.changelogFileName)
        addHeaderToChangelog(changelogFile)

        changelogFile.appendText(
            "- changeSet:\n" +
                    "   id: ${GenerateChangeSet.id}\n" +
                    "   author: ${Author.authorName}\n" +
                    "   changes:\n" +
                    "   - createIndex:\n" +
                    "       columns:\n" +
                    "       - column:\n" +
                    "           name:\n" +
                    "           type:\n" +
                    "       indexName:\n" +
                    "       tableName:\n" +
                    "       unique:\n\n"
        )
        GenerateChangeSet.id++

    }
}