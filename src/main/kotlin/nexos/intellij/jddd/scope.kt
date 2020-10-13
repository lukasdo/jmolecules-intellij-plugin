package nexos.intellij.jddd

import com.intellij.icons.AllIcons
import com.intellij.ide.highlighter.JavaFileType
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiJavaFile
import com.intellij.psi.search.scope.packageSet.AbstractPackageSet
import com.intellij.psi.search.scope.packageSet.CustomScopesProvider
import com.intellij.psi.search.scope.packageSet.NamedScope
import com.intellij.psi.search.scope.packageSet.NamedScopesHolder

private class JDDDPackageSet(private val info: Info):
        AbstractPackageSet(info.displayName, 1) {

    override fun contains(file: VirtualFile, project: Project, holder: NamedScopesHolder?): Boolean {
        if (holder != null && file.fileType == JavaFileType.INSTANCE) {
            val psi = getPsiFile(file, holder.project)
            if (psi is PsiJavaFile) {
                return cached(psi).contains(info)
            }
        }
        return false
    }

    override fun createCopy() = this

    override fun getText() = info.displayName

    override fun equals(other: Any?): Boolean {
        if(other is JDDDPackageSet) {
            return info == other.info
        }
        return false
    }

    override fun hashCode(): Int = info.hashCode()

    @Deprecated("see com.intellij.psi.search.scope.packageSet.PackageSetBase", ReplaceWith("false"))
    override fun contains(file: VirtualFile, holder: NamedScopesHolder?) = false
}

private class JDDDNamedScope(private val info:Info):
        NamedScope(info.displayName, AllIcons.Ide.LocalScope, JDDDPackageSet(info)) {
    override fun getDefaultColorName() = info.defaultColorName

    override fun createCopy() = this

    override fun equals(other: Any?): Boolean {
        if (other is JDDDNamedScope) {
            return info == other.info
        }
        return false
    }

    override fun hashCode(): Int = info.hashCode()
}

class Scopes : CustomScopesProvider {
    companion object {
        private val scopes: MutableList<NamedScope> by lazy { Info.all.map { JDDDNamedScope(it) }.toMutableList() }
    }

    override fun getCustomScopes() = scopes
}