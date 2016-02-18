package org.library.gui;

import org.library.model.Book;
import org.library.wrappers.BookWrapper;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * Created by Sebastian Kubalski on 18.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class TotalBookView extends JFrame {
    private JPanel contentPane;
    private JButton searchButton;
    private JTextField searchField;
    private JList searchResults;

    public TotalBookView(){
        setContentPane(contentPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        search();
        setVisible(true);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });

        searchResults.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                select(e);
            }
        });
    }

    private ArrayList<BookWrapper.Books> books;

    private void search(){
        try{
            Vector<String> vec = new Vector<>();
            if(searchField.getText().isEmpty()) {
                books = Book.getAll();
            } else {
                books = Book.getSearchResults(searchField.getText());
            }
            vec.addAll(books.stream().map(book -> book.tytul).collect(Collectors.toList()));
            searchResults.setListData(vec);
        } catch (SQLException e){
            System.out.println(e.getMessage());
            dispose();
        }
    }

    private void select(ListSelectionEvent e){
        new BookDetailView(books.get(e.getFirstIndex()).id);
        dispose();
    }
}
