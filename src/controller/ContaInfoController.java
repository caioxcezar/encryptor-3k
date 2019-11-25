package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import utils.FxUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class ContaInfoController implements Initializable {
    @FXML
    private TextField txtUsuario, txtQtdTextos;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Button btnOk;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    private void init(){
        txtQtdTextos.setDisable(true);
        txtUsuario.setDisable(true);
        txtQtdTextos.setText(Main.usuario.getTextos().size() + "");
        txtUsuario.setText(Main.usuario.getNome());
    }

    public void ok(){
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }
    public void alterarSenha() {
        try {
            Main.usuario.setSenha(String.format("%16s", txtSenha.getText()));
            FxUtils.mensagemLoad("Senha alterada com sucesso");
        } catch (Exception e) {
            FxUtils.mensagemLoad(e.getMessage());
        }
    }

}
