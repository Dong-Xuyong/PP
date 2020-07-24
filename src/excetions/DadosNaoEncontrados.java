/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excetions;


public class DadosNaoEncontrados extends Exception {

    /**
     *
     */
    public DadosNaoEncontrados() {
        super("Dados nao econtrados!");
    }

    /**
     *
     * @param msg
     */
    public DadosNaoEncontrados(String msg) {
        super(msg);
    }

}
