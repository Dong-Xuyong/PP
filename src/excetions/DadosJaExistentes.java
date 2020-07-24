/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excetions;

public class DadosJaExistentes extends Exception{
    
    /**
     *
     */
    public DadosJaExistentes() {
        super("Dados ja existentes!");
    }
    
    /**
     *
     * @param msg
     */
    public DadosJaExistentes(String msg) {
        super(msg);
    }
}
