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
import main.Main;
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
    private ArrayList<Texto> textos;
    private Texto txtSelecionado;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    private void init() {
        listarMensagens();
    }

    private void listarMensagens() {
        try {
            textos = Main.usuario.getTextos();
            ObservableList<Texto> obTextos = FXCollections.observableArrayList(textos);
            lista_mensagens.getItems().clear();
            lista_mensagens.setItems(obTextos);
            lista_mensagens.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(Texto item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null || item.getCodigo() == 0) {
                        setText(null);
                    } else {
                        setText(String.format("id: %d", item.getCodigo()));
                    }
                }
            });
        } catch (Exception e) {
            FxUtils.mensagemLoad("Ocorreu um erro ao listar mensagens", e.getMessage());
        }
    }

    public void alterarMsg() {
        try{
            txtSelecionado.setTexto(txt_mensagem.getText());
            this.txtSelecionado.criptografar();
            TextoDao.alterar(this.txtSelecionado, Main.usuario);
        }catch (Exception ex) {
            utils.FxUtils.mensagemLoad("Erro ao carregar mensagem", ex.getLocalizedMessage());
        }
    }
    public void limparDados() {
        txt_mensagem.setText("");
    }

    public void exibirMsgEncriptada() {
        this.txtSelecionado = lista_mensagens.getSelectionModel().getSelectedItem();
        if (txtSelecionado != null){
            try {
                txtSelecionado.descriptografar();
                txt_mensagem.setText(txtSelecionado.getTexto());
            }catch (Exception ex) {
                utils.FxUtils.mensagemLoad("Erro ao carregar mensagem", ex.getLocalizedMessage());
            }
        }
    }
    public void apagarMsg(){
        try{
            Main.usuario.rmTexto(this.txtSelecionado);
            listarMensagens();
        }catch (Exception ex){
            utils.FxUtils.mensagemLoad(ex.getLocalizedMessage());
        }

    }
}
