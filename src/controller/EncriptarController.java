package controller;

import dao.TextoDao;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import model.Texto;
import utils.FxUtils;

public class EncriptarController {
    @FXML
    private TextArea txt_mensagem;
    @FXML
    private PasswordField txt_senha;

    public void encriptarMsg() {
        try {
            String mensagem = txt_mensagem.getText();
            String senha = txt_senha.getText();
            Texto t = new Texto(mensagem, senha);
            t.criptografar();
            TextoDao.salvar(t);
            FxUtils.mensagemLoad("Salvo com sucesso", String.format("Mensagem descriptografada: %s\n\n" +
                    "Mensagem encriptografada: %s\n\n" +
                    "Senha: %s", mensagem, t.getTexto(), t.getSenha()));
            limparMsg();
        } catch (Exception ex) {
            FxUtils.mensagemLoad("Ocorreu um erro ao tentar salvar", ex.getMessage());
        }
    }

    public void limparMsg() {
        txt_mensagem.setText("");
        txt_senha.setText("");
    }
}
