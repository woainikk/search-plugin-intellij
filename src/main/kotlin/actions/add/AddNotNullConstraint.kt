package actions.add

import Author
import actions.Changelog
import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import findLastId
import java.io.File

class AddNotNullConstraint : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertNotNullConstraint()
    }

    private fun insertNotNullConstraint() {
        val changelogFile = File(Changelog.changelogFileName)
        addHeaderToChangelog(changelogFile)
        if (IdValue.id < findLastId(Changelog.changelogFileName!!)) {
            IdValue.id = findLastId(Changelog.changelogFileName!!)
        }
        changelogFile.appendText(
              """- changeSet:
                    "    id: ${IdValue.id}
                    "    author: ${Author.authorName}
                    "    changes:
                    "    - addNotNullConstraint:
                    "       columnDataType:
                    "       columnName:
                    "       defaultNullValue:
                    "       tableName:"""
        )
        IdValue.id++
    }

}