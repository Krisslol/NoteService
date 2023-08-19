import java.util.Date

data class Note(
    val noteId: Int,
    val title: String,
    val text: String,
    val ownerId: Int,
    val noteDeleteId: Boolean = false,
    val date: Date
)

data class Comment(
    val commentId: Int,
    val text: String,
    val ownerId: Int,
    val commentDeleteId: Boolean = false,
    val noteId: Int,
    val ownerCommentId: Int,
    val date: Date
)

object NoteService {

    private val notes = mutableListOf<Note>()
    private val comments = mutableListOf<Comment>()
    private var count = 0

    private fun addNote(note: Note): Note {
        notes += note.copy(noteId = count++)
        return notes.last()
    }

    private fun createComment(noteId: Int, comment: Comment): Comment {
        for ((index, note) in notes.withIndex()) {
            if (note.noteId == noteId) {
                comments += comment
                return comment
            }
        }
        throw CommentNotFoundException("Comment not found")
    }

    private fun editNote(newNote: Note): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (note.noteId == newNote.noteId && note.noteDeleteId) {
                note.ownerId == newNote.ownerId
                note.date == newNote.date
                note.text == newNote.text
                notes[index] = newNote.copy()
                return true
            }
        }
        return false
    }

    private fun editComment(newComment: Comment, note: Note): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (comment.noteId == newComment.noteId && comment.commentDeleteId && comment.commentId == newComment.commentId && note.noteDeleteId) {
                comment.ownerId == newComment.ownerId
                comment.date == newComment.date
                comment.text == newComment.text
                comment.ownerCommentId == newComment.ownerCommentId
                comments[index] = newComment.copy()
                return true

            }
        }
        return false
    }


    private fun deleteNote(noteId: Int, note: Note, comment: Comment) {
        if (note.noteId == noteId && note.noteDeleteId) notes[noteId] = note.copy(noteDeleteId = true)
        else throw NoteNotFoundException("Note is deleted yet")

    }

    private fun deleteComment(noteId: Int, comment: Comment, note: Note) {
        if (comment.noteId == noteId && comment.commentDeleteId && note.noteDeleteId) comments[noteId] =
            comment.copy(commentDeleteId = true)
        else throw NoteNotFoundException("Comment is deleted yet")

    }

    private fun restoreComment(comment: Comment, note: Note) {
        if (!comment.commentDeleteId && note.noteDeleteId) comment else throw CommentNotFoundException("Comment not found")
    }
}


