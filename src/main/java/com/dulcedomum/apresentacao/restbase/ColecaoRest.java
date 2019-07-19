package com.dulcedomum.apresentacao.restbase;

import java.util.ArrayList;
import java.util.List;

public class ColecaoRest {
    private List<ElementoRest> elementos = new ArrayList();

    public ColecaoRest() {
    }

    public ColecaoRest(List<ElementoRest> elementos) {
        this.elementos = elementos;
    }

    public List<ElementoRest> getElementos() {
        return this.elementos;
    }
}
