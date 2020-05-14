/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated;


import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.generated.tables.Author;
import org.jooq.generated.tables.Comment;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>public</code> schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index USER_ID_UINDEX = Indexes0.USER_ID_UINDEX;
    public static final Index COMMENT_ID_UINDEX = Indexes0.COMMENT_ID_UINDEX;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index USER_ID_UINDEX = Internal.createIndex("user_id_uindex", Author.AUTHOR, new OrderField[] { Author.AUTHOR.ID }, true);
        public static Index COMMENT_ID_UINDEX = Internal.createIndex("comment_id_uindex", Comment.COMMENT, new OrderField[] { Comment.COMMENT.ID }, true);
    }
}
