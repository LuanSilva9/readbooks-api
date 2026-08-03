-- V1__init_tables_system.sql

CREATE TABLE books (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    book_name VARCHAR(255) NOT NULL,
    description TEXT,
    link TEXT,
    start_read_date DATE,
    end_read_date DATE,
    num_pages_read BIGINT,
    num_pages_full BIGINT NOT NULL

    CONSTRAINT UQ_books_book_name UNIQUE(book_name)
);

CREATE TABLE summary_daily (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    summary_content TEXT NOT NULL,
    book_id UUID NOT NULL,
    created_at TIMESTAMPTZ default CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ,
    
    CONSTRAINT book_summary_fk FOREIGN KEY (book_id) references books(id) ON DELETE CASCADE
);

