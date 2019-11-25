package controller;

import dao.UsuarioDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;
import model.Usuario;
import utils.EncryptString;
import utils.FxUtils;
import utils.PasswordEncrypt;

public class LoginController {
    private EncryptString cryptator = new PasswordEncrypt();
    @FXML
    private TextField txtUserLogin, txtUserCad;
    @FXML
    private PasswordField txtSenhaCad, txtConSenhaCad, txtSenhaLogin;
    @FXML
    private Button btnCadastro;

    public LoginController() throws Exception {
    }

    public void logar() {
        try {
            String nome = txtUserLogin.getText().trim();
            String senha = cryptator.criptografar(String.format("%16s", txtSenhaLogin.getText()));
            if (nome.equals("") || senha.equals(""))
                throw new Exception("Informe o usuário e senha");
            Main.usuario = UsuarioDao.get(nome, senha);
            if (Main.usuario == null)
                throw new Exception("Usuário e senha invalidos");
            else {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
                stage.setTitle("Menu inicial");
                stage.setScene(new Scene(root, 640, 380));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

                Stage thisStage = (Stage) btnCadastro.getScene().getWindow();
                thisStage.close();
            }
        } catch (Exception ex)
        {
            FxUtils.mensagemLoad("Erro ao Logar", ex.getMessage());
        }
    }
    public void cancelarLogin(){
        System.exit(1);
    }

    public void cadastrar() {
        try {
            String nome = txtUserCad.getText().trim();
            String senha = txtSenhaCad.getText();
            if (nome.equals("") || senha.equals(""))
                throw new Exception("Usuário ou senha devem ser preexidos");
            if (!senha.equals(txtConSenhaCad.getText()))
                throw new Exception("Senhas não correspondem");
            Usuario u = new Usuario(nome, senha);
            UsuarioDao.salvar(u);
            Main.usuario = u;
            txtUserCad.setText("");
            txtSenhaCad.setText("");
            txtConSenhaCad.setText("");
            FxUtils.mensagemLoad("Cadastrado com sucesso");
        } catch (Exception ex){
            FxUtils.mensagemLoad("Erro ao Cadastrar", ex.getMessage());
        }

    }
    public void cancelarCadastro(){
        System.exit(1);
    }
}
