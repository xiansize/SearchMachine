package com.tcsoft.searchmachinary.presenter;

import com.tcsoft.searchmachinary.bean.Book;
import com.tcsoft.searchmachinary.model.action.BookAction;
import com.tcsoft.searchmachinary.model.action.BookActionImpl;
import com.tcsoft.searchmachinary.model.listener.ActionListener;
import com.tcsoft.searchmachinary.view.BookView;


public class BookPresenter extends BasePresenter<BookView> {


    private BookAction bookAction;
    private BookView bookView;

    public BookPresenter(BookView bookView) {
        this.bookView = bookView;
        this.bookAction = new BookActionImpl();
    }


    public void showWeather() {
        bookView.showWeather();
    }


    public void getBookHolding(Book b) {
        if (b.getRecNo() == null && !isViewAttached()) return;
        bookAction.getBookDetails(b, new ActionListener<Book>() {
            @Override
            public void onSuccess(Book book) {
                bookView.showBookDetails(book);
                if (book.gethList().size() > 0)
                    bookView.showBookHolding();
            }

            @Override
            public void onFailure(String tag, String msg) {

            }
        });
    }


}
