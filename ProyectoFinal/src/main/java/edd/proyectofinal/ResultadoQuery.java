package edd.proyectofinal;

/**
 *
 * @author jeffmenca
 */
public class ResultadoQuery {

    private String texto;
    private boolean valor;

    public ResultadoQuery(String texto, boolean valor) {
        this.texto = texto;
        this.valor = valor;
    }

    public String getTexto() {
        return texto;
    }

    public boolean getValor() {
        return valor;
    }
}
