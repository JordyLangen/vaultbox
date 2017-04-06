package com.jlangen.vaultbox.database

import android.os.Environment
import io.reactivex.Observable
import java.io.File

class KeepassDatabaseRepository {

    companion object {
        const val KEEPASS_DATABASE_EXTENSION = ".kdbx"
    }

    private fun search(directory: File, extension: String): List<File> {
        val databaseFiles = mutableListOf<File>()
        val files = directory.listFiles()

        if (files == null || files.isEmpty()) {
            return databaseFiles
        }

        for (entry in files) {
            if (entry.isDirectory) {
                databaseFiles.addAll(search(entry, extension))
            }
            if (entry.isFile && entry.path.endsWith(extension)) {
                databaseFiles.add(entry)
            }
        }

        return databaseFiles
    }

    fun findAll(): Observable<KeepassDatabase> {
        return Observable.fromCallable {
            val externalStorage = Environment.getExternalStorageDirectory()
            search(externalStorage, KEEPASS_DATABASE_EXTENSION)
        }.
                flatMap {
                    Observable.fromIterable(it)
                }
                .map {
                    KeepassDatabase(it.name, it.absolutePath)
                }
    }
}