package com.jlangen.vaultbox.repositories

import android.os.Environment
import com.jlangen.vaultbox.models.Vault
import io.reactivex.Observable
import java.io.File

class VaultRepository {

    companion object {
        const val KEEPASS_DATABASE_EXTENSION = ".kdbx"
    }

    private fun search(directory: File, extension: String): List<Vault> {
        val databaseFiles = mutableListOf<Vault>()
        val files = directory.listFiles()

        if (files == null || files.isEmpty()) {
            return databaseFiles
        }

        for (entry in files) {
            if (entry.isDirectory) {
                databaseFiles.addAll(search(entry, extension))
            }
            if (entry.isFile && entry.path.endsWith(extension)) {
                databaseFiles.add(Vault(entry.name, entry.absolutePath))
            }
        }

        return databaseFiles
    }

    fun findAll(): Observable<List<Vault>> {
        return Observable.fromCallable {
            val externalStorage = Environment.getExternalStorageDirectory()
            search(externalStorage, KEEPASS_DATABASE_EXTENSION)
        }
    }
}