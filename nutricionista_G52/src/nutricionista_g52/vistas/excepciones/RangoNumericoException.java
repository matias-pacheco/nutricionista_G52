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
public class RangoNumericoException extends Exception {

    /**
     * Creates a new instance of <code>RangoNumericoException</code> without detail message.
     */
    public RangoNumericoException() {
    }

    /**
     * Constructs an instance of <code>RangoNumericoException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public RangoNumericoException(String msg) {
        super(msg);
    }
}
