package org.library.gui;

import org.library.model.Book;
import org.library.wrappers.BookWrapper;
import org.library.wrappers.FunctionalityHelper;
import org.library.wrappers.UserWrapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Sebastian Kubalski on 18.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class BookDetailView extends JFrame {
    private JPanel contentPane;
    private JButton backButton;
    private JButton borrowButton;
    private JButton commentsButton;
    private JTextField tytulValue;
    private JTextField isbnValue;
    private JLabel starLabel;
    private JLabel starValues;
    private JLabel rokWydaniaLabel;
    private JLabel rokWydaniaValue;

    /**
     * @param bookId id of selected book
     */
    public BookDetailView(Integer bookId){
        setContentPane(contentPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        try{
            this.bookDetail = Book.getBookDetails(bookId);
            setValues();
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
        commentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CommentsView(bookDetail.id);
                dispose();
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

    private void setValues(){
        tytulValue.setText(this.bookDetail.tytul);
        isbnValue.setText(this.bookDetail.isbn);
        starValues.setText(String.valueOf(FunctionalityHelper.round(this.bookDetail.star, 1)));
        rokWydaniaValue.setText(String.valueOf(this.bookDetail.rok));
    }
}
