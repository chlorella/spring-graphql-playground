/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables.records;


import java.time.LocalDate;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.generated.tables.Book;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BookRecord extends UpdatableRecordImpl<BookRecord> implements Record4<UUID, String, LocalDate, UUID> {

    private static final long serialVersionUID = -1245994886;

    /**
     * Setter for <code>public.book.id</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.book.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.book.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.book.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.book.publish_date</code>.
     */
    public void setPublishDate(LocalDate value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.book.publish_date</code>.
     */
    public LocalDate getPublishDate() {
        return (LocalDate) get(2);
    }

    /**
     * Setter for <code>public.book.user_id</code>.
     */
    public void setUserId(UUID value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.book.user_id</code>.
     */
    public UUID getUserId() {
        return (UUID) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<UUID, String, LocalDate, UUID> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<UUID, String, LocalDate, UUID> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Book.BOOK.ID;
    }

    @Override
    public Field<String> field2() {
        return Book.BOOK.NAME;
    }

    @Override
    public Field<LocalDate> field3() {
        return Book.BOOK.PUBLISH_DATE;
    }

    @Override
    public Field<UUID> field4() {
        return Book.BOOK.USER_ID;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public LocalDate component3() {
        return getPublishDate();
    }

    @Override
    public UUID component4() {
        return getUserId();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public LocalDate value3() {
        return getPublishDate();
    }

    @Override
    public UUID value4() {
        return getUserId();
    }

    @Override
    public BookRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public BookRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public BookRecord value3(LocalDate value) {
        setPublishDate(value);
        return this;
    }

    @Override
    public BookRecord value4(UUID value) {
        setUserId(value);
        return this;
    }

    @Override
    public BookRecord values(UUID value1, String value2, LocalDate value3, UUID value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BookRecord
     */
    public BookRecord() {
        super(Book.BOOK);
    }

    /**
     * Create a detached, initialised BookRecord
     */
    public BookRecord(UUID id, String name, LocalDate publishDate, UUID userId) {
        super(Book.BOOK);

        set(0, id);
        set(1, name);
        set(2, publishDate);
        set(3, userId);
    }
}
