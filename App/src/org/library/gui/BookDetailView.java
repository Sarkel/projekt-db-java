package org.library.gui;

import org.library.model.Book;
import org.library.wrappers.BookWrapper;
import org.library.wrappers.UserWrapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by sebas on 18.02.2016.
 */
public class BookDetailView extends JFrame {
    private JPanel contentPane;
    private JButton backButton;
    private JButton borrowButton;
    private JButton commentsButton;

    public BookDetailView(Integer bookId){
        setContentPane(contentPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        try{
            this.bookDetail = Book.getBookDetails(bookId);
        } catch (SQLException e){
            System.out.println(e.getMessage());
            dispose();
        }
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                back();
            }
        });
        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrow();
                back();
            }
        });
    }

    private BookWrapper.BookDetail bookDetail;

    private void back(){
        TotalBookView view = new TotalBookView();
        dispose();
    }

    private void borrow(){
        try{
            Book.borrowBook(this.bookDetail.id, UserWrapper.UserId.getInstance().getId());
        } catch (SQLException e){
            System.out.println(e.getMessage());
            dispose();
        }
    }
}
