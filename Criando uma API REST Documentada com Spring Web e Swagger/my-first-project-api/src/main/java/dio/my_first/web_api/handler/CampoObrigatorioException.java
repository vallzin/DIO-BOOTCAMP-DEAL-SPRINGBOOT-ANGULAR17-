package dio.my_first.web_api.handler;

public class CampoObrigatorioException extends BusinessException{

    public CampoObrigatorioException(String campo) {
        super("O campo %s é obrigatório", campo);
    }
}
