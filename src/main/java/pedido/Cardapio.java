package pedido;

import ingredientes.Ingrediente;

import java.util.Collections;
import java.util.TreeMap;

public class Cardapio {
    private TreeMap<Ingrediente, Double> precos;

    public Cardapio() {
        this.precos = new TreeMap<>();
    }

    public TreeMap<Ingrediente, Double> getPrecos() {
        return this.precos;
    }

    public void adicionarIngrediente(Ingrediente ingrediente, Double preco) {
        precos.put(ingrediente, preco);
    }

    public boolean atualizarIngrediente(Ingrediente ingrediente, Double preco) {
        precos.replace(ingrediente, preco);
        return true;
    }

    public boolean removerIngrediente(Ingrediente ingrediente) {
        precos.remove(ingrediente);
        return true;
    }

    public Double buscarPreco(Ingrediente ingrediente) {
        double preco = 0;

        for (int i = 0; i < precos.size(); i++) {
            preco = precos.get(ingrediente);
        }
        return preco;
    }

    @Override
    public String toString() {
        return this.precos.toString();
    }

}
