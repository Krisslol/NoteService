package ru.netology


import Comment
import Note
import NoteService
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NoteServiceTest {
    @Test
    fun addNote() {
        val note = Note(
            noteId = 1,
            title = "blabla",
            text = "blabla",
            ownerId = 2,
            noteDeleteId = false,
            date = Date()
        )
        NoteService.addNote(note)
        assertEquals(1, note.noteId)

    }

    @Test
    fun createComment() {
        val comment = Comment(
            commentId = 2,
            text = "blabla",
            ownerId = 5,
            commentDeleteId = false,
            noteId = 1,
            ownerCommentId = 3,
            date = Date()
        )
        NoteService.createComment(1, comment)
        assertEquals(2, comment.noteId)
    }


    @Test
    fun editNote() {
        val note = Note (
            noteId = 1,
            title = "blabla",
            text = "blabla",
            ownerId = 2,
            noteDeleteId = false,
            date = Date()
                )
        NoteService.addNote(note)
        val editNote = Note(2, "sdasd", "sdadsa", 2, true, date = Date())
        val result = NoteService.editNote(editNote)
        assertTrue(result)

    }

    @Test
    fun editComment() {
        val comment = Comment (
            commentId = 2,
            text = "blabla",
            ownerId = 5,
            commentDeleteId = false,
            noteId = 1,
            ownerCommentId = 3,
            date = Date()
                )
        NoteService.createComment(1,comment)
        val editComment = Comment(2, "dfdsf", 5, false, 1,3,date=Date())
        val note = Note(2, "sdasd", "sdadsa", 2, true, date = Date())
        val res = NoteService.editComment(editComment, note)
        assertTrue(res)
    }

    @Test
    fun getNote() {
    }

    @Test
    fun getNoteById() {
    }

    @Test
    fun getComment() {
    }

    @Test
    fun restoreComment() {
    }
}