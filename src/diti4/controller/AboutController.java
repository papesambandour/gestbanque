package diti4.controller;

import diti4.helper.Helper;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AboutController {
    Helper helper = new Helper();
    @FXML
    private AnchorPane about ;
    @FXML
    void retourClick(MouseEvent event) {
        try {
            helper.redirect(about,"profil.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
