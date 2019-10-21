package controller;

import dao.TextoDao;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.Texto;
import utils.FxUtils;

import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class DesencriptarController implements Initializable {
    @FXML
    private TextArea txt_mensagem;
    @FXML
    private ListView<Texto> lista_mensagens;
    @FXML
    private PasswordField txt_senha;
    private ArrayList<Texto> textos;
    private Texto txtSelecionado;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }
    public void init() {
        try {
            textos = TextoDao.listar();
            ObservableList<Texto> texto = FXCollections.observableArrayList(textos);
            lista_mensagens.setItems(texto);
            lista_mensagens.setCellFactory(param -> new ListCell<Texto>() {
                @Override
                protected void updateItem(Texto item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null || item.getCodigo() == 0) {
                        setText(null);
                    } else {
                        setText(String.format("id: %d msg: %s", item.getCodigo(), item.getTexto().substring(0, 15)));
                    }
                }
            });
        } catch (SQLException | ClassNotFoundException e) {
            FxUtils.mensagemLoad("Ocorreu um erro ao listar mensagens", e.getMessage());
        }
    }
    public void desencriptarMsg() {
        try {
            if (txt_senha.getText().equals(txtSelecionado.getSenha().trim())) {
                txtSelecionado.descriptografar();
                txt_mensagem.setText(txtSelecionado.getTexto());
            }
        }catch (Exception ex) {
            utils.FxUtils.mensagemLoad("Erro ao carregar mensagem", ex.getLocalizedMessage());
        }
    }
    public void limparDados() {
        txt_mensagem.setText("");
    }
    public void exibirMsgEncriptada() {
        this.txtSelecionado = lista_mensagens.getSelectionModel().getSelectedItem();
    }
}
