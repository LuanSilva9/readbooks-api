package com.readbooks.api.contracts.usecases;

import java.util.UUID;

import com.readbooks.api.contracts.CrudContract;
import com.readbooks.api.model.Book;

public interface BookUC extends CrudContract<Book, UUID> {

}
