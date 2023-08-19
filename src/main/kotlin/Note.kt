import java.util.Date

data class Note(
    val noteId: Int,
    val title: String,
    var text: String,
    var ownerId: Int,
    val noteDeleteId: Boolean = false,
    var date: Date
)

data class Comment(
    val commentId: Int,
    var text: String,
    var ownerId: Int,
    var commentDeleteId: Boolean = false,
    val noteId: Int,
    var ownerCommentId: Int,
    var date: Date
)

object NoteService {

    private val notes = mutableListOf<Note>()
    private val comments = mutableListOf<Comment>()
    private var count = 0

    fun addNote(note: Note): Note {
        notes += note.copy(noteId = count++)
        return notes.last()
    }

    fun createComment(noteId: Int, comment: Comment): Comment {
        for ((index, note) in notes.withIndex()) {
            if (note.noteId == noteId) {
                comments += comment
                return comment
            }
        }
        throw CommentNotFoundException("Comment not found")
    }

    fun editNote(newNote: Note): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.noteId == newNote.noteId && note.noteDeleteId) {
                note.ownerId = newNote.ownerId
                note.date = newNote.date
                note.text = newNote.text
                notes[index] = newNote.copy()
                return true
            }
        }
        return false
    }

    fun editComment(newComment: Comment, note: Note): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (comment.noteId == newComment.noteId && comment.commentDeleteId && comment.commentId == newComment.commentId && note.noteDeleteId) {
                comment.ownerId = newComment.ownerId
                comment.date = newComment.date
                comment.text = newComment.text
                comment.ownerCommentId = newComment.ownerCommentId
                comments[index] = newComment.copy()
                return true

            }
        }
        return false
    }


    fun deleteNote(noteId: Int, note: Note, comment: Comment) {
        if (note.noteId == noteId && note.noteDeleteId) notes[noteId] = note.copy(noteDeleteId = true)
        else throw NoteNotFoundException("Note is deleted yet")

    }

    fun deleteComment(noteId: Int, comment: Comment, note: Note) {
        if (comment.noteId == noteId && comment.commentDeleteId && note.noteDeleteId) comments[noteId] =
            comment.copy(commentDeleteId = true)
        else throw NoteNotFoundException("Comment is deleted yet")

    }

    fun restoreComment(comment: Comment, note: Note) {
        if (!comment.commentDeleteId && note.noteDeleteId) comment.commentDeleteId =
            false else throw CommentNotFoundException("Comment not found")
    }

    fun getNotes(note: Note, ownerId: Int): MutableList<Note> {
        val list = mutableListOf<Note>()
        val iterator = list.listIterator()
        for (item in iterator) {
            iterator.add(note)
        }
        return list
    }

    fun noteGetById(notes: MutableList<Note>): MutableList<Int> {
        val noteId = mutableListOf<Int>()
        for (note in notes) {
            noteId.add(note.noteId)
        }
        return noteId

    }

    fun getComments(comment: Comment): MutableList<Comment> {
        val list = mutableListOf<Comment>()
        val iterator = list.listIterator()
        for (item in iterator) {
            iterator.add(comment)
        }
        return list
    }

}


