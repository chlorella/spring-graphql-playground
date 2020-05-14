/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated;


import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.generated.tables.Author;
import org.jooq.generated.tables.Book;
import org.jooq.generated.tables.BookComment;
import org.jooq.generated.tables.Comment;
import org.jooq.generated.tables.records.AuthorRecord;
import org.jooq.generated.tables.records.BookCommentRecord;
import org.jooq.generated.tables.records.BookRecord;
import org.jooq.generated.tables.records.CommentRecord;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>public</code> schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AuthorRecord> USER_PK = UniqueKeys0.USER_PK;
    public static final UniqueKey<BookRecord> BOOK_PK = UniqueKeys0.BOOK_PK;
    public static final UniqueKey<BookCommentRecord> BOOK_COMMENT_PK = UniqueKeys0.BOOK_COMMENT_PK;
    public static final UniqueKey<CommentRecord> COMMENT_PK = UniqueKeys0.COMMENT_PK;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<BookRecord, AuthorRecord> BOOK__BOOK_USER_ID_FK = ForeignKeys0.BOOK__BOOK_USER_ID_FK;
    public static final ForeignKey<BookCommentRecord, BookRecord> BOOK_COMMENT__BOOK_COMMENT_BOOK_ID_FK = ForeignKeys0.BOOK_COMMENT__BOOK_COMMENT_BOOK_ID_FK;
    public static final ForeignKey<CommentRecord, AuthorRecord> COMMENT__COMMENT_USER_ID_FK = ForeignKeys0.COMMENT__COMMENT_USER_ID_FK;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<AuthorRecord> USER_PK = Internal.createUniqueKey(Author.AUTHOR, "user_pk", new TableField[] { Author.AUTHOR.ID }, true);
        public static final UniqueKey<BookRecord> BOOK_PK = Internal.createUniqueKey(Book.BOOK, "book_pk", new TableField[] { Book.BOOK.ID }, true);
        public static final UniqueKey<BookCommentRecord> BOOK_COMMENT_PK = Internal.createUniqueKey(BookComment.BOOK_COMMENT, "book_comment_pk", new TableField[] { BookComment.BOOK_COMMENT.BOOK_ID, BookComment.BOOK_COMMENT.COMMENT_ID }, true);
        public static final UniqueKey<CommentRecord> COMMENT_PK = Internal.createUniqueKey(Comment.COMMENT, "comment_pk", new TableField[] { Comment.COMMENT.ID }, true);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<BookRecord, AuthorRecord> BOOK__BOOK_USER_ID_FK = Internal.createForeignKey(Keys.USER_PK, Book.BOOK, "book_user_id_fk", new TableField[] { Book.BOOK.USER_ID }, true);
        public static final ForeignKey<BookCommentRecord, BookRecord> BOOK_COMMENT__BOOK_COMMENT_BOOK_ID_FK = Internal.createForeignKey(Keys.BOOK_PK, BookComment.BOOK_COMMENT, "book_comment_book_id_fk", new TableField[] { BookComment.BOOK_COMMENT.BOOK_ID }, true);
        public static final ForeignKey<CommentRecord, AuthorRecord> COMMENT__COMMENT_USER_ID_FK = Internal.createForeignKey(Keys.USER_PK, Comment.COMMENT, "comment_user_id_fk", new TableField[] { Comment.COMMENT.USER_ID }, true);
    }
}