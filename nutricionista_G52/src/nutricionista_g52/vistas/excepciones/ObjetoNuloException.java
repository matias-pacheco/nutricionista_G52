/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista_g52.vistas.excepciones;

/**
 *
 * @author Matías Pacheco
 */
public class ObjetoNuloException extends Exception {

    /**
     * Creates a new instance of <code>ObjetoNuloException</code> without detail message.
     */
    public ObjetoNuloException() {
    }

    /**
     * Constructs an instance of <code>ObjetoNuloException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ObjetoNuloException(String msg) {
        super(msg);
    }
}
