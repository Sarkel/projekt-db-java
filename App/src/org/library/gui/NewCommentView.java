package org.library.gui;

import com.sun.istack.internal.NotNull;
import org.library.model.Comments;
import org.library.wrappers.UserWrapper;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Sebastian Kubalski on 18.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class NewCommentView extends JFrame{

    private JPanel contentPane;
    private JButton cancelButton;
    private JButton submitButton;
    private JTextArea contentValue;
    private JSlider starValue;
    private JLabel starLabel;

    /**
     * @param book id of selected book
     */
    public NewCommentView(@NotNull Integer book){
        setContentPane(contentPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        starLabel.setText(String.valueOf(starValue.getValue()));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CommentsView(book);
                dispose();
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Comments.createComment(UserWrapper.UserId.getInstance().getId(), book, star,
                            contentValue.getText());
                    new BookDetailView(book);
                    dispose();
                } catch (SQLException ex){
                    System.out.println(ex.getMessage());
                    dispose();
                }
            }
        });
        starValue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                star = starValue.getValue();
                starLabel.setText(String.valueOf(starValue.getValue()));
            }
        });
    }

    private double star;
}
