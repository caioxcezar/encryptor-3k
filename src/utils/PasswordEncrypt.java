package utils;

public class PasswordEncrypt extends EncryptString {

    public PasswordEncrypt() throws Exception {
        super("68Ui%HxztyXRAex@");
    }
    @Override
    public String getChave() throws Exception {
        throw new Exception("Não é possivel visualizar a chave");
    }
}
