import actions.Changelog
import actions.MasterChangelog
import java.io.File
import java.util.*
import javax.swing.JOptionPane

fun checkAuthorAndChangelogIsDetermined(): Boolean {
    if (Author.authorName == null) {
        JOptionPane.showMessageDialog(
            null, "Author name is empty!\n " +
                    "Set it in File -> Settings -> Tools -> Changesets Author."
        )
        return false
    }
    if (Changelog.changelogFileName == null) {
        JOptionPane.showMessageDialog(
            null, "Changelog file is not determined!\n " +
                    "Click on prefered as a changelod file and click Liquibase -> Set as a changelog."
        )
        return false
    }
    return true
}

fun addHeaderToChangelog(changelogFile: File) {
    if (changelogFile.length() < 2) {
        changelogFile.writeText("databaseChangeLog:\n")
    }
}

fun checkMasterChangelogDetermined(): Boolean {
    if (MasterChangelog.changelogMasterName == null) {
        JOptionPane.showMessageDialog(
            null, "Master changelog file is not determined!\n " +
                    "Click on prefered as a master changelod file and click Liquibase -> Set as a master changelog."
        )
        return false
    }
    return true
}

fun checkFileContainString(masterFile: File, changelogFileName: String): Boolean {
    val scanner = Scanner(masterFile)
    var lineNum = 0
    while (scanner.hasNextLine()) {
        val line = scanner.nextLine()
        lineNum++
        println(line)
        println(changelogFileName)
        if (line.contains(changelogFileName)) {
            return true
        }
    }
    return false
}